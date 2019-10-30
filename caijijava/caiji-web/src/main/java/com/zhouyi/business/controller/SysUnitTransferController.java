package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysUnitTransfer;
import com.zhouyi.business.core.service.SysUnitTransferService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.dto.SysUnitTransferDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysUnitTransferController
 * @Description: 用户调离记录控制器
 * @date 2019/6/21 9:12
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/unitTransfer")
@Api(hidden = true)
public class SysUnitTransferController {

    @Autowired
    private SysUnitTransferService sysUnitTransferService;


    /**
     * 根据调离记录编码获取部门调离记录
     * @param transferCode 记录编码
     * @return 记录对象
     */
    @RequestMapping(value="/getSysUnitTransfer/{transferCode}")
    public Response<SysUnitTransfer> getSysUnitTransferByCode(@PathVariable String transferCode){
        SysUnitTransfer sysUnitTransfer=sysUnitTransferService.searchTransferByPrimaryKey(transferCode);
        Response<SysUnitTransfer> responseData=new Response<SysUnitTransfer>();
        responseData.setData(sysUnitTransfer);
        return responseData;
    }


    /**
     * 新增部门调离记录
     * @param sysUnitTransfer
     * @return
     */
    @RequestMapping(value="/addSysUnitTransfer")
    public Response<Object> addUnitTransfer(@RequestBody SysUnitTransfer sysUnitTransfer){
        boolean result=sysUnitTransferService.addSysUnitTransferSelective(sysUnitTransfer);
        return ResponseUtil.getResponseInfo(result);
    }

    /**
     * 更新部门调离记录
     * @param sysUnitTransfer
     * @return
     */
    @RequestMapping(value = "/updateSysUnitTransfer")
    public Response<Object> moidfyUnitTransfer(@RequestBody SysUnitTransfer sysUnitTransfer){
        boolean result=sysUnitTransferService.modifyByPrimaryKeySelective(sysUnitTransfer);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 根据部门调离记录编码删除调离记录
     * @param unitCode
     * @return
     */
    @RequestMapping(value="/deleteSysUnitTransfer/{unitCode}")
    public Response<Object> removeUnitTransfer(@PathVariable String unitCode){
        boolean result=sysUnitTransferService.removeByPrimaryKey(unitCode);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 获取分页数据
     * @param sysUnitTransferDto
     * @return
     */
    @RequestMapping(value = "/listUnitTransfer")
    public Response<PageData<SysUnitTransfer>> getSysUnitOperationByConditions(@RequestBody SysUnitTransferDto sysUnitTransferDto){
        Map<String,Object> conditions= MapUtils.setPageConditions(sysUnitTransferDto.getpNo(),sysUnitTransferDto.getpSize());
        PageData<SysUnitTransfer> pageData=sysUnitTransferService.searchUnitTransfersPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }


}
