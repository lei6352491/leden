package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenEquipmentParets;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenEquipmentParetsMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(LedenEquipmentParets record);

    int insertSelective(LedenEquipmentParets record);

    LedenEquipmentParets selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(LedenEquipmentParets record);

    int updateByPrimaryKey(LedenEquipmentParets record);

    List<LedenEquipmentParets> listLedenEquipmentParetesByConditions(Map<String,Object> conditions);

    int getLedenEquipmentParetsCountByConditions(Map<String,Object> conditions);
}