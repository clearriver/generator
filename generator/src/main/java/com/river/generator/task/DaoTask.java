package com.river.generator.task;

import java.util.ArrayList;
import java.util.List;

import com.river.generator.Constants;
import com.river.generator.framework.AbstractApplicationTask;
import com.river.generator.framework.context.ApplicationContext;
import com.river.generator.handler.BaseHandler;
import com.river.generator.handler.impl.DaoHandler;
import com.river.generator.model.DaoInfo;
import com.river.generator.model.MapperInfo;

public class DaoTask extends AbstractApplicationTask {

    private static String DAO_FTL = "template/Dao.ftl";

    private List<DaoInfo> daoInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成dao");

        daoInfos = (List<DaoInfo>) context.getAttribute("daoList");

        BaseHandler<DaoInfo> handler = null;
        for (DaoInfo daoInfo : daoInfos) {
            handler = new DaoHandler(DAO_FTL, daoInfo);
            handler.setConfig(getConfig());
            handler.execute(context);
        }

        logger.info("生成dao完成");
        return false;
    }

    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);

        List<MapperInfo> mapperInfos = new ArrayList<MapperInfo>();
        MapperInfo mapperInfo = null;
        for (DaoInfo daoInfo : daoInfos) {
            mapperInfo = new MapperInfo();
            mapperInfo.setDaoInfo(daoInfo);
            mapperInfo.setEntityInfo(daoInfo.getEntityInfo());
            mapperInfo.setFileName(daoInfo.getEntityInfo().getEntityName() + Constants.MAPPER_XML_SUFFIX);
            mapperInfo.setNamespace(daoInfo.getPackageStr() + Constants.CHARACTER_POINT + daoInfo.getClassName());
            mapperInfos.add(mapperInfo);
        }
        context.setAttribute("mapperInfos", mapperInfos);
    }

}
