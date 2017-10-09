package com.river.generator.handler;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.river.generator.framework.context.ApplicationContext;
import com.river.generator.util.DateUtil;
import com.river.generator.util.FileHelper;
import com.river.generator.util.FreeMarkerUtil;

public abstract class BaseHandler<T> {

    protected ApplicationContext  context;
    protected String              ftlName;
    protected String              savePath;
    /**bizName:业务模块(category)名称,为必须有值*/
    protected String 			bizName;
    protected Map<String, String> param = new HashMap<String, String>();
    protected T                   info;
    private Map<String,String>   config            = new HashMap<String,String>();

    public String generateFinalStr() {
        String temp = FileHelper.readFileToString(this.getClass().getClassLoader().getResource("").getPath() + ftlName);
        String modelStr = FreeMarkerUtil.getProcessValue(param, temp);
        modelStr=modelStr.replaceAll("\r\n", "\n");
        modelStr=modelStr.replaceAll("\n", "\r\n");
        return modelStr;
    }

    /**
     * 保存到文件
     * 
     * @param str
     */
    public void saveToFile(String str) {
        FileHelper.writeToFile(getConfigByKey("base.baseDir") + File.separator + getConfigByKey(bizName+".path") + File.separator +savePath, str);
    }

    /**
     * 组装参数
     * 
     * @param param
     */
    public abstract void combileParams(T info);

    /**
     * 设置一些公共的参数.
     */
    public void beforeGenerate() {
        String time = DateUtil.formatDataToStr(new Date(), "yyyy年MM月dd日");
        param.put("author", System.getProperty("user.name"));
        param.put("time", time);
    }

    /**
     * 生成文件
     */
    public void execute(ApplicationContext context) {
        this.context = context;
        String str = null;
        combileParams(info);
        beforeGenerate();
        str = generateFinalStr();
        saveToFile(str);
    }

    public void setConfig(Map<String, String> config){
    	this.config=config;
    }
    public Map<String, String> getConfig(){
    	return config==null?config=new HashMap<String,String>():config;
    }
    public String getConfigByKey(String name) {
        String value = (String) getConfig().get(name);
        return (value == null) ? "":value;
    }
}
