package com.zhouyi.business.core.service;

import java.util.List;

import com.zhouyi.business.core.model.ArticleInfo;
import com.zhouyi.business.core.vo.ArticleVo;

public interface ArticleService {
	public void saveArticle(ArticleInfo data);
	
	public void updateArticle(ArticleInfo data);
	
	public ArticleInfo getObjectInfo(Long id);
	
	public List<ArticleVo> queryArticleList(String keyword,Integer pStart,Integer pSize);
	
	public Integer getArticleCount(String keyword);
}
