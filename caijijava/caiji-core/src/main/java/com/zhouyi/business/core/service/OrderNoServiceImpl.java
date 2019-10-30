package com.zhouyi.business.core.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.OrderNoMapper;
import com.zhouyi.business.core.model.OrderNo;

@Service
public class OrderNoServiceImpl implements OrderNoService{

	@Autowired
	private OrderNoMapper orderMapper;

	@Override
	public Long getOrderNo() {
		// TODO Auto-generated method stub
		OrderNo data=new OrderNo();
		orderMapper.insertAndReturnId(data);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
		Date d=new Date();
		DecimalFormat df = new DecimalFormat("00000000000");
		String uid = df.format(data.getId());
		return Long.valueOf(sdf.format(d)+uid);
	}

	@Override
	public String getOrderNo(String phone) {
		SimpleDateFormat sdf = new SimpleDateFormat("mmss");
		Date now=new Date();
		Random r=new Random();
		int random=r.nextInt(8999)+1000;
		String orderNo=random+sdf.format(now)+"_"+phone;
		return orderNo;
	}


	
	
}
