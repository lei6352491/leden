package com.zhouyi.business.controller;

import com.zhouyi.business.config.CronConfiguration;
import com.zhouyi.business.runnable.UploadRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@RestController
@Slf4j
@RequestMapping("/quartz/task")
public class DynamicTaskController {

    @Autowired
    private CronConfiguration cronConfiguration;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;


    @RequestMapping(value = "/startUpload")
    public String startCron(){
        future=threadPoolTaskScheduler.schedule(new UploadRunnable(), new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
               return new CronTrigger(cronConfiguration.getUploadCron()).nextExecutionTime(triggerContext);
            }
        });
        System.out.println("-------数据上报任务启动----------");

        return "success";
    }



    @RequestMapping(value = "/stopUpload")
    public String stopCron(){
        if(future!=null){
            future.cancel(true);
        }

        System.out.println("------数据上报关闭成功");
        return "success";
    }


}
