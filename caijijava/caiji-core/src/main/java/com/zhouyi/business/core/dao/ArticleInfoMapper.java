package com.zhouyi.business.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.ArticleInfo;
import com.zhouyi.business.core.vo.ArticleVo;

@Mapper
public interface ArticleInfoMapper extends BaseMapper<ArticleInfo, Long>{
	public List<ArticleVo> queryArticleList(@Param("keyword")String keyword,@Param("pStart")Integer pStart,@Param("pSize")Integer pSize);
	
	public Integer getArticleCount(@Param("keyword")String keyword);
}
