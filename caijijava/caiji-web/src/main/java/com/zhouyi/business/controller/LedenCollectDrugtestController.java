package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectDrugtest;
import com.zhouyi.business.core.model.LedenCollectSign;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.vo.LedenCollectDrugtestVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/drugtest")
@Api(hidden = true)
public class LedenCollectDrugtestController {

    @Autowired
    private BaseService<LedenCollectDrugtest, LedenCollectDrugtestVo> baseService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectDrugtestVo ledenCollectDrugtestVo){
        LedenCollectDrugtestVo ledenCollectDrugtestVo1 = ledenCollectDrugtestVo;
        if (ledenCollectDrugtestVo == null){
            ledenCollectDrugtestVo1 = new LedenCollectDrugtestVo();
        }
        Response<Map<String, Object>> dataList = baseService.findDataList(ledenCollectDrugtestVo1);
        Map<String, Object> map = dataList.getData();
        List<LedenCollectDrugtest> list = (List<LedenCollectDrugtest>)map.get("list");
        list.stream().forEach((s)->{
            s.setXdzp(new String(s.getXdjctp()));
            s.setXdjctp(null);
        });
        return dataList;
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectDrugtest ledenCollectDrugtest){
        return baseService.saveData(ledenCollectDrugtest);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectDrugtest ledenCollectDrugtest){
        return baseService.updateData(ledenCollectDrugtest);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }


}
