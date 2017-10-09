package com.river.generator.task;

import java.util.ArrayList;
import java.util.List;

import com.river.generator.Constants;
import com.river.generator.config.Configuration;
import com.river.generator.framework.AbstractApplicationTask;
import com.river.generator.framework.context.ApplicationContext;
import com.river.generator.handler.BaseHandler;
import com.river.generator.handler.impl.ServiceHandler;
import com.river.generator.model.CommandInfo;
import com.river.generator.model.EntityInfo;
import com.river.generator.model.ManagerInfo;
import com.river.generator.model.ServiceImplInfo;
import com.river.generator.model.ServiceInfo;
import com.river.generator.model.ServiceTestInfo;

public class ServiceTask extends AbstractApplicationTask {

    private static String SERVICE_FTL = "template/Service.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成service。。。");

        List<ServiceInfo> serviceInfos = (List<ServiceInfo>) context.getAttribute("serviceInfos");

        BaseHandler<ServiceInfo> baseHandler = null;
        for (ServiceInfo serviceInfo : serviceInfos) {
            baseHandler = new ServiceHandler(SERVICE_FTL, serviceInfo);
            baseHandler.setConfig(getConfig());
            baseHandler.execute(context);
        }

        logger.info("结束生成service。。。");
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);

        List<ServiceInfo> serviceInfos = (List<ServiceInfo>) context.getAttribute("serviceInfos");
        List<EntityInfo> entityInfos = (List<EntityInfo>) context.getAttribute("entityInfos");
        List<ManagerInfo> managerInfos = (List<ManagerInfo>) context.getAttribute("managerInfos");

        List<ServiceImplInfo> serviceImplInfos = new ArrayList<ServiceImplInfo>();
        List<ServiceTestInfo> serviceTestInfos = new ArrayList<ServiceTestInfo>();
        ServiceImplInfo serviceImplInfo = null;
        ServiceTestInfo serviceTestInfo = null;
        for (int i = 0; i < serviceInfos.size(); i++) {
            ServiceInfo serviceInfo = serviceInfos.get(i);
            EntityInfo entityInfo = entityInfos.get(i);
            ManagerInfo managerInfo = managerInfos.get(i);
            serviceImplInfo = new ServiceImplInfo();
            serviceTestInfo = new ServiceTestInfo();
            CommandInfo commandInfo = serviceInfo.getCommandInfo();
            serviceImplInfo.setBatchCommandType(
                commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getBatchClassName());
            serviceImplInfo.setClassName(commandInfo.getEntityName() + Constants.SERVICE_IMPL_SUFFIX);
            serviceImplInfo
                .setCommandType(commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getClassName());
            serviceImplInfo.setEntityDesc(entityInfo.getEntityDesc());
            serviceImplInfo.setEntityName(entityInfo.getEntityName());
            serviceImplInfo.setGetCommandType(
                commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getGetClassName());
            serviceImplInfo.setListCommandType(
                commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getListClassName());
            serviceImplInfo.setLowerEntityName(
                entityInfo.getEntityName().substring(0, 1).toLowerCase() + entityInfo.getEntityName().substring(1));
            serviceImplInfo
                .setManagerType(managerInfo.getPackageStr() + Constants.CHARACTER_POINT + managerInfo.getClassName());
            serviceImplInfo.setPackageStr(getConfigByKey("serviceImpl.package"));
            serviceImplInfo.setQueryCommand(
                commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getQueryClassName());
            serviceImplInfo
                .setServiceType(serviceInfo.getPackageStr() + Constants.CHARACTER_POINT + serviceInfo.getClassName());
            serviceImplInfo.setVoType(commandInfo.getVoType());

            serviceTestInfo.setClassName(commandInfo.getEntityName() + Constants.SERVICE_TEST_SUFFIX);
            serviceTestInfo.setPackageStr(getConfigByKey("serviceTest.package"));
            serviceTestInfo.setServiceImplInfo(serviceImplInfo);

            serviceImplInfos.add(serviceImplInfo);
            serviceTestInfos.add(serviceTestInfo);
        }
        context.setAttribute("serviceImplInfos", serviceImplInfos);
        context.setAttribute("serviceTestInfos", serviceTestInfos);
    }

}
