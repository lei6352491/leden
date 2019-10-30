package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenEquipment;
import com.zhouyi.business.core.model.LedenEquipmentParets;
import com.zhouyi.business.core.model.PageData;

import java.util.Map;

/**
 * 设备配件业务接口
 */
public interface LedenEquipmentParetsService {

    /**
     * 条件查询业务配件信息
     * @param conditions
     * @return
     */
    PageData<LedenEquipmentParets> getLedenEquipmentParetsPage(Map<String,Object> conditions);

    /**
     * 根据id获取设备配件信息
     * @param id
     * @return
     */
    LedenEquipmentParets getLedenEquipmentParetsById(Integer id);

    /**
     * 新增配件信息
     * @param ledenEquipmentParets
     * @return
     */
    Boolean addLedenEquipmentParets(LedenEquipmentParets ledenEquipmentParets);

    /**
     * 删除设备信息
     * @param id
     * @return
     */
    Boolean removeLedenEquipmentParetsById(Integer id);


    /**
     * 修改设备信息
     * @param ledenEquipmentParets
     * @return
     */
    Boolean  updateLedenEquipmentParets(LedenEquipmentParets ledenEquipmentParets);



}
