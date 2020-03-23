package com.zhouyi.business.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: first
 * @Date: 下午9:05 2019/12/25
 * @Description: 其他的控制器
**/

@RestController
public class OtherController {


    @Autowired
    FtpData ftpData;

    @RequestMapping(value = "/ftp_data")
    public FtpData getConfigFtpData(){
        return ftpData;
    }
}


@Component
@ConfigurationProperties(prefix = "finger.ftp")
@Data
class FtpData{
    @Value("${finger.ftp.ip}")
    private String host;
    private String port;
    private String user;
    private String password;
}
