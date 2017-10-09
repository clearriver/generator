package com.river.generator.handler.impl;

import java.io.File;

import com.river.generator.Constants;
import com.river.generator.config.Configuration;
import com.river.generator.handler.BaseHandler;
import com.river.generator.model.DaoInfo;

public class DaoHandler extends BaseHandler<DaoInfo> {

    public DaoHandler(String ftlName, DaoInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.bizName="dao";
        this.savePath = info.getClassName() + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(DaoInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("importStr", info.getImportStr());
        this.param.put("entityDesc", info.getEntityInfo().getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("entityClassName", info.getEntityInfo().getClassName());
        this.param.put("entityName", info.getEntityInfo().getEntityName());
    }

}
