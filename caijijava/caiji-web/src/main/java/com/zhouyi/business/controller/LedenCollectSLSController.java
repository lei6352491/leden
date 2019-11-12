package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenCollectSLSService;
import com.zhouyi.business.core.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectSLSController
 * @Description: TODO
 * @date 2019/7/3 16:53
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/sls")
@Api(hidden = true)
public class LedenCollectSLSController {

    @Autowired
    private LedenCollectSLSService ledenCollectSLSService;


    /**
     * 解析xml入库接口
     * @param path
     * @return
     */
    @RequestMapping(value="/parseXml")
    public Response<Object> parseXml(String path){
        boolean flag= false;

        try {
            flag = ledenCollectSLSService.insertSignalement(path);
        } catch (AuthenticationException e) {
            return ResponseUtil.returnError(e.getReturnCode());
        } catch (XmlParseException e){
            return ResponseUtil.ntrError(e.getMessage());
        }

        return ResponseUtil.getResponseInfo(flag);
    }



}
