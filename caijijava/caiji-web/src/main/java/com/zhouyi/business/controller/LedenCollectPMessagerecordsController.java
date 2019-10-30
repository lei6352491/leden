package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectPMessagerecords;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectPMessagerecordsService;
import com.zhouyi.business.core.vo.LedenCollectPMessagerecordsVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/messagerecords")
@Api(hidden = true)
public class LedenCollectPMessagerecordsController {

    @Autowired
    private BaseService<LedenCollectPMessagerecords, LedenCollectPMessagerecordsVo> baseService;

    @Autowired
    private LedenCollectPMessagerecordsService ledenCollectPMessagerecordsService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectPMessagerecordsVo ledenCollectPMessagerecordsVo){
        LedenCollectPMessagerecordsVo ledenCollectPMessagerecordsVo1 = ledenCollectPMessagerecordsVo;
        if (ledenCollectPMessagerecordsVo == null){
            ledenCollectPMessagerecordsVo1 = new LedenCollectPMessagerecordsVo();
        }
        return baseService.findDataList(ledenCollectPMessagerecordsVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectPMessagerecords ledenCollectPMessagerecords){
        return baseService.saveData(ledenCollectPMessagerecords);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectPMessagerecords ledenCollectPMessagerecords){
        return baseService.updateData(ledenCollectPMessagerecords);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }

    @RequestMapping(value = "/select")
    public Response selectDataById(@RequestBody LedenCollectPMessagerecordsVo ledenCollectPMessagerecordsVo){
        return ledenCollectPMessagerecordsService.selectDataById(ledenCollectPMessagerecordsVo);
    }

}
