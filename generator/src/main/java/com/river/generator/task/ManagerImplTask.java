package com.river.generator.task;

import java.util.List;

import com.river.generator.framework.AbstractApplicationTask;
import com.river.generator.framework.context.ApplicationContext;
import com.river.generator.handler.BaseHandler;
import com.river.generator.handler.impl.ManagerImplHandler;
import com.river.generator.model.ManagerImplInfo;

public class ManagerImplTask extends AbstractApplicationTask {

    private static String MANAGERIMPL_FTL = "template/ManagerImpl.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成managerImpl");

        List<ManagerImplInfo> managerInfos = (List<ManagerImplInfo>) context.getAttribute("managerImplInfos");

        BaseHandler<ManagerImplInfo> handler = null;
        for (ManagerImplInfo managerImplInfo : managerInfos) {
            handler = new ManagerImplHandler(MANAGERIMPL_FTL, managerImplInfo);
            handler.setConfig(getConfig());
            handler.execute(context);
        }

        logger.info("生成managerImpl完成");
        return false;
    }

}
