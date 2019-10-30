package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.PhoneService;
import com.zhouyi.business.core.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李秸康
 * @ClassNmae: PhoneInfoController
 * @Description: TODO
 * @date 2019/7/12 9:15
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/phone")
@Api(hidden = true)
public class PhoneInfoController {

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value="/parseXml")
    public Response<Object> parsePackets(String path){
        try {
            boolean flag=phoneService.parseXml(path);
            return ResponseUtil.getResponseInfo(flag);
        } catch (Exception e) {
            e.printStackTrace();
           return ResponseUtil.ntrError("发生异常:"+e.getMessage());
        }
    }



}
