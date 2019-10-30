package com.zhouyi.business.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.FornameRequirements;

@Mapper
public interface FornameRequirementsMapper extends BaseMapper<FornameRequirements, Long>{
	public FornameRequirements getFornameRequirementsByOrderNo(@Param("orderNo") String orderNo);
	
	public List<FornameRequirements> getRequirementsListByIds(@Param("ids") List<Long> ids);
}
