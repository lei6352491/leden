package com.zhouyi.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Author: first
 * @Date: 上午6:23 2019/11/6
 * @Description: 线程池配置
**/
@Configuration
public class TaskScheduledConfiguration {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

}
