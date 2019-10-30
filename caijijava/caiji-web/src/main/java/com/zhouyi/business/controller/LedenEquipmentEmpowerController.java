package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.EquipmentEmpowerRequest;
import com.zhouyi.business.core.model.LedenEquipmentEmpower;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenEquipmentEmpowerService;
import com.zhouyi.business.core.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentEmpowerController
 * @Description: TODO
 * @date 2019/7/5 15:40
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/equipment/empower")
@Api(hidden = true,description = "设备授权API接口")
public class LedenEquipmentEmpowerController {

    @Autowired
    private LedenEquipmentEmpowerService ledenEquipmentEmpowerService;


    /**
     * 根据设备编码查询设备授权信息
     * @param equipmentCode
     * @return
     */
    @RequestMapping(value="/getAuthorized/{equipmentCode}")
    public Response<Map<String,String>> getEmpowerInfoByEquipmentCode(@PathVariable String equipmentCode){
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenEquipmentEmpowerService.searchEmpowerByEquipmentCode(equipmentCode));
    }


    @RequestMapping(value = "/getAuthorized")
    public Response<Map<String,String>> getEmpowerInfoByEquipmentCode2(String equipmentCode){
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenEquipmentEmpowerService.searchEmpowerByEquipmentCode(equipmentCode));
    }

    /**
     * 根据设备编码查询设备已授权信息
     */
    @ApiOperation(value = "获取设备的授权信息",notes = "根据设备编码查询设备已授权信息")
    @RequestMapping(value = "/selectEquipmentEmpowerList")
    public Response<LedenEquipmentEmpower> selectEquipmentEmpowerList(@RequestBody LedenEquipmentEmpower ledenEquipmentEmpower){
        return ledenEquipmentEmpowerService.selectEquipmentEmpowerListByModel(ledenEquipmentEmpower);
    }

    /**
     * 更新设备授权信息
     * */
    @ApiOperation(value = "更新设备授权信息",notes = "根据设备编号和节点集更新设备的授权信息")
    @RequestMapping(value = "/updateEquipmentEmpowerList")
    public Response updateEquipmentEmpowerList(@RequestBody EquipmentEmpowerRequest equipmentEmpowerRequest){
        return ledenEquipmentEmpowerService.updateEquipmentEmpower(equipmentEmpowerRequest);
    }
}
