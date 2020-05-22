package com.lsdk.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.Executor;

@Component
public class SchedulerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

    @Resource(name = "sysExcutor")
    private Executor executor;

    //清除完成的message
    @Scheduled(cron = "0 * * * * ?")
    public void preWindControl(){
        logger.info(" >>> clear finished job start time:{} <<< ",new Date());
        executor.execute(new ClearMessageTask());
        logger.info(" >>> clear finished job end time:{} <<< ",new Date());
    }


}
