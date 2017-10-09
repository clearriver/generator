package com.river.generator.task;

import java.util.List;

import com.river.generator.Constants;
import com.river.generator.config.Configuration;
import com.river.generator.framework.AbstractApplicationTask;
import com.river.generator.framework.context.ApplicationContext;
import com.river.generator.handler.BaseHandler;
import com.river.generator.handler.impl.MapperHandler;
import com.river.generator.model.MapperInfo;

public class MapperTask extends AbstractApplicationTask {

    private String MAPPER_FTL = getConfigByKey("base.database")
        .equals(Constants.DB_ORACLE) ? "template/Mapper_oracle.ftl" : "template/Mapper.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成Mapper");

        List<MapperInfo> list = (List<MapperInfo>) context.getAttribute("mapperInfos");

        BaseHandler<MapperInfo> handler = null;
        for (MapperInfo mapperInfo : list) {
            handler = new MapperHandler(MAPPER_FTL, mapperInfo);
            handler.setConfig(getConfig());
            handler.execute(context);
        }

        logger.info("生成Mapper完成");
        return false;
    }

    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
    }
}
