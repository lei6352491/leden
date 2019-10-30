package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectFullpalm;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.vo.LedenCollectFullpalmVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/fullpalm")
@Api(hidden = true)
public class LedenCollectFullpalmController {

    @Autowired
    private BaseService<LedenCollectFullpalm, LedenCollectFullpalmVo> baseService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectFullpalmVo ledenCollectFullpalmVo){
        LedenCollectFullpalmVo ledenCollectFullpalmVo1 = ledenCollectFullpalmVo;
        if (ledenCollectFullpalmVo == null){
            ledenCollectFullpalmVo1 = new LedenCollectFullpalmVo();
        }
        return baseService.findDataList(ledenCollectFullpalmVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectFullpalm ledenCollectFullpalm){
        return baseService.saveData(ledenCollectFullpalm);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectFullpalm ledenCollectFullpalm){
        return baseService.updateData(ledenCollectFullpalm);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }

}
