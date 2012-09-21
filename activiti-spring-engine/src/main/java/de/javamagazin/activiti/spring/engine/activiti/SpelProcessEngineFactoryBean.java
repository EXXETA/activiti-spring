package de.javamagazin.activiti.spring.engine.activiti;

import org.activiti.spring.ProcessEngineFactoryBean;

public class SpelProcessEngineFactoryBean extends ProcessEngineFactoryBean {

    @Override
    protected void initializeExpressionManager() {
        if (applicationContext != null) {
            SpelExpressionManager expressionManager = new SpelExpressionManager(applicationContext, processEngineConfiguration.getBeans());
            processEngineConfiguration.setExpressionManager(expressionManager);
        }
    }
}