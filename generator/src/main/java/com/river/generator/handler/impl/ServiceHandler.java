package com.river.generator.handler.impl;

import java.io.File;

import com.river.generator.Constants;
import com.river.generator.config.Configuration;
import com.river.generator.handler.BaseHandler;
import com.river.generator.model.ServiceInfo;

public class ServiceHandler extends BaseHandler<ServiceInfo> {

    public ServiceHandler(String ftlName, ServiceInfo info){
        this.info = info;
        this.ftlName = ftlName;
        this.bizName="service";
        this.savePath = info.getClassName() + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(ServiceInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("getCommandType", info.getGetCommandType());
        this.param.put("listCommandType", info.getListCommandType());
        this.param.put("batchCommandType", info.getBatchCommandType());
        this.param.put("commandType", info.getCommandType());
        this.param.put("queryCommandType", info.getQueryCommandType());
        this.param.put("voType", info.getVoType());
        this.param.put("entityDesc", info.getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("entityName", info.getEntityName());
        this.param.put("voClassName", info.getVoClassName());
    }

}
