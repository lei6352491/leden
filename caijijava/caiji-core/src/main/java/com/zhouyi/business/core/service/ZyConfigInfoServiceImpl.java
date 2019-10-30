package com.zhouyi.business.core.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.ZyConfigInfoMapper;
import com.zhouyi.business.core.model.ZyConfigInfo;

@Service
public class ZyConfigInfoServiceImpl implements ZyConfigInfoService {
	
	@Autowired
	private ZyConfigInfoMapper zyConfigInfoMapper; 

	@Override
	public ZyConfigInfo selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return zyConfigInfoMapper.selectByPrimaryKey(id);
	}

}
