package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.TestNameInfo;

public interface TestNameInfoService {
	public Long addTestNameInfo(TestNameInfo data);
	
	public void addAsyncTestNameInfo(TestNameInfo data);
	
	public TestNameInfo getObjectById(Long id);
}
