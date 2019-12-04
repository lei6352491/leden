package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenUploadLogRequest;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenUploadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杜承旭
 * @ClassNmae: LedenUploadLogController
 * @Description: TODO
 * @date 2019/12/3 13:49
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/uploadLog")
public class LedenUploadLogController {

    @Autowired
    private LedenUploadLogService ledenUploadLogService;

    @RequestMapping(value = "/selectList")
    public Response selectList(@RequestBody LedenUploadLogRequest ledenUploadLogRequest){
        return ledenUploadLogService.selectList(ledenUploadLogRequest);
    }

    @RequestMapping(value = "/select/{id}")
    public Response findOne(@PathVariable(value = "id")String id){
        return ledenUploadLogService.findOne(id);
    }
}
