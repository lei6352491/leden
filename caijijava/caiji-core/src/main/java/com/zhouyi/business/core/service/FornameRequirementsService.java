package com.zhouyi.business.core.service;

import java.util.List;
import java.util.Map;

import com.zhouyi.business.core.model.FornameRequirements;

public interface FornameRequirementsService {
	public FornameRequirements getObjectById(Long id);
	
	public Map<Long,FornameRequirements> getRequirementsMapByIds(List<Long> ids);
}
