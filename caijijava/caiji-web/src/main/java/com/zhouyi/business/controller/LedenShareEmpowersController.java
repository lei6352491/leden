package com.zhouyi.business.controller;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.LedenShareApp;
import com.zhouyi.business.core.model.LedenShareEmpowers;
import com.zhouyi.business.core.model.NodeEmpowersDto;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenShareAppService;
import com.zhouyi.business.core.service.LedenShareEmpowersService;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.dto.EmpowersNodeDto;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 李秸康
 * @ClassNmae: LedenShareEmpowersController
 * @Description: TODO 共享授权接口
 * @date 2019/8/6 16:50
 * @Version 1.0
 **/
@Api(description = "共享授权接口列表")
@RequestMapping(value = "/api/share/empower")
@RestController
public class LedenShareEmpowersController {

    @Autowired
    private LedenShareEmpowersService ledenShareEmpowersService;

    @ApiOperation(value = "查询授权列表信息")
    @RequestMapping(value = "/app",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appSign",paramType = "query",value = "应用标识"),
            @ApiImplicitParam(name = "appUrl",paramType = "query",value = "系统地址")
    })
    public Response<List<LedenShareApp>> listAppEmpowersByConditions(
            @RequestParam(value = "appSign",required = false) String appSign,
            @RequestParam(value = "appUrl",required = false) String appUrl
    ){
        Map<String,Object> conditions=new HashMap<>();
        conditions.put("appSign",appSign);
        conditions.put("appUrl",appUrl);
        List<LedenShareApp> data=ledenShareEmpowersService.listShareEmpowersByConditions(conditions);



        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,data);




    }



    @ApiOperation(value = "修改授权信息")
    @RequestMapping(value = "/app",method = RequestMethod.PUT)
    public Response<Object> modifyEmpowers(
           @RequestBody NodeEmpowersDto[] nodeEmpowersDtos
            ){

        //封装授权信息对象
        List<LedenShareEmpowers> empowers=Arrays.stream(nodeEmpowersDtos).map(x->x.getLedenShareEmpowers()).collect(Collectors.toList());
        empowers.forEach(x->{
            if(x.isMultiple()==true) {
                x.setIssue(true);
                x.setRecive(true);
                x.setShareType("1");
            }else{
                x.setShareType("0");
            }
        });

        //进行授权
        ledenShareEmpowersService.changAppEmpowers(empowers);
        return ResponseUtil.getResponseInfo(true);
    }
}
