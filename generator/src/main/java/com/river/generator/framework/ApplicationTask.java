package com.river.generator.framework;

import java.util.Map;

import com.river.generator.framework.context.ApplicationContext;

public interface ApplicationTask extends Skipable {

    boolean perform(ApplicationContext context) throws Exception;

    boolean hasNext();

    void registerNextTask(ApplicationTask nextTask);

    ApplicationTask next();

    void initLogger(String applicationTaskId, String applicationId);
    void setConfig(Map<String, String> config);
}
