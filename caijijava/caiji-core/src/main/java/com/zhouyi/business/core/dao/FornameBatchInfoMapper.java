package com.zhouyi.business.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.FornameBatchInfo;

@Mapper
public interface FornameBatchInfoMapper extends BaseMapper<FornameBatchInfo, Long>{
	public List<Map<String, Object>> getFornameBatchList(@Param("orderNo") String orderNo);
}
