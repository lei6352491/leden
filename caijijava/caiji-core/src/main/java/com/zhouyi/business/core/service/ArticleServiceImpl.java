package com.zhouyi.business.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.ArticleInfoMapper;
import com.zhouyi.business.core.model.ArticleInfo;
import com.zhouyi.business.core.vo.ArticleVo;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleInfoMapper articleInfoMapper;

	@Override
	public void saveArticle(ArticleInfo data) {
		// TODO Auto-generated method stub
		Date now=new Date();
		if(data.getId()==null){
			data.setCreateTime(now);
			data.setUpdateTime(now);
			articleInfoMapper.insertAndReturnId(data);
		}else{
			data.setUpdateTime(now);
			articleInfoMapper.updateByPrimaryKeySelective(data);
		}
	}

	@Override
	public void updateArticle(ArticleInfo data) {
		// TODO Auto-generated method stub
		articleInfoMapper.updateByPrimaryKeySelective(data);
	}

	@Override
	public ArticleInfo getObjectInfo(Long id) {
		// TODO Auto-generated method stub
		return articleInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ArticleVo> queryArticleList(String keyword,Integer pStart,Integer pSize) {
		// TODO Auto-generated method stub
		return articleInfoMapper.queryArticleList(keyword,pStart,pSize);
	}

	@Override
	public Integer getArticleCount(String keyword) {
		// TODO Auto-generated method stub
		return articleInfoMapper.getArticleCount(keyword);
	}

	
	
}
