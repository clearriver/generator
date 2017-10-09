package com.river.generator.handler.impl;

import java.io.File;

import com.river.generator.Constants;
import com.river.generator.config.Configuration;
import com.river.generator.handler.BaseHandler;
import com.river.generator.model.ServiceImplInfo;
import com.river.generator.model.ServiceTestInfo;

public class ServiceTestHandler extends BaseHandler<ServiceTestInfo> {

    public ServiceTestHandler(String ftlName, ServiceTestInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.bizName="serviceTest";
        this.savePath =  info.getClassName() + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(ServiceTestInfo serviceTestInfo) {
        ServiceImplInfo info = serviceTestInfo.getServiceImplInfo();
        this.param.put("packageStr", serviceTestInfo.getPackageStr());
        this.param.put("serviceType", info.getServiceType());
        this.param.put("getCommandType", info.getGetCommandType());
        this.param.put("listCommandType", info.getListCommandType());
        this.param.put("batchCommandType", info.getBatchCommandType());
        this.param.put("commandType", info.getCommandType());
        this.param.put("queryCommand", info.getQueryCommand());
        this.param.put("voType", info.getVoType());
        this.param.put("entityDesc", info.getEntityDesc());
        this.param.put("lowerEntityName", info.getLowerEntityName());
        this.param.put("entityName", info.getEntityName());
    }

}
