package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectLooks;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.vo.LedenCollectLooksVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/looks")
@Api(value = "人像接口",hidden = true)
public class LedenCollectLooksController {

    @Autowired
    private BaseService<LedenCollectLooks, LedenCollectLooksVo> baseService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectLooksVo ledenCollectLooksVo){
        LedenCollectLooksVo ledenCollectLooksVo1 = ledenCollectLooksVo;
        if (ledenCollectLooksVo == null){
            ledenCollectLooksVo1 = new LedenCollectLooksVo();
        }
        return baseService.findDataList(ledenCollectLooksVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectLooks ledenCollectLooks){
        return baseService.saveData(ledenCollectLooks);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectLooks ledenCollectLooks){
        return baseService.updateData(ledenCollectLooks);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }
}
