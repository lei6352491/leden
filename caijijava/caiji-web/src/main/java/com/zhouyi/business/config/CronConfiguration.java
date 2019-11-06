package com.zhouyi.business.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: first
 * @Date: 上午6:15 2019/11/6
 * @Description: Cron表达式的配置类
**/
@ConfigurationProperties(prefix = "task")
@Data
@Component
public class CronConfiguration {
    private String uploadCron;
}
