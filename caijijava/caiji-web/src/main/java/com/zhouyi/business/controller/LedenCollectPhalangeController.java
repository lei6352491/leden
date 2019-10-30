package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectPhalange;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.vo.LedenCollectPhalangeVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/phalange")
@Api(hidden = true)
public class LedenCollectPhalangeController {

    @Autowired
    private BaseService<LedenCollectPhalange, LedenCollectPhalangeVo> baseService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectPhalangeVo ledenCollectPhalangeVo){
        LedenCollectPhalangeVo ledenCollectPhalangeVo1 = ledenCollectPhalangeVo;
        if (ledenCollectPhalangeVo == null){
            ledenCollectPhalangeVo1 = new LedenCollectPhalangeVo();
        }
        return baseService.findDataList(ledenCollectPhalangeVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectPhalange ledenCollectPhalange){
        return baseService.saveData(ledenCollectPhalange);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectPhalange ledenCollectPhalange){
        return baseService.updateData(ledenCollectPhalange);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }

}
