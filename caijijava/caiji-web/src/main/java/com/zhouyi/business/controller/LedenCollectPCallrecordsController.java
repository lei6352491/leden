package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectPCallrecords;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectPCallrecordsService;
import com.zhouyi.business.core.vo.LedenCollectPCallrecordsVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/callrecords")
@Api(hidden = true)
public class LedenCollectPCallrecordsController {
    @Autowired
    private BaseService<LedenCollectPCallrecords, LedenCollectPCallrecordsVo> baseService;

    @Autowired
    private LedenCollectPCallrecordsService ledenCollectPCallrecordsService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectPCallrecordsVo ledenCollectPCallrecordsVo){
        LedenCollectPCallrecordsVo ledenCollectPCallrecordsVo1 = ledenCollectPCallrecordsVo;
        if (ledenCollectPCallrecordsVo == null){
            ledenCollectPCallrecordsVo1 = new LedenCollectPCallrecordsVo();
        }
        return baseService.findDataList(ledenCollectPCallrecordsVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectPCallrecords ledenCollectPCallrecords){
        return baseService.saveData(ledenCollectPCallrecords);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectPCallrecords ledenCollectPCallrecords){
        return baseService.updateData(ledenCollectPCallrecords);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }

    @RequestMapping(value = "/select")
    public Response selectDataById(@RequestBody LedenCollectPCallrecordsVo ledenCollectPCallrecordsVo){
        return ledenCollectPCallrecordsService.selectDataById(ledenCollectPCallrecordsVo);
    }

}
