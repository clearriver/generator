package com.river.generator.handler.impl;

import java.io.File;

import com.river.generator.Constants;
import com.river.generator.config.Configuration;
import com.river.generator.handler.BaseHandler;
import com.river.generator.model.ManagerInfo;

public class ManagerHandler extends BaseHandler<ManagerInfo> {

    public ManagerHandler(String ftlName, ManagerInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.bizName="manager";
        this.savePath = info.getClassName() + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(ManagerInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("voType", info.getVoType());
        this.param.put("entityDesc", info.getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("voClassName", info.getVoClassName());
        this.param.put("entityName", info.getVoInfo().getEntityInfo().getEntityName());
    }

}
