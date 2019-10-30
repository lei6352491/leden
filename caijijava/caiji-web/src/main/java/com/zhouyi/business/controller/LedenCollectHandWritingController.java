package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenCollectHandWritingService;
import com.zhouyi.business.core.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectHandWritingController
 * @Description: 笔记处理器
 * @date 2019/7/4 16:24
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/handwriting")
@Api(description = "笔记接口",hidden = true)
public class LedenCollectHandWritingController {

    @Autowired
    private LedenCollectHandWritingService ledenCollectHandWritingService;

    @RequestMapping(value="/parseXml")
    public Response inputHandWritingXml(String path){
        boolean flag= false;
        try {
            flag = ledenCollectHandWritingService.inputHandWirtingXml(path);
        } catch (XmlParseException e) {
            return ResponseUtil.ntrError(e.getMessage());
        } catch (AuthenticationException e){
            return ResponseUtil.returnError(e.getReturnCode());
        }
        return ResponseUtil.getResponseInfo(flag);
    }

    /**
     *根据人员编号查询笔记信息
     */
    @RequestMapping(value = "/get/{id}")
    public Response selectHandWriting(@PathVariable String id){
        return ledenCollectHandWritingService.selectHandWritingById(id);
    }
}
