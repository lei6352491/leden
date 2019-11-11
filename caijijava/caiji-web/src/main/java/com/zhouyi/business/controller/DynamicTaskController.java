package com.zhouyi.business.controller;

import com.zhouyi.business.component.DataReportComponent;
import com.zhouyi.business.config.CronConfiguration;
import com.zhouyi.business.core.model.LedenEquipment;
import com.zhouyi.business.core.model.LedenUploadLog;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardPerson;
import com.zhouyi.business.core.service.LedenCollectPersonService;
import com.zhouyi.business.core.service.LedenEquipmentService;
import com.zhouyi.business.core.service.LedenUploadLogService;
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
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * @Author: first
 * @Date: 上午6:25 2019/11/8
 * @Description:  动态任务控制器
**/
@RestController
@Slf4j
@RequestMapping("/quartz/task")
public class DynamicTaskController {

    @Autowired
    private DataReportComponent dataReportComponent;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private LedenUploadLogService ledenUploadLogService;
    @Autowired
    private CronConfiguration cronConfiguration;
    @Autowired
    private UploadRunnable uploadRunnable;
    @Autowired
    private LedenEquipmentService ledenEquipmentService;

    private ScheduledFuture<?> future;


    private ScheduledFuture<?> uploadFuture;
    @RequestMapping(value = "/startUpload")
    public String startCron(){
        beginPacketAndUpload();
        System.out.println("-------数据上报任务启动----------");

        return "success";
    }



    @RequestMapping(value = "/stopUpload")
    public String stopCron(){
        if(uploadFuture!=null){
            uploadFuture.cancel(true);
        }

        System.out.println("------数据上报关闭成功");
        return "success";
    }

    public String beginPacketAndUpload() {


        uploadFuture=threadPoolTaskScheduler.schedule(uploadRunnable,x->new CronTrigger(cronConfiguration.getUploadCron()).nextExecutionTime(x));
        return "success";


    }

}
