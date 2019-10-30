package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysBaseConf;
import com.zhouyi.business.core.service.SysBaseConfService;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.SysBaseConfVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/baseconf")
@Api(hidden = true)
public class SysBaseConfController {
    
    @Autowired
    private SysBaseConfService sysBaseConfService;
    



    @RequestMapping(value = "/save")
    public Response<Object> saveSysBaseConf(@RequestBody SysBaseConf sysBaseConf){
        sysBaseConfService.saveSysBaseConf(sysBaseConf);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }

    @RequestMapping(value = "/update")
    public Response<Object> updateSysBaseConf(@RequestBody SysBaseConf sysBaseConf){
        sysBaseConfService.updateSysBaseConf(sysBaseConf);
        return ResponseUtil.returnError(ReturnCode.SUCCESS);
    }

    @RequestMapping(value = "/delete/{id}")
    public Response<Object> deleteSysBaseConf(@PathVariable(value = "id")String id){
        sysBaseConfService.deleteSysBaseConfById(id);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }
}
