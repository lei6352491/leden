package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenPersonIndex;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenPersonIndexMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(LedenPersonIndex record);

    int insertSelective(LedenPersonIndex record);

    LedenPersonIndex selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(LedenPersonIndex record);

    int updateByPrimaryKey(LedenPersonIndex record);

    LedenPersonIndex selectNextPrimaryKey(String unitCode);
}