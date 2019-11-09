package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenEquipment;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenEquipmentVo;
import com.zhouyi.business.core.vo.LedenEquipmentVo2;

import java.util.List;
import java.util.Map;

/**
 * 设备信息业务接口
 */
public interface LedenEquipmentService {

    /**
     * 分页获取设别信息
     * @param conditions
     * @return
     */
    PageData<LedenEquipment> getLedenEquipmentPage(Map<String,Object> conditions);

    /**
     * 通过id获取设备信息
     * @param id
     * @return
     */
    LedenEquipment getLedenEquipmentById(Integer id);

    /**
     * 添加设备信息
     * @param ledenEquipment
     * @return
     */
    String addLedenEquipment(LedenEquipment ledenEquipment);

    /**
     * 修挂设备信息
     * @param ledenEquipment
     * @return
     */
    Boolean updateLedenEquipment(LedenEquipment ledenEquipment);

    /**
     * 删除设备信息
     * @param id
     * @return
     */
    Boolean removeLedenEquipmentById(Integer id);


    /**
     * 采集节点注册
     * @param ledenEquipment 采集信息对象
     * @return 设备编码
     */
    String collectNodeRegister(LedenEquipmentVo ledenEquipment);


    Response selectEquipmentListByData(LedenEquipmentVo2 ledenEquipmentVo2);

    Response selectEquipmentByEquipmentCode(String equipmentCode);


    LedenEquipment getEquipmentByEquipmentCode(String code);
}
