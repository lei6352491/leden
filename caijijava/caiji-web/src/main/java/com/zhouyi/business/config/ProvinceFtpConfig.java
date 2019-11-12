package com.zhouyi.business.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: first
 * @Date: 上午9:49 2019/10/31
 * @Description: 省综ftp模块
**/
@ConfigurationProperties(prefix = "provinceComprehensive.ftp")
@Component
@Data
public class ProvinceFtpConfig {
    private String ip;
    private String port;
    private String targetDir;
    private String username;
    private String password;

    private String serverAddress;


    public String getServerAddress() {
        if(serverAddress==null){
           StringBuffer stringBuffer=new StringBuffer("http://");
           stringBuffer.append(ip);
           stringBuffer.append(":");
           stringBuffer.append("port");
        }
        return serverAddress;
    }
}
