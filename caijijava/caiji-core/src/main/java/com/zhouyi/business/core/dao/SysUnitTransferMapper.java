package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysUnitTransfer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUnitTransferMapper {
    int deleteByPrimaryKey(String transferCode);

    int insert(SysUnitTransfer record);

    int insertSelective(SysUnitTransfer record);

    SysUnitTransfer selectByPrimaryKey(String transferCode);

    int updateByPrimaryKeySelective(SysUnitTransfer record);

    int updateByPrimaryKey(SysUnitTransfer record);

    List<SysUnitTransfer> listUnitTransfersByConditions(Map<String,Object> conditions);

    int getUnitTransfersCountByConditions(Map<String,Object> conditions);
}