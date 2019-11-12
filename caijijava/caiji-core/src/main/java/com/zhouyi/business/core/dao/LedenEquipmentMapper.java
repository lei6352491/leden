package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenEquipment;
import com.zhouyi.business.core.model.LedenEquipmentResult;
import com.zhouyi.business.core.vo.LedenEquipmentVo2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenEquipmentMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(LedenEquipment record);

    int insertSelective(LedenEquipment record);

    LedenEquipment selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(LedenEquipment record);

    int updateByPrimaryKey(LedenEquipment record);

    List<LedenEquipment> listLedenEquipmentsByConditios(Map<String,Object> conditions);

    int getLedenEquipmentCountByConditions(Map<String,Object> conditions);

    /**
     * 根据部门编码查询授权信息
     * @param equipmentCode
     * @return
     */
    LedenEquipment getLedenEquipmentByEquipmentCode(String equipmentCode);

    List<LedenEquipmentResult> selectEquipmentListByData(LedenEquipmentVo2 ledenEquipmentVo2);

    Integer selectEquipmentTotal(LedenEquipmentVo2 ledenEquipmentVo2);

    Integer selectEquipmentByCodeTotal(LedenEquipment ledenEquipment);

    /**
     * 根据部门编号查询出该部门下面所有得设备信息
     * @param unitCode
     * @return
     */
    List<String> listEqipmentCodebyUnitCode(String unitCode);

    LedenEquipment selectEquipmentByMac(@Param("mac")String equipmentMac);

    LedenEquipment selectEquipmentByEquipmentCode(@Param("equipmentCode")String equipmentCode);


    LedenEquipment getEquipmentByEquipmentCode(String equipmentCode);



}