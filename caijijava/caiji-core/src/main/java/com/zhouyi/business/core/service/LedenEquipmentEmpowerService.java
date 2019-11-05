package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.EquipmentEmpowerRequest;
import com.zhouyi.business.core.model.LedenEquipmentEmpower;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.EmpowerNodeVo;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentEmpowerService
 * @Description: 授权信息 接口
 * @date 2019/7/5 15:34
 * @Version 1.0
 **/
public interface LedenEquipmentEmpowerService {

    /**
     * 根据设备编码查询设备的授权信息
     * @param equipmentCode
     * @return
     */
    Map<String,String> searchEmpowerByEquipmentCode(String equipmentCode);

    Response<LedenEquipmentEmpower> selectEquipmentEmpowerListByModel(LedenEquipmentEmpower ledenEquipmentEmpower);

    Response updateEquipmentEmpower(EquipmentEmpowerRequest equipmentEmpowerRequest);

    /**
     * 通过设备id获取已授权的节点信息
     * @param equipmentId
     * @return
     */
    List<String> searchEmpwerdNodeSign(String equipmentId);
}
