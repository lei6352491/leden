package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectSign;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.vo.LedenCollectSignVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/sign")
@Api(hidden = true)
public class LedenCollectSignController {

    @Autowired
    private BaseService<LedenCollectSign, LedenCollectSignVo> baseService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectSignVo ledenCollectSignVo){
        LedenCollectSignVo ledenCollectSignVo1 = ledenCollectSignVo;
        if (ledenCollectSignVo == null){
            ledenCollectSignVo1 = new LedenCollectSignVo();
        }
        Response<Map<String, Object>> dataList = baseService.findDataList(ledenCollectSignVo1);
        Map<String,Object> map = dataList.getData();
        Object object = map.get("list");
        List<LedenCollectSign> list = null;
        if (object != null){
            list = (List<LedenCollectSign>) object;
        }
        list.stream().forEach(s->{
            s.setTzzp(new String(s.getTstzZp()));
            s.setTstzZp(null);
        });
        return dataList;
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectSign ledenCollectSign){
        return baseService.saveData(ledenCollectSign);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectSign ledenCollectSign){
        return baseService.updateData(ledenCollectSign);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }

}
