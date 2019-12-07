package com.zhouyi.business.controller;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.utils.HttpUtil;
import com.zhouyi.business.core.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.core.internal.jobs.ObjectMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: first
 * @Date: 上午10:37 2019/11/30
 * @Description: 公安部接口调用
**/
@RestController
@Slf4j
public class PublicSecurityController {

    @Value("${publicSecurity.provinceId}")
    private String provinceId;
    @Value("${publicSecurity.serviceType}")
    private String serviceType;
    @Value("${publicSecurity.ip}")
    private String ip;
    @Value("${publicSecurity.port}")
    private String port;
    @Value("${publicSecurity.url}")
    private String url;


    /**
     * 中转查询数据（公安部）
     * @param targetIDCard 目标身份证好
     * @param userId 用户警号
     * @param unitCode 用户单位编码
     * @param userIDCard 用户身份证号码
     * @param userName 用户姓名
     * @return
     */
    @RequestMapping("/public_security/person")
    public Map transitData(@RequestParam String targetIDCard,
                              @RequestParam String userId,
                              @RequestParam String unitCode,
                              @RequestParam String userIDCard,
                              @RequestParam String userName){
        StringBuffer url=new StringBuffer("http://").append(ip)
                .append(":").append(port).append(this.url);

        StringBuffer targetIDCardBuffer=new StringBuffer("{GMSFHM:'").append(targetIDCard).append("'}");
        Map<String,String> params=new HashMap<String, String>(7){{
            put("provinceId",provinceId);
            put("serviceType",serviceType);
            put("strCondition",targetIDCardBuffer.toString());
            put("userId",userId);
            put("userDept",unitCode);
            put("userSfzh",userIDCard);
            put("userName",userName);
        }};


        //调用接口
        try {
            ResponseVo responseVo = HttpUtil.sendPostByform(url.toString(), params);
            return (Map)JSON.parse(responseVo.data);
        } catch (IOException e) {
            log.error("网络异常");
            e.printStackTrace();
        }
        return null;



    }




}

