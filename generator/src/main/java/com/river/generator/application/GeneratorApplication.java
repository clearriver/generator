package com.river.generator.application;

import java.util.HashMap;
import java.util.Map;

import com.river.generator.config.Configuration;
import com.river.generator.framework.Application;
import com.river.generator.task.CombineInfoTask;
import com.river.generator.task.CommandTask;
import com.river.generator.task.DaoTask;
import com.river.generator.task.EntityTask;
import com.river.generator.task.InitTask;
import com.river.generator.task.ManagerImplTask;
import com.river.generator.task.ManagerTask;
import com.river.generator.task.MapperTask;
import com.river.generator.task.ServiceImplTask;
import com.river.generator.task.ServiceTask;
import com.river.generator.task.ServiceTestTask;
import com.river.generator.task.VoTask;

/**
 * 程序入口
 * 
 * @version
 * 
 * <pre>
 * Author	Version		Date			Changes
 * wuqh 	1.0  		2017年10月06日 	Created
 * </pre>
 * 
 * @since 1.
 * Copyright (c) 2017, Thinkive(http://www.thinkive.com/) All Rights Reserved.
 */
public class GeneratorApplication {

    public static void main(String[] args) {
        // 程序入口
        Application application = new Application(GeneratorApplication.class.getSimpleName());
        application.parseArgs(args);
        application.setConfig(initConfig());
        application.setApplicationName(GeneratorApplication.class.getName());
        application.addApplicationTask(InitTask.class) // 获取数据库表以及字段相关信息
            .addApplicationTask(CombineInfoTask.class) // 基本信息封装
            .addApplicationTask(EntityTask.class) // 生成Entity
            .addApplicationTask(DaoTask.class) // 生成Dao
            .addApplicationTask(MapperTask.class) // 生成Mapper.xml
            .addApplicationTask(VoTask.class) // 生成Vo
            .addApplicationTask(ManagerTask.class) // 生成Manager
            .addApplicationTask(ManagerImplTask.class) // 生成ManagerImpl
            .addApplicationTask(CommandTask.class) // 生成Command
            .addApplicationTask(ServiceTask.class) // 生成Service
            .addApplicationTask(ServiceImplTask.class) // 生成ServiceImpl
            .addApplicationTask(ServiceTestTask.class) // 生成Service单元测试类
            .work();
    }
    public static Map<String,String> initConfig(){
    	Map<String,String>   config            = new HashMap<String,String>();
    	config.put("jdbc.driverName", "com.mysql.jdbc.Driver");
    	config.put("jdbc.url", "jdbc:mysql://127.0.0.1:3306/river?useUnicode=yes&amp;characterEncoding=UTF8&amp;allowMultiQueries=true");
    	config.put("jdbc.username", "river");
    	config.put("jdbc.password", "river");
    	
    	config.put("base.database", "mysql");
    	config.put("base.schemaPattern", "river");
    	config.put("base.baseColumns", "");
    	config.put("base.tableNames", "eop_app");
    	config.put("base.entityNames", "EopApp");
    	config.put("base.entityDescs", "应用信息");
    	config.put("base.baseDir", "D:\\river\\logs\\auto\\com\\river");
    	
    	config.put("entity.package", "com.river.entity");
    	config.put("entity.path", "entity");
    	
    	config.put("dao.package", "com.river.dao");
    	config.put("dao.path", "dao");
    	
    	config.put("mapperXml.path", "mapper");
    	
    	config.put("vo.package", "com.river.dao");
    	config.put("vo.path", "dao");
    	
    	config.put("manager.package", "com.river.manager");
    	config.put("manager.path", "manager");
    	
    	config.put("managerImpl.package", "com.river.manager.impl");
    	config.put("managerImpl.path", "manager/impl");
    	
    	config.put("command.package", "com.river.command");
    	config.put("command.path", "command");
    	
    	config.put("service.package", "com.river.service");
    	config.put("service.path", "service");
    	
    	config.put("serviceImpl.package", "com.river.service.impl");
    	config.put("serviceImpl.path", "service/impl");
    	
    	config.put("serviceTest.package", "com.river.service");
    	config.put("serviceTest.path", "service");
    	return config;
    }
}
