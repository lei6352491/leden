package com.zhouyi.business.core.service;

import java.util.List;
import java.util.Map;

import com.zhouyi.business.core.vo.NameCommentVo;

public interface NameCommentService {
	public List<NameCommentVo> queryNameCommentList(Map<String,Object> params);
	
	public Integer getNameCommentCount(Map<String,Object> params);
}
