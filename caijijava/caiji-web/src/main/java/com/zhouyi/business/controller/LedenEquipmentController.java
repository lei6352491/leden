package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.model.LedenEquipment;
import com.zhouyi.business.core.model.LedenEquipmentResult;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenEquipmentService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenEquipmentVo;
import com.zhouyi.business.core.vo.LedenEquipmentVo2;
import com.zhouyi.business.dto.EquipmentListDto;
import com.zhouyi.business.dto.LedenEquipmentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentController
 * @Description: TODO
 * @date 2019/6/25 10:36
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/equipment")
@Api(description = "接入管理（设备管理）")
public class LedenEquipmentController {

    @Autowired
    private LedenEquipmentService ledenEquipmentService;


    /**
     * 分页获取设备信息
     * @param ledenEquipmentDto
     * @return
     */
    @RequestMapping(value="/listEquipment",method= RequestMethod.POST)
    @ApiOperation(value = "列表查询接入信息")
    public Response<PageData<LedenEquipment>> listEquipments(@RequestBody EquipmentListDto ledenEquipmentDto){
        Map<String,Object> conditions= MapUtils.objectTransferToMap(ledenEquipmentDto);
        PageData<LedenEquipment> pageData=ledenEquipmentService.getLedenEquipmentPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }


    /**
     * 更新设备信息
     * @param ledenEquipmentDto
     * @return
     */

    @RequestMapping(value="/updateEquipment",method=RequestMethod.PUT)
    @ApiOperation(value = "更新接入信息")
    public Response<Object> modifyEquipments(@RequestBody LedenEquipmentDto ledenEquipmentDto){
        LedenEquipment ledenEquipment=new LedenEquipment();
        BeanUtils.copyProperties(ledenEquipmentDto,ledenEquipment);
        Boolean result = ledenEquipmentService.updateLedenEquipment(ledenEquipment);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 添加设备信息
     * @param ledenEquipmentDto
     * @return
     */
    @RequestMapping(value="/insertEquipment",method=RequestMethod.POST)
    @ApiOperation(value = "添加接入信息")
    public Response<Object> addEquipments(@RequestBody LedenEquipmentDto ledenEquipmentDto){
        LedenEquipment ledenEquipment=transferObject(ledenEquipmentDto);
        String equipmentCode = ledenEquipmentService.addLedenEquipment(ledenEquipment);
        if (StringUtils.isEmpty(equipmentCode)){
            return ResponseUtil.getResponseInfo(false);
        }else {
            return ResponseUtil.getResponseInfo(true);
        }
    }


    /**
     * 删除设备信息
     * @param id
     * @return
     */
    @RequestMapping(value="/delEquipment/{id}",method=RequestMethod.DELETE)
    @ApiOperation(value = "删除接入信息")
    public Response<Object> delEquipment(@PathVariable Integer id){
        Boolean result=ledenEquipmentService.removeLedenEquipmentById(id);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 根据id获取设备信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getEquipment/{id}",method=RequestMethod.GET)
    @ApiOperation(value = "根据id获取接入信息")
    public Response<LedenEquipment> getEquipmentById(@PathVariable Integer id){
        LedenEquipment ledenEquipment=ledenEquipmentService.getLedenEquipmentById(id);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenEquipment);
    }


    /**
     * 对象转换方法
     * @param ledenEquipmentDto
     * @return
     */
    private LedenEquipment transferObject(LedenEquipmentDto ledenEquipmentDto){
        LedenEquipment ledenEquipment=new LedenEquipment();
        BeanUtils.copyProperties(ledenEquipmentDto,ledenEquipment);
        return ledenEquipment;
    }


    /**
     * 注册设备接口
     * @param ledenEquipmentVo
     * @return
     */
    @ApiOperation(value = "接入注册")
    @RequestMapping(value="/applyRegisterClient",method=RequestMethod.POST)
    public Response<String> collectNodeRegister(@RequestBody LedenEquipmentVo ledenEquipmentVo){
            ledenEquipmentVo.setPkId(UUID.randomUUID().toString().substring(0,32));
            String equipmentCode=ledenEquipmentService.collectNodeRegister(ledenEquipmentVo);
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,equipmentCode);
    }

    /**
     * 查询设备接入注册信息列表
     * */
    @ApiOperation(value = "查询接入注册列表")
    @RequestMapping(value="/selectEquipmentList",method=RequestMethod.POST)
    public Response selectEquipmentListByData(@RequestBody LedenEquipmentVo2 ledenEquipmentVo2) {
        return ledenEquipmentService.selectEquipmentListByData(ledenEquipmentVo2);
    }



}
