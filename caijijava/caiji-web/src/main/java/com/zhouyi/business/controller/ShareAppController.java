package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.LedenShareApp;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.ShareAppDetailDto;
import com.zhouyi.business.core.service.LedenShareAppService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.dto.ShareAppConditionsDto;
import com.zhouyi.business.dto.ShareAppInsertDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: ShareAppController
 * @Description: TODO 共享信息控制器
 * @date 2019/8/3 14:40
 * @Version 1.0
 **/
@Api(description = "共享信息相关接口列表")
@RequestMapping(value = "/api/share")
@RestController
public class ShareAppController {

    @Autowired
    private LedenShareAppService ledenShareAppService;


    /**
     * 分页获取条件信息
     * @param conditionsDto
     * @return
     */
    @ApiOperation(value = "分页查询共享信息")
    @RequestMapping(value = "/search/app",method = RequestMethod.POST)
    public Response<PageData<LedenShareApp>> listShareAppInfo(@RequestBody ShareAppConditionsDto conditionsDto){
        //封装入参条件
        Map<String,Object> conditions= MapUtils.objectTransferToMap(conditionsDto);
        PageData<LedenShareApp> pageData=ledenShareAppService.listAllShareApp(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }


    /**
     * 新增共享信息
     * @param shareAppInsertDto
     * @return
     */
    @ApiOperation(value = "新增共享信息")
    @RequestMapping(value = "/app",method = RequestMethod.POST)
    public Response<Object> addShareAppInfo(@RequestBody ShareAppInsertDto shareAppInsertDto){
        boolean flag = ledenShareAppService.addShareAppInfo(shareAppInsertDto);
        return ResponseUtil.getResponseInfo(flag);
    }


    @ApiOperation(value = "修改共享信息")
    @RequestMapping(value = "/app",method=RequestMethod.PUT)
    public Response<Object> updateShareAppInfo(@RequestBody ShareAppInsertDto shareAppInsertDto){
        boolean flag=ledenShareAppService.modifyShareAppInfo(shareAppInsertDto);
        return ResponseUtil.getResponseInfo(flag);
    }


    /**
     * 查询共享信息
     * @param pkId
     * @return
     */
    @ApiOperation(value = "查看共享信息（查询详情）")
    @RequestMapping(value = "/app/{pkId}",method = RequestMethod.GET)
    @ApiImplicitParam(value = "主键id",name = "pkId",paramType = "path")
    public Response<ShareAppDetailDto> getShareDetail(@PathVariable String pkId){
        ShareAppDetailDto data = ledenShareAppService.getShareAppInfoDetail(pkId);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,data);
    }

}
