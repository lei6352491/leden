package com.zhouyi.business.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.*;

/**
 * @Author: first
 * @Date: 下午10:48 2019/11/6
 * @Description: 线程池配置类
**/
@Configuration
public class ThreadPoolConfiguration {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }
//
//    @Value("${Thread.core}")
//    private Integer core;
//    @Value("${Thread.max}")
//    private Integer max;
//    @Value("${Thread.sec}")
//    private Integer sec;
//
//    /**
//     * 手动创建线程池
//     * @return
//     */
//    @Bean
//    public ThreadPoolExecutor threadPoolExecutor(){
//        return new ThreadPoolExecutor(core,
//                max,
//                sec,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>(),
//                (x)->{
//                    Thread thread=new Thread(x);
//                    thread.setName("Data-Upload-Thread");
//                    return thread;
//                },
//                new ThreadPoolExecutor.AbortPolicy());
//    }



}
