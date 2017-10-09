package com.river.generator.task;

import java.util.List;

import com.river.generator.framework.AbstractApplicationTask;
import com.river.generator.framework.context.ApplicationContext;
import com.river.generator.handler.BaseHandler;
import com.river.generator.handler.impl.ServiceTestHandler;
import com.river.generator.model.ServiceTestInfo;

public class ServiceTestTask extends AbstractApplicationTask {

    private static String SERVICETEST_FTL = "template/ServiceTest.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成service单元测试类。。。");

        List<ServiceTestInfo> list = (List<ServiceTestInfo>) context.getAttribute("serviceTestInfos");

        BaseHandler<ServiceTestInfo> baseHandler = null;
        for (ServiceTestInfo serviceTestInfo : list) {
            baseHandler = new ServiceTestHandler(SERVICETEST_FTL, serviceTestInfo);
            baseHandler.setConfig(getConfig());
            baseHandler.execute(context);
        }

        logger.info("生成service单元测试类完成。。。");
        return false;
    }

}
