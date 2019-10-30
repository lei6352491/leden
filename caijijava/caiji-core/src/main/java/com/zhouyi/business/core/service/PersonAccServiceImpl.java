package com.zhouyi.business.core.service;

import java.math.BigDecimal;
import java.util.Date;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.PersonAccInfoMapper;
import com.zhouyi.business.core.dao.PersonExchangeDetailMapper;
import com.zhouyi.business.core.model.PersonAccInfo;
import com.zhouyi.business.core.model.PersonExchangeDetail;

@Service
public class PersonAccServiceImpl implements PersonAccService{

	@Autowired
	private PersonAccInfoMapper personAccInfoMapper;
	
	@Autowired
	private PersonExchangeDetailMapper personExchangeDetailMapper;
	
	@Override
	public void incomeBalance(Long userId, int amount, Long serialNo) {
		// TODO Auto-generated method stub
		PersonExchangeDetail pd=personExchangeDetailMapper.getPersonExchangeDetailByNo(serialNo,"001");
		if(pd!=null){
			throw new BusinessException(ReturnCode.ERROR_1019.getCode(), ReturnCode.ERROR_1019.getMsg());
		}
		PersonAccInfo acc = personAccInfoMapper.getSubaccByUserId(userId);
		int result=personAccInfoMapper.addAvaBalance(amount, acc.getAccNo());
		if(result==0){
			throw new BusinessException(ReturnCode.ERROR_1018.getCode(), ReturnCode.ERROR_1018.getMsg());
		}
		this.insertPersonTotalBalance(acc, serialNo, "001", BigDecimal.valueOf(amount));
	}

	private void insertPersonTotalBalance(PersonAccInfo acc, Long serialNo, String operateType, BigDecimal totalBalance) {
    	
    	BigDecimal newBalance=totalBalance;
    	if(operateType.equals("001")){
    		newBalance=newBalance.add(acc.getAvaBalance());
    	}else if(operateType.equals("005")){
    		newBalance=newBalance.subtract(acc.getAvaBalance());
    	}
    	PersonExchangeDetail personExchangeDetail = new PersonExchangeDetail();
    	personExchangeDetail.setAccNo(acc.getAccNo());
    	personExchangeDetail.setTotalBalance(totalBalance);
    	personExchangeDetail.setOperateType(operateType);
    	personExchangeDetail.setOldAvaBalance(acc.getAvaBalance());
    	personExchangeDetail.setNewAvaBalance(newBalance);
		personExchangeDetail.setCreateTime(new Date());
		personExchangeDetail.setSerialNo(serialNo);
		
		personExchangeDetailMapper.insertSelective(personExchangeDetail);
    }

	@Override
	public void deduBalance(Long userId, int amount, Long serialNo) {
		// TODO Auto-generated method stub
		PersonExchangeDetail pd=personExchangeDetailMapper.getPersonExchangeDetailByNo(serialNo,"005");
		if(pd!=null){
			throw new BusinessException(ReturnCode.ERROR_1019.getCode(), ReturnCode.ERROR_1019.getMsg());
		}
		PersonAccInfo acc = personAccInfoMapper.getSubaccByUserId(userId);
		int result=personAccInfoMapper.subAvaBalance(amount, acc.getAccNo());
		if(result==0){
			throw new BusinessException(ReturnCode.ERROR_1018.getCode(), ReturnCode.ERROR_1018.getMsg());
		}
		this.insertPersonTotalBalance(acc, serialNo, "005", BigDecimal.valueOf(amount));
	}
	
	
	
}
