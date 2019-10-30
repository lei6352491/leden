package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysBaseConf;
import com.zhouyi.business.core.service.SysBaseConfService;
import com.zhouyi.business.core.vo.SysBaseConfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody; import org.springframework.web.bind.annotation.RequestMapping; import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: BaseController
 * @Description: TODO
 * @date 2019/9/10 16:11
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/base")
public class BaseController {
    @Autowired
    private SysBaseConfService sysBaseConfService;

    @RequestMapping(value = "/getlist")
    public Response<Object> getSysBaseConfByModel(
                                        @RequestBody SysBaseConfVo sysBaseConfVo){
        List<SysBaseConf> baseConfs = sysBaseConfService.findSysBaseConfByModel(sysBaseConfVo);
        if (baseConfs == null){
            return null;
        }
        int total = sysBaseConfService.findTotal(sysBaseConfVo);
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("total",total);
        map.put("list",baseConfs);
        Response<Object> response = new Response<Object>();
        response.setData(map);
        return response;
    }
     @RequestMapping(value = "/get/{id}")
    public Response<SysBaseConf> getSysBaseConfById(@PathVariable String id){
        SysBaseConf baseConf = sysBaseConfService.findSysBaseConfById(id);
        Response<SysBaseConf> response = new Response<SysBaseConf>();
        response.setData(baseConf);
        return response;
    }

}
