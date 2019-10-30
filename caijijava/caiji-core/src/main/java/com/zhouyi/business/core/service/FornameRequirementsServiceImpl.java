package com.zhouyi.business.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.FornameRequirementsMapper;
import com.zhouyi.business.core.model.FornameRequirements;

@Service
public class FornameRequirementsServiceImpl implements FornameRequirementsService{

	@Autowired
	private FornameRequirementsMapper fornameRequirementsMapper;

	@Override
	public FornameRequirements getObjectById(Long id) {
		// TODO Auto-generated method stub
		return fornameRequirementsMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<Long,FornameRequirements> getRequirementsMapByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		List<FornameRequirements> list=fornameRequirementsMapper.getRequirementsListByIds(ids);
		Map<Long,FornameRequirements> data=new HashMap<Long,FornameRequirements>();
		for(FornameRequirements obj:list){
			data.put(obj.getId(), obj);
		}
		return data;
	}

	
	
}
