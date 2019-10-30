//package com.zhouyi.business.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.zhouyi.business.core.common.ReturnCode;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.zhouyi.business.core.model.ArticleInfo;
//import com.zhouyi.business.core.model.Response;
//import com.zhouyi.business.core.service.ArticleService;
//import com.zhouyi.business.core.vo.ArticleVo;
//import com.zhouyi.business.dto.BaseDto;
//
//@Controller
//@RequestMapping("/api/article")
//public class ArticleController {
//	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
//
//	@Autowired
//	private ArticleService articleService;
//
//	/**
//	 * 查询文章详情
//	 * @param dto
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping("/getArticleInfo")
//	@ResponseBody
//	public Response<Object> getArticleInfo(@RequestBody ArticleInfo dto, HttpServletRequest request,HttpServletResponse response){
//		Response<Object> res=new Response<Object>();
//		if(dto.getId()==null){
//			res.setCode(ReturnCode.ERROR_03.getCode());
//			res.setMsg(ReturnCode.ERROR_03.getMsg());
//			return res;
//		}
//		ArticleInfo data=articleService.getObjectInfo(dto.getId());
//		res.setData(data);
//		return res;
//	}
//
//	/**
//	 * 查询文章列表
//	 * @param dto
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping("/queryArticlePageList")
//	@ResponseBody
//	public Response<List<ArticleVo>> queryArticlePageList(@RequestBody BaseDto dto, HttpServletRequest request,HttpServletResponse response){
//		Response<List<ArticleVo>> res=new Response<List<ArticleVo>>();
//		Integer pStart=(dto.getpNo()-1)*dto.getpSize();;
//		try{
//			List<ArticleVo> list=articleService.queryArticleList(dto.getKeyword(),pStart,dto.getpSize());
//			res.setData(list);
//		}catch(Exception e){
//			logger.error("queryArticlePageList_err=",e);
//		}
//		return res;
//	}
//}
