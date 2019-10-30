package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysUserTransfer;
import com.zhouyi.business.core.service.SysUserTransferService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.dto.SysUserTransferDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysUserTransferController
 * @Description: TODO
 * @date 2019/6/21 10:01
 * @Version 1.0
 **/
@RequestMapping(value="/api/sysUserTransfer")
@RestController
@Api(hidden = true)
public class SysUserTransferController {

    @Autowired
    private SysUserTransferService sysUserTransferService;


    /**
     * 获取人员调离记录
     * @param transferCode 记录编码
     * @return
     */
    @RequestMapping(value="/getSysUserTransfer/{transferCode}")
    public Response<SysUserTransfer> getSysUserTransferByCode(@PathVariable String transferCode){
        SysUserTransfer sysUserTransfer=sysUserTransferService.searchSysUserTransfer(transferCode);
        Response<SysUserTransfer> sysUserTransferResponse=new Response<SysUserTransfer>();
        sysUserTransferResponse.setData(sysUserTransfer);
        return sysUserTransferResponse;
    }


    /**
     * 新增调离记录
     * @param sysUserTransfer 调离信息对象
     * @return
     */
    @RequestMapping(value="/addSysUserTransfer")
    public Response<Object> addSysUserTransfer(@RequestBody SysUserTransfer sysUserTransfer){
        boolean result=sysUserTransferService.addSysUserTransferSelective(sysUserTransfer);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 删除用户调离记录信息
     * @param transferCode
     * @return
     */
    @RequestMapping(value="/deleteSysUserTransfer/{transferCode}")
    public Response<Object> removeSysUserTransfer(@PathVariable String transferCode){
        boolean result=sysUserTransferService.removeByKey(transferCode);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 修改用户调离记录
     * @param sysUserTransfer
     * @return
     */
    @RequestMapping(value="/updateSysUserTransfer")
    public Response<Object> updateSysUserTransfer(@RequestBody SysUserTransfer sysUserTransfer){
        boolean result=sysUserTransferService.modifySysUserTransferSelective(sysUserTransfer);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 分页获取用户调离记录信息
     * @param sysUserTransferDto
     * @return
     */
    @RequestMapping(value="/listSysUserTransfer")
    public Response<PageData<SysUserTransfer>> listSysUserTransfer(SysUserTransferDto sysUserTransferDto){
        Map<String,Object> conditions= MapUtils.setPageConditions(sysUserTransferDto.getpNo(),sysUserTransferDto.getpSize());
        PageData<SysUserTransfer> pageData=sysUserTransferService.searchSysUserTransferPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }
}
