package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.dto.SysDataLogConditionsDto;
import com.zhouyi.business.core.model.SysLogData;
import com.zhouyi.business.core.service.SysLogDataService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysLogDataController
 * @Description: 数据日志控制器
 * @date 2019/6/21 10:47
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/sysDataLog")
@Api(description = "数据日志API")
public class SysLogDataController {

    @Autowired
    private SysLogDataService sysLogDataService;


    /**
     * 根据主键id获取数据日志信息
     * @param pkId
     * @return
     */
    @RequestMapping(value="/getSysLogData/{pkId}",method = RequestMethod.GET)
    @ApiOperation(value = "获取单个系统日期信息",hidden = true)
    @ApiImplicitParam(value = "主键id",name = "pkId",paramType = "path")
    public Response<SysLogData> getSysLogData(@PathVariable String pkId){
        Response<SysLogData> sysLogDataResponse=new Response<SysLogData>();
        SysLogData sysLogData=sysLogDataService.searchSysLogDataByPkId(pkId);
        sysLogDataResponse.setData(sysLogData);
        return sysLogDataResponse;
    }


    /**
     * 添加数据日志信息
     * @param sysLogData
     * @return
     */
    @RequestMapping(value="/addSysLogData")
    @ApiOperation(value = "添加日志",hidden = true)
    public Response<Object> addSysLogData(@RequestBody SysLogData sysLogData){
        boolean result=sysLogDataService.addSysLogDataSelective(sysLogData);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 删除数据日志信息
     * @param pkId
     * @return
     */
    @RequestMapping(value="/deleteSysLogData/{pkId}")
    @ApiOperation(hidden = true,value = "删除日志")
    public Response<Object> removeSysLogData(@PathVariable String pkId){
        boolean result=sysLogDataService.removeSysLogData(pkId);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 修改数据日志信息
     * @param sysLogData
     * @return
     */
    @RequestMapping(value="/updateSysLogData")
    @ApiOperation(value = "修改日志",hidden = true)
    public Response<Object> modifySysLogData(@RequestBody SysLogData sysLogData){
        boolean result=sysLogDataService.modifySysLogDataSelective(sysLogData);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 分页显示数据日志信息
     * @return
     */
    @RequestMapping(value="/list/sys_log_data",method = RequestMethod.POST)
    @ApiOperation(value = "日志列表")
//    @ApiImplicitParams(
//            {
//            @ApiImplicitParam(value = "对象姓名",name = "userName",paramType = "query"),
//            @ApiImplicitParam(value = "用户姓名",name = "personName",paramType = "query"),
//            @ApiImplicitParam(value = "IP地址",name = "addreIp",paramType="query")
//            }
//    )
    public Response<PageData<SysLogData>> listSysLogData(@RequestBody SysDataLogConditionsDto sysDataLogConditionsDto){
        Map<String,Object> conditions=MapUtils.objectTransferToMap(sysDataLogConditionsDto);
        PageData<SysLogData> pageData=sysLogDataService.searchSysLogDataPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }


}
