package com.zhouyi.business.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ftp配置类
 */
@Component
@ConfigurationProperties(prefix = "finger.ftp")
@Data
public class FtpConfig {
   private String user;
   private String password;
   private String ip;
   private int port;
   private String dir;
}
