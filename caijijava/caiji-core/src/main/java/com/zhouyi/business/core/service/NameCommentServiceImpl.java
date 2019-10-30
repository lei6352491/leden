package com.zhouyi.business.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.NameCommentMapper;
import com.zhouyi.business.core.vo.NameCommentVo;

@Service
public class NameCommentServiceImpl implements NameCommentService{

	@Autowired
	private NameCommentMapper nameCommentMapper;

	@Override
	public List<NameCommentVo> queryNameCommentList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return nameCommentMapper.queryNameCommentList(params);
	}

	@Override
	public Integer getNameCommentCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return nameCommentMapper.getNameCommentCount(params);
	}

	
	
}
