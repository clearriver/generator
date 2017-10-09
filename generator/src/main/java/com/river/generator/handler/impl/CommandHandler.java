package com.river.generator.handler.impl;

import java.io.File;

import com.river.generator.Constants;
import com.river.generator.config.Configuration;
import com.river.generator.handler.BaseHandler;
import com.river.generator.model.CommandInfo;
import com.river.generator.util.StringUtil;

public class CommandHandler extends BaseHandler<CommandInfo> {

    private String className;

    public CommandHandler(String ftlName, CommandInfo info, String className){
        this.ftlName = ftlName;
        this.className = className;
        this.info = info;
        this.bizName="command";
        this.savePath = this.className + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(CommandInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("voType", info.getVoType());
        this.param.put("entityName", info.getEntityName());
        this.param.put("voClassName", info.getVoClassName());
        this.param.put("className", this.className);
        this.param.put("serialVersionNum", StringUtil.generate16LongNum());
    }

}
