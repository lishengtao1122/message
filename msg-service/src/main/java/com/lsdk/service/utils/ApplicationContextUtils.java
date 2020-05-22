package com.lsdk.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware{

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextUtils.class);

    //静态变量保存上下文变量
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        if(applicationContext==null){
            logger.error(">>> applicationContextUtils init applicationContext error ! <<<",new Throwable("init applicationContext error!"));
            System.exit(0);
        }
        return applicationContext.getBean(clazz);
    }

}
