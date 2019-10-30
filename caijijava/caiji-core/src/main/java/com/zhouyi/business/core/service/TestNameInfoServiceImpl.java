package com.zhouyi.business.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.TestNameInfoMapper;
import com.zhouyi.business.core.model.TestNameInfo;

@Service
public class TestNameInfoServiceImpl implements TestNameInfoService{

	@Autowired
	private TestNameInfoMapper testNameInfoMapper;

	@Override
	public Long addTestNameInfo(TestNameInfo data) {
		// TODO Auto-generated method stub
		testNameInfoMapper.insertAndReturnId(data);
		return data.getId();
	}

	@Async
	@Override
	public void addAsyncTestNameInfo(TestNameInfo data) {
		// TODO Auto-generated method stub
		testNameInfoMapper.insertAndReturnId(data);
	}

	@Override
	public TestNameInfo getObjectById(Long id) {
		// TODO Auto-generated method stub
		return testNameInfoMapper.selectByPrimaryKey(id);
	}

	
	
}
