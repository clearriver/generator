package com.river.generator.task;

import java.util.List;

import com.river.generator.framework.AbstractApplicationTask;
import com.river.generator.framework.context.ApplicationContext;
import com.river.generator.handler.BaseHandler;
import com.river.generator.handler.impl.ServiceImplHandler;
import com.river.generator.model.ServiceImplInfo;

public class ServiceImplTask extends AbstractApplicationTask {

    private static String SERVICEIMPL_FTL = "template/ServiceImpl.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成serviceImpl...");
        List<ServiceImplInfo> list = (List<ServiceImplInfo>) context.getAttribute("serviceImplInfos");

        BaseHandler<ServiceImplInfo> baseHandler = null;
        for (ServiceImplInfo info : list) {
            baseHandler = new ServiceImplHandler(SERVICEIMPL_FTL, info);
            baseHandler.setConfig(getConfig());
            baseHandler.execute(context);
        }

        logger.info("结束生成serviceImpl。。。");
        return false;
    }

}
