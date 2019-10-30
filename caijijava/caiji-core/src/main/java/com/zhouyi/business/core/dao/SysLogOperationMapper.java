package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysLogOperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysLogOperationMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(SysLogOperation record);

    int insertSelective(SysLogOperation record);

    SysLogOperation selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(SysLogOperation record);

    int updateByPrimaryKey(SysLogOperation record);

    /**
     * 根据条件分页查询系统操作日志
     * @param conditions
     * @return
     */
    List<SysLogOperation> listSysLogOperationByConditions(Map<String,Object> conditions);

    /**
     * 根据条件查询系统日志记录数
     * @param conditions
     * @return
     */
    int getSysLogOperationCountByConditions(Map<String,Object> conditions);
}