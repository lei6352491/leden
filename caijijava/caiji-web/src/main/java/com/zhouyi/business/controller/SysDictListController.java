package com.zhouyi.business.controller;

import com.zhouyi.business.component.ApplicationRunner;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysDictList;
import com.zhouyi.business.core.service.SysDictListService;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.SysDictListVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/dictlist")
@Api(hidden = true)
public class SysDictListController {

    @Autowired
    private SysDictListService sysDictListService;


    @RequestMapping(value = "/get/{id}")
    public Response<SysDictList> getSysDictListById(@PathVariable(value = "id") String id){
        List<SysDictList> dictList = sysDictListService.findSysDictListById(id);
        if (dictList == null){
            return null;
        }
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,dictList);
    }

    @RequestMapping(value = "/getList")
    public Response<Object> getSysDictListList(
                                        @RequestBody SysDictListVo sysDictListVo){
        List<SysDictList> dictListList = sysDictListService.findSysDiceListByModel(sysDictListVo);
        if (dictListList == null){
            return null;
        }
        int total = sysDictListService.findTotal(sysDictListVo);
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("total",total);
        map.put("list",dictListList);

        Response<Object> response = new Response<Object>();
        response.setData(map);
        return response;
    }

    @RequestMapping(value = "/save")
    public Response<Object> saveSysDictList(@RequestBody SysDictList sysDictList){
        sysDictListService.saveSysDictList(sysDictList);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }

    @RequestMapping(value = "/update")
    public Response<Object> updateSysDictList(@RequestBody SysDictList sysDictList){
        sysDictListService.updateSysDictList(sysDictList);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }

    @RequestMapping(value = "/delete/{id}")
    public Response<Object> deleteSysDictList(@PathVariable(value = "id")String id){
        sysDictListService.deleteSysDictList(id);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }

    @RequestMapping(value = "testlist")
    public Response<List<SysDictList>> testList(@RequestBody SysDictListVo sysDictListVo){
        List<SysDictList> dictLists = sysDictListService.testList(sysDictListVo);
        Response<List<SysDictList>> response = new Response<List<SysDictList>>();
        response.setData(dictLists);
        return response;
    }

    @RequestMapping(value = "/dictResponse")
    public Response<List<SysDictList>> dictResponse(){
        Map map = ApplicationRunner.map;
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
    }
}
