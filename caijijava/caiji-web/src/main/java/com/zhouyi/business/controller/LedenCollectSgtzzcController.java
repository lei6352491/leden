package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectSgtzzc;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.vo.LedenCollectSgtzzcVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sgtzzc")
@Api(hidden = true)
public class LedenCollectSgtzzcController {

    @Autowired
    private BaseService<LedenCollectSgtzzc, LedenCollectSgtzzcVo> baseService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectSgtzzcVo ledenCollectSgtzzcVo){
        LedenCollectSgtzzcVo ledenCollectSgtzzcVo1 = ledenCollectSgtzzcVo;
        if (ledenCollectSgtzzcVo == null){
            ledenCollectSgtzzcVo1 = new LedenCollectSgtzzcVo();
        }
        return baseService.findDataList(ledenCollectSgtzzcVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectSgtzzc ledenCollectSgtzzc){
        return baseService.saveData(ledenCollectSgtzzc);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectSgtzzc ledenCollectSgtzzc){
        return baseService.updateData(ledenCollectSgtzzc);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }
}
