package com.zhouyi.business.core.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.ProductInfoMapper;
import com.zhouyi.business.core.model.ProductInfo;
import com.zhouyi.business.core.vo.ProductVo;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Override
	public List<ProductVo> queryProductList(String keyword,Integer pStart,Integer pSize) {
		// TODO Auto-generated method stub
		return productInfoMapper.queryProductList(keyword,pStart,pSize);
	}

	@Override
	public void saveProduct(ProductInfo data) {
		// TODO Auto-generated method stub
		Date now=new Date();
		if(data.getId()==null){
			data.setCreateTime(now);
			data.setUpdateTime(now);
			productInfoMapper.insertSelective(data);
		}else{
			data.setUpdateTime(now);
			productInfoMapper.updateByPrimaryKeySelective(data);
		}
	}

	@Override
	public Integer getProductCount(String keyword) {
		// TODO Auto-generated method stub
		return productInfoMapper.getProductCount(keyword);
	}

	@Override
	public ProductInfo getProductInfo(Long id) {
		// TODO Auto-generated method stub
		return productInfoMapper.selectByPrimaryKey(id);
	}

	
	
}
