package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenCollectProcessMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(LedenCollectProcess record);

    int insertSelective(LedenCollectProcess record);

    LedenCollectProcess selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(LedenCollectProcess record);

    int updateByPrimaryKey(LedenCollectProcess record);

    List<LedenCollectProcess> listLedenCollectProcessByConditions(Map<String,Object> conditions);

    int getLedenCollectProcessCountByConditions(Map<String,Object> conditions);

    int deleteProcessByPersonCodeAndNodeCode(@Param(value="personCode") String personCode, @Param(value="nodeCode") String nodeCode);
}