package com.zhouyi.business.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zhouyi.business.core.model.NameCommentInfo;
import com.zhouyi.business.core.vo.NameCommentVo;

@Mapper
public interface NameCommentMapper extends BaseMapper<NameCommentInfo, Long>{
	public List<NameCommentVo> queryNameCommentList(Map<String,Object> params);
	
	public Integer getNameCommentCount(Map<String,Object> params);
}
