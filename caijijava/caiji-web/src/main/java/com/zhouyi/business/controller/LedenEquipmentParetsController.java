package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.LedenEquipmentParets;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenEquipmentParetsService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ParseUtil;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.dto.LedenEquipmentParetsDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentParetsController
 * @Description: 设备配置信息控制器
 * @date 2019/6/25 10:49
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/equipmentParets")
@Api(hidden = true)
public class LedenEquipmentParetsController {

    @Autowired
    private LedenEquipmentParetsService ledenEquipmentParetsService;

    /**
     * 分页查询设备配件信息
     * @param ledenEquipmentParetsDto
     * @return
     */
    @RequestMapping(value="/listEquipmentParets")
    public Response<PageData<LedenEquipmentParets>> listEquipmentParets(@RequestBody LedenEquipmentParetsDto ledenEquipmentParetsDto){
        Map<String,Object> conditions= MapUtils.setPageConditions(ledenEquipmentParetsDto.getpNo(),ledenEquipmentParetsDto.getpSize());
        PageData<LedenEquipmentParets> pageData=ledenEquipmentParetsService.getLedenEquipmentParetsPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }

    /**
     * 添加设备配件信息
     * @param ledenEquipmentParetsDto
     * @return
     */
    @RequestMapping(value="/insertEquipmentParets")
    public Response<Object> insertEquipmentParets(@RequestBody LedenEquipmentParetsDto ledenEquipmentParetsDto){
        LedenEquipmentParets ledenEquipmentParets= new LedenEquipmentParets();
        ParseUtil.parseObject(ledenEquipmentParetsDto,ledenEquipmentParets);
        boolean result=ledenEquipmentParetsService.addLedenEquipmentParets(ledenEquipmentParets);
        return ResponseUtil.getResponseInfo(result);
    }

    /**
     * 更新设备配件信息
     * @param ledenEquipmentParetsDto
     * @return
     */
    @RequestMapping(value="/updateEquipmentParets")
    public Response<Object> updateEquipmentParets(@RequestBody LedenEquipmentParetsDto ledenEquipmentParetsDto){
        LedenEquipmentParets ledenEquipmentParets=new LedenEquipmentParets();
        ParseUtil.parseObject(ledenEquipmentParetsDto,ledenEquipmentParets);
        boolean result=ledenEquipmentParetsService.updateLedenEquipmentParets(ledenEquipmentParets);
        return ResponseUtil.getResponseInfo(result);
    }

    /**
     * 删除设备信息
     * @param id
     * @return
     */
    @RequestMapping(value="/delEquipmentParets/{id}")
    public Response<Object> deleteEquipmentParets(@PathVariable Integer id){
        Boolean result=ledenEquipmentParetsService.removeLedenEquipmentParetsById(id);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 根据id获取设备配件信息
     * @param id
     * @return
     */
    @RequestMapping(value="/getEquipmentParets/{id}")
    public Response<LedenEquipmentParets> getEquipmentParetsById(@PathVariable Integer id){
        LedenEquipmentParets ledenEquipmentParets=ledenEquipmentParetsService.getLedenEquipmentParetsById(id);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenEquipmentParets);
    }

}
