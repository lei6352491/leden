package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysLogOperation;
import com.zhouyi.business.core.service.SysLogOperationService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.dto.SysLogOperationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysLogOperationController
 * @Description: 系统日志控制器
 * @date 2019/6/21 10:59
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/sysLogOperation")
@Api(description = "系统操作日志API")
public class SysLogOperationController {

    @Autowired
    private SysLogOperationService sysLogOperationService;


    /**
     * 获取系统操作记录信息
     * @param pkId 记录主键id
     * @return
     */
    @RequestMapping(value="/getSysLogOperation/{pkId}",method = RequestMethod.GET)
    @ApiOperation(value = "根据主键id获取某条记录")
    @ApiImplicitParam(value = "主键id",name = "pkId",paramType = "path")
    public Response<SysLogOperation> getSysLogOperation(@PathVariable String pkId){
        Response<SysLogOperation> sysLogOperationResponse=new Response<SysLogOperation>();
        SysLogOperation sysLogOperation=sysLogOperationService.searchSysLogOperation(pkId);
        sysLogOperationResponse.setData(sysLogOperation);
        return sysLogOperationResponse;
    }


    /**
     * 修改系统日志记录
     * @param sysLogOperation
     * @return
     */
    @RequestMapping(value="/updateSysLogOperation",method=RequestMethod.PUT)
    @ApiOperation(value = "修改日志记录")
    public Response<Object> modifySysLogOperation(@RequestBody SysLogOperation sysLogOperation){
        boolean result=sysLogOperationService.modifySysLogOperationSelective(sysLogOperation);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 添加系统日志记录
     * @param sysLogOperation
     * @return
     */
    @RequestMapping(value="/addSysLogOperation",method=RequestMethod.POST)
    @ApiOperation(value = "添加系统日志记录")
    public Response<Object> addSysLogOperation(@RequestBody SysLogOperation sysLogOperation){
        boolean result=sysLogOperationService.addSysLogOperationSelective(sysLogOperation);
        return ResponseUtil.getResponseInfo(result);
    }

    /**
     * 删除系统日志记录
     * @param pkId
     * @return
     */
    @ApiOperation(value = "删除系统日期功能",notes = "物理删除")
    @ApiImplicitParam(value = "主键id",name = "pkId",paramType = "path")
    @RequestMapping(value="/deleteSysLogOperation/{pkId}",method=RequestMethod.DELETE)
    public Response<Object> removeSysLogOperation(@PathVariable String pkId){
        boolean result=sysLogOperationService.removeSysLogOperationById(pkId);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 获取分页对象接口
     * @param sysLogOperationDto
     * @return
     */
    @ApiOperation(value = "分页查询日志")
    @RequestMapping(value="/listSysLogOperation",produces = {"application/json"},method=RequestMethod.POST)
    public Response<PageData<SysLogOperation>> listSysLogOperations(@RequestBody SysLogOperationDto sysLogOperationDto){
        Map<String,Object> conditions= MapUtils.objectTransferToMap(sysLogOperationDto);

        PageData<SysLogOperation> pageData=sysLogOperationService.searchSysLogOperationPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }
}
