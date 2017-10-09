package com.river.generator.handler.impl;

import java.io.File;

import com.river.generator.Constants;
import com.river.generator.config.Configuration;
import com.river.generator.handler.BaseHandler;
import com.river.generator.model.ServiceImplInfo;

public class ServiceImplHandler extends BaseHandler<ServiceImplInfo> {

    public ServiceImplHandler(String ftlName, ServiceImplInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.bizName="serviceImpl";
        this.savePath = info.getClassName() + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(ServiceImplInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("managerType", info.getManagerType());
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
