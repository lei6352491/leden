package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysLogData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysLogDataMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(SysLogData record);

    int insertSelective(SysLogData record);

    SysLogData selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(SysLogData record);

    int updateByPrimaryKey(SysLogData record);

    /**
     * 根据条件分页查询数据日志
     * @param conditions
     * @return
     */
    List<SysLogData> listSysLogDataByConditions(Map<String,Object> conditions);

    /**
     * 根据条件查询总记录数
     * @param conditions
     * @return
     */
    int getSysLogDataCountByConditions(Map<String,Object> conditions);
}