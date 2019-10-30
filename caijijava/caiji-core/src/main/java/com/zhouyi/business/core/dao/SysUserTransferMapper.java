package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysUserTransfer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserTransferMapper {
    int deleteByPrimaryKey(String transferCode);

    int insert(SysUserTransfer record);

    int insertSelective(SysUserTransfer record);

    SysUserTransfer selectByPrimaryKey(String transferCode);

    int updateByPrimaryKeySelective(SysUserTransfer record);

    int updateByPrimaryKey(SysUserTransfer record);

    /**
     * 根据条件分页查询用户调离记录信息
     * @param conditions
     * @return
     */
    List<SysUserTransfer> listSysUserTransfersByConditions(Map<String,Object> conditions);


    /**
     * 根据条件查询用户调离记录数量
     * @param conditions
     * @return
     */
    int getSysUserTransferCountByConditions(Map<String,Object> conditions);
}