package com.lsdk.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
@MapperScan("com.lsdk.service.dao.mapper")
@ComponentScan("com.lsdk.service")
@EnableScheduling
public class MsgApplication {

    public static void main(String[] args){
        SpringApplication.run(MsgApplication.class,args);
    }

}
