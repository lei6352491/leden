package com.zhouyi.business.controller;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhouyi.business.core.common.ReturnCode;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.common.CommonConstant;
import com.zhouyi.business.core.common.Config;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.ProductInfo;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.ProductService;
import com.zhouyi.business.core.vo.ProductVo;
import com.zhouyi.business.dto.BaseDto;

@Controller
@RequestMapping("/api/product")
@Api(hidden = true)
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Config config;
	/**
	 * 查询产品列表
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/admin/queryProductPageList")
	@ResponseBody
	public Response<PageData<ProductVo>> queryArticlePageList(@RequestBody BaseDto dto, HttpServletRequest request,HttpServletResponse response){
		Response<PageData<ProductVo>> res=new Response<PageData<ProductVo>>();
		PageData<ProductVo> data=new PageData<ProductVo>();
		Integer pStart=(dto.getpNo()-1)*dto.getpSize();
		List<ProductVo> list=productService.queryProductList(dto.getKeyword(),pStart,dto.getpSize());
		Integer totalCount=productService.getProductCount(dto.getKeyword());
		data.setTotalCount(totalCount);
		data.setDataList(list);
		data.setpSize(dto.getpSize());
		data.setTotalPage((totalCount+dto.getpSize()-1)/dto.getpSize());
		res.setData(data);
		return res;
	}
	
	/**
	 * 添加/修改产品
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/admin/addProduct")
	@ResponseBody
	public void addProduct(HttpServletRequest request,HttpServletResponse response){
		Response<Object> resp=new Response<Object>();
		ProductInfo dto=new ProductInfo();
		String amount=request.getParameter("amount");
        String description=request.getParameter("description");
        String productName=request.getParameter("productName");
        String productCode=request.getParameter("productCode");
		PrintWriter out = null;
		try{
			out = response.getWriter();
	        dto.setAmount(Integer.valueOf(amount));
	        dto.setDescription(description);
	        dto.setDescription(description);
	        dto.setProductCode(productCode);
	        dto.setProductName(productName);
			
			CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request))
	        {
	            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	            Date now=new Date();
	            File dir=new File(config.getUploadPath()+"/"+sdf.format(now));
	            if(!dir.exists()){
	            	dir.mkdirs();
	            }
	            Iterator<String> iter=multiRequest.getFileNames();
	            
	            while(iter.hasNext())
	            {
	                //一次遍历所有文件
	                MultipartFile file=multiRequest.getFile(iter.next().toString());
	                if(file!=null)
	                {
	                	int index=file.getOriginalFilename().lastIndexOf(".");
	                	String newFileName=System.currentTimeMillis()+file.getOriginalFilename().substring(index);
	                    String path="/"+sdf.format(now)+"/"+newFileName;
	                    dto.setImages(CommonConstant.FILE_ROOT+path);
	                    //上传
	                    file.transferTo(new File(config.getUploadPath()+path));
	                }
	                 
	            }
	           
	        }
	        if(dto.getImages()==null || dto.getImages().equals("")){
	        	resp.setCode(ReturnCode.ERROR_01.getCode());
		        resp.setMsg(ReturnCode.ERROR_01.getMsg());
				out.println(JSON.toJSONString(resp));
	        	return;
	        }
	        productService.saveProduct(dto);
	        resp.setCode(ReturnCode.SUCCESS.getCode());
	        resp.setMsg(ReturnCode.SUCCESS.getMsg());
			out.println(JSON.toJSONString(resp));
		}catch(Exception e){
			logger.error("addProduct_err=",e);
			resp.setCode(ReturnCode.ERROR_01.getCode());
	        resp.setMsg(ReturnCode.ERROR_01.getMsg());
			out.println(JSON.toJSONString(resp));
		}
		
	}
	
	/**
	 * 下架产品
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/admin/toPutaway")
	@ResponseBody
	public Response<PageData<ProductVo>> toPutaway(@RequestBody ProductInfo dto, HttpServletRequest request,HttpServletResponse response){
		Response<PageData<ProductVo>> res=new Response<PageData<ProductVo>>();
		dto.setUpdateTime(new Date());
		productService.saveProduct(dto);
		return res;
	}
	
	/**
	 * 获取产品详情
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/admin/getProductInfo")
	@ResponseBody
	public Response<ProductInfo> getProductInfo(@RequestBody ProductInfo dto, HttpServletRequest request,HttpServletResponse response){
		Response<ProductInfo> res=new Response<ProductInfo>();
		ProductInfo pi=productService.getProductInfo(dto.getId());
		res.setData(pi);
		return res;
	}
}
