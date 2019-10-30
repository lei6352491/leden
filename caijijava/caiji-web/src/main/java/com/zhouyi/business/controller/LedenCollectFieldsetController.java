package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectFieldset;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.vo.LedenCollectFieldsetVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/fieldset")
@Api(hidden = true)
public class LedenCollectFieldsetController {

    @Autowired
    private BaseService<LedenCollectFieldset, LedenCollectFieldsetVo> baseService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id")String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectFieldset ledenCollectFieldset){
        return baseService.saveData(ledenCollectFieldset);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectFieldsetVo ledenCollectFieldsetVo){
        LedenCollectFieldsetVo ledenCollectFieldsetVo1 = ledenCollectFieldsetVo;
        if (ledenCollectFieldsetVo == null){
            ledenCollectFieldsetVo1 = new LedenCollectFieldsetVo();
        }
        return baseService.findDataList(ledenCollectFieldsetVo1);
    }

    @RequestMapping(value = "/update")
    public Response update(@RequestBody LedenCollectFieldset ledenCollectCategoryNode){
        return baseService.updateData(ledenCollectCategoryNode);
    }

    @RequestMapping(value = "/delete/{id}")
    public Response delete(@PathVariable(value = "id") String id){
        return baseService.deleteData(id);
    }
}
