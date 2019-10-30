package com.zhouyi.business.core.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.ZyConfigInfo;


@Mapper
public interface ZyConfigInfoMapper extends BaseMapper<ZyConfigInfo, Long>{
    int deleteByPrimaryKey(BigDecimal id);

    int insert(ZyConfigInfo record);

    int insertSelective(ZyConfigInfo record);

    ZyConfigInfo selectByPrimaryKey(@Param("id") BigDecimal id);

    int updateByPrimaryKeySelective(ZyConfigInfo record);

    int updateByPrimaryKey(ZyConfigInfo record);
}