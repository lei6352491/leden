package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectFourfinger;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.vo.LedenCollectFourfingerVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/fourfinger")
@Api(value = "四指接口",hidden = true)
public class LedenCollectFourfingerController {

    @Autowired
    private BaseService<LedenCollectFourfinger, LedenCollectFourfingerVo> baseService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectFourfingerVo ledenCollectFourfingerVo){
        LedenCollectFourfingerVo ledenCollectFourfingerVo1 = ledenCollectFourfingerVo;
        if (ledenCollectFourfingerVo == null){
            ledenCollectFourfingerVo1 = new LedenCollectFourfingerVo();
        }
        return baseService.findDataList(ledenCollectFourfingerVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectFourfinger ledenCollectFourfinger){
        return baseService.saveData(ledenCollectFourfinger);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectFourfinger ledenCollectFourfinger){
        return baseService.updateData(ledenCollectFourfinger);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }

}
