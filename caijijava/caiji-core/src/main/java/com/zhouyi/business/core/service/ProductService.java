package com.zhouyi.business.core.service;

import java.util.List;

import com.zhouyi.business.core.model.ProductInfo;
import com.zhouyi.business.core.vo.ProductVo;

public interface ProductService {
	public List<ProductVo> queryProductList(String keyword,Integer pStart,Integer pSize);
	
	public void saveProduct(ProductInfo data);
	
	public Integer getProductCount(String keyword);
	
	public ProductInfo getProductInfo(Long id);
}
