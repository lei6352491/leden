package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenEquipmentEmpower;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenEquipmentEmpowerMapper extends BaseMapper<LedenEquipmentEmpower,String>{


    /**
     * 根据设备id查询设备的授权信息
     * @param equipmentCode
     * @return
     */
    List<LedenEquipmentEmpower> getEquipmentEmpowerByEquipmnetCode(String equipmentCode);

    List<LedenEquipmentEmpower> selectEquipmentEmpowerByEquipmentCode(String code);


    /**
     * 查询已经授权的节点信息
     * @param equipmentCode
     * @return
     */
    List<String> listEquipmentEmpowerdByEquipmentCode(String equipmentCode);

}