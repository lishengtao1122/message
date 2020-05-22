package com.lsdk.service.job;

import com.lsdk.service.service.impl.MessageLogService;
import com.lsdk.service.service.impl.MessageService;
import com.lsdk.service.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;

public abstract class BaseTask implements Runnable {

    protected ApplicationContext applicationContext;

    protected MessageService messageService;

    protected MessageLogService messageLogService;

    public BaseTask(){
        this.applicationContext = ApplicationContextUtils.getApplicationContext();
        this.messageService = this.applicationContext.getBean(MessageService.class);
        this.messageLogService = this.applicationContext.getBean(MessageLogService.class);
    }

}
