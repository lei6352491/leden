package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectPalm;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectPalmService;
import com.zhouyi.business.core.vo.LedenCollectPalmVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/palm")
@Api(hidden = true)
@Slf4j
public class LedenCollectPalmController {

    @Autowired
    private BaseService<LedenCollectPalm, LedenCollectPalmVo> baseService;

    @Autowired
    private LedenCollectPalmService ledenCollectPalmService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectPalmVo ledenCollectPalmVo){
        LedenCollectPalmVo ledenCollectPalmVo1 = ledenCollectPalmVo;
        if (ledenCollectPalmVo == null){
            ledenCollectPalmVo1 = new LedenCollectPalmVo();
        }
        return baseService.findDataList(ledenCollectPalmVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectPalm ledenCollectPalm){
        return baseService.saveData(ledenCollectPalm);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectPalm ledenCollectPalm){
        return baseService.updateData(ledenCollectPalm);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }

    /**
     * 根据人员编号查询掌纹及四指指纹信息
     * */
    @RequestMapping(value = "/getpalm/{id}")
    public Response selectPalmOrFourFingerByPersonCode(@PathVariable String id){
        log.info("开始查询掌纹数据");
        return ledenCollectPalmService.selectPalmOrFourFingerByPersonCode(id);
    }
}
