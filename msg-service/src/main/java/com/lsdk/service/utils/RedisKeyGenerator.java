package com.lsdk.service.utils;


import com.lsdk.service.config.Constans;

//redis key的生成工具
public class RedisKeyGenerator {

    public static String keyGenerator(String... keys){
        StringBuilder stringBuilder = new StringBuilder();
        int length = keys.length;
        int i = 1;
        for (String key:keys){
            stringBuilder.append(key);
            if(i < length){
                stringBuilder.append(Constans.SYMBOL_COLON);
            }
            i++;
        }
        return stringBuilder.toString();
    }

    private RedisKeyGenerator(){}

}
