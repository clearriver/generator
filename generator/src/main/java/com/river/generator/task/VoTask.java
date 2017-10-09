package com.river.generator.task;

import java.util.ArrayList;
import java.util.List;

import com.river.generator.Constants;
import com.river.generator.config.Configuration;
import com.river.generator.framework.AbstractApplicationTask;
import com.river.generator.framework.context.ApplicationContext;
import com.river.generator.handler.BaseHandler;
import com.river.generator.handler.impl.VoHandler;
import com.river.generator.model.CommandInfo;
import com.river.generator.model.EntityInfo;
import com.river.generator.model.ManagerInfo;
import com.river.generator.model.VoInfo;

public class VoTask extends AbstractApplicationTask {

    private static String VO_FTL = "template/Vo.ftl";
    private List<VoInfo>  voList;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成vo");
        voList = (List<VoInfo>) context.getAttribute("voList");

        BaseHandler<VoInfo> handler = null;
        for (VoInfo voInfo : voList) {
            handler = new VoHandler(VO_FTL, voInfo);
            handler.setConfig(getConfig());
            handler.execute(context);
        }
        logger.info("结束生成vo");
        return false;
    }

    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);

        // 组装ManagerInfo、CommandInfo
        List<ManagerInfo> managerInfos = new ArrayList<ManagerInfo>();
        List<CommandInfo> commandInfos = new ArrayList<CommandInfo>();
        ManagerInfo managerInfo = null;
        CommandInfo commandInfo = null;
        for (VoInfo voInfo : voList) {
            managerInfo = new ManagerInfo();
            commandInfo = new CommandInfo();

            EntityInfo entityInfo = voInfo.getEntityInfo();

            managerInfo.setClassName(entityInfo.getEntityName() + Constants.MANAGER_SUFFIX);
            managerInfo.setEntityDesc(entityInfo.getEntityDesc());
            managerInfo.setPackageStr(getConfigByKey("manager.package"));
            managerInfo.setVoClassName(voInfo.getClassName());
            managerInfo.setVoType(voInfo.getPackageStr() + Constants.CHARACTER_POINT + voInfo.getClassName());
            managerInfo.setVoInfo(voInfo);

            commandInfo.setEntityName(entityInfo.getEntityName());
            commandInfo.setPackageStr(getConfigByKey("command.package"));
            commandInfo.setVoClassName(voInfo.getClassName());
            commandInfo.setVoType(voInfo.getPackageStr() + Constants.CHARACTER_POINT + voInfo.getClassName());
            commandInfo.setClassName(entityInfo.getEntityName() + Constants.COMMAND_SUFFIX);
            commandInfo.setGetClassName("Get" + entityInfo.getEntityName() + Constants.COMMAND_SUFFIX);
            commandInfo.setQueryClassName(entityInfo.getEntityName() + "Query" + Constants.COMMAND_SUFFIX);
            commandInfo.setBatchClassName(entityInfo.getEntityName() + "Batch" + Constants.COMMAND_SUFFIX);
            commandInfo.setListClassName("List" + entityInfo.getEntityName() + Constants.COMMAND_SUFFIX);

            managerInfos.add(managerInfo);
            commandInfos.add(commandInfo);
        }
        context.setAttribute("managerInfos", managerInfos);
        context.setAttribute("commandInfos", commandInfos);

    }

}
