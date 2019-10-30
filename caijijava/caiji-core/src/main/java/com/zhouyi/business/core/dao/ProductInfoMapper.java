package com.zhouyi.business.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.ProductInfo;
import com.zhouyi.business.core.vo.ProductVo;

@Mapper
public interface ProductInfoMapper extends BaseMapper<ProductInfo, Long>{
	public List<ProductVo> queryProductList(@Param("keyword")String keyword,@Param("pStart")Integer pStart,@Param("pSize")Integer pSize);
	
	public Integer getProductCount(@Param("keyword")String keyword);
}
