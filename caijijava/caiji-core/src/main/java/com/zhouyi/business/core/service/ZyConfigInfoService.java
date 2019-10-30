package com.zhouyi.business.core.service;

import java.math.BigDecimal;

import com.zhouyi.business.core.model.ZyConfigInfo;

public interface ZyConfigInfoService {
	
	
	ZyConfigInfo selectByPrimaryKey(BigDecimal id);
}
