package com.zhouyi.business.controller;
import com.zhouyi.business.config.CronConfiguration;
import com.zhouyi.business.runnable.UploadRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ScheduledFuture;

/**
 * @Author: first
 * @Date: 上午6:25 2019/11/8
 * @Description:  动态任务控制器
**/
@RestController
@Slf4j
@RequestMapping("/quartz/upload")
public class DynamicTaskController {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private CronConfiguration cronConfiguration;
    @Autowired
    private UploadRunnable uploadRunnable;

    private boolean upload_on_off=false;

    private ScheduledFuture<?> uploadFuture;


    /**
     * 开启上报省厅
     * @return
     */
    @RequestMapping(value = "/start")
    public String startCron(){
        if(upload_on_off==false){
            beginPacketAndUpload();
            log.info("-------数据上报任务启动----------");
            return "数据上报启动成功";
        }else{
            return "数据上报任务已是开启状态";
        }
    }


    /**
     * 关闭上报省厅
     */
    @RequestMapping(value = "/stop")
    public String stopCron(){
        if(uploadFuture!=null&&upload_on_off==true){
            uploadFuture.cancel(true);
            upload_on_off=false;
            log.info("------数据上报关闭成功");
            return "数据上报关闭成功";
        }else{
            return "数据上报已是关闭状态";
        }
    }

    public void beginPacketAndUpload() {
        uploadFuture=threadPoolTaskScheduler.schedule(uploadRunnable,x->new CronTrigger(cronConfiguration.getUploadCron()).nextExecutionTime(x));
        //表示已经开启了上传
        upload_on_off=true;
    }


}
