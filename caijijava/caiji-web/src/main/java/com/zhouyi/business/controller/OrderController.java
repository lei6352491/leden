package com.zhouyi.business.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.common.LocalCacheProvider;
import com.zhouyi.business.core.model.OrderInfo;
import com.zhouyi.business.core.model.PayOrderInfo;
import com.zhouyi.business.core.model.PayTypeEnum;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.TransactionInfo;
import com.zhouyi.business.core.service.MemberService;
import com.zhouyi.business.core.service.OrderService;
import com.zhouyi.business.core.service.PayOrderService;
import com.zhouyi.business.core.service.WXPayService;
import com.zhouyi.business.core.utils.HttpRequestUtil;
import com.zhouyi.business.core.vo.MemberVo;
import com.zhouyi.business.core.vo.OrderVo;
import com.zhouyi.business.core.vo.WXRechargeBackVo;
import com.zhouyi.business.core.vo.WXRechargeVo;
import com.zhouyi.business.dto.OrderDto;
import com.zhouyi.business.dto.WithdrawDto;
import com.zhouyi.business.model.LoginData;

@Controller
@RequestMapping("/api/order")
@Api(hidden = true)
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private WXPayService wxPayService;
	
	@Autowired
	private PayOrderService payOrderService;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 查询订单列表
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryOrderPageListByUser")
	@ResponseBody
	public Response<List<OrderVo>> queryOrderPageListByUser(@RequestBody OrderDto dto, HttpServletRequest request,HttpServletResponse response){
		Response<List<OrderVo>> res=new Response<List<OrderVo>>();
		int pStart=(dto.getpNo()-1)*dto.getpSize();
		MemberVo user=(MemberVo)request.getAttribute("userInfo");
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", user.getId());
		params.put("pStart", pStart);
		params.put("pSize", dto.getpSize());
		List<OrderVo> list=orderService.queryOrderList(params);
		res.setData(list);
		return res;
	}
	
	/**
	 * 支付订单
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/payOrder")
	@ResponseBody
	public Response<WXRechargeVo> payOrder(@RequestBody OrderDto dto, HttpServletRequest request,HttpServletResponse response){
		Response<WXRechargeVo> res=new Response<WXRechargeVo>();
		if(dto.getOrderId()==null){
			res.setCode(ReturnCode.ERROR_03.getCode());
			res.setMsg(ReturnCode.ERROR_03.getMsg());
			return res;
		}
		MemberVo user=(MemberVo)request.getAttribute("userInfo");
		OrderInfo order=orderService.getObjectById(dto.getOrderId());
		if(order==null){
			res.setCode(ReturnCode.ERROR_1021.getCode());
			res.setMsg(ReturnCode.ERROR_1021.getMsg());
			return res;
		}
		String userIp=HttpRequestUtil.getRemoteIp(request);
		PayOrderInfo data=new PayOrderInfo();
		data.setChannel(502);
		data.setWxOpenid(user.getOpenId());
		data.setTotalBalance(order.getActualAmount());
		data.setType(PayTypeEnum.TYPE_105.getType());
		data.setRemark("支付订单");
		data.setUserId(user.getId());
		data.setStatus(0);
		data.setIpAddress(userIp);
		data.setOrderNo(order.getOrderNo());
		payOrderService.addPayOrder(data);
		TransactionInfo ti=new TransactionInfo();
		ti.setAmount(order.getActualAmount());
		ti.setOrderNo(order.getOrderNo());
		ti.setRemark("支付订单");
		ti.setUserId(order.getUserId().toString());
		ti.setWxOpenid(user.getOpenId());
		ti.setUserIp(userIp);
		WXRechargeVo result=wxPayService.payOrder(ti);
		if(!result.checkSuccess()){
			logger.info("payOrder_err={}",JSON.toJSONString(result));
			res.setCode(ReturnCode.ERROR_01.getCode());
			res.setMsg(result.getErrCodeDes());
			return res;
		}
		
		res.setData(result);
		return res;
	}
	
	/**
	 * 提现
	 * @param withdrawDto
	 * @param request
	 * @return
	 */
	@RequestMapping("/toWithdrawals")
	@ResponseBody
	public Response<Object> toWithdrawals(@RequestBody WithdrawDto withdrawDto, HttpServletRequest request) {
	    
	    
		Response<Object> res = new Response<Object>();
		
		//判断钱不总数是不是等于0的情况
		if(withdrawDto.getTotalAmount() == 0){
			res.setCode(ReturnCode.ERROR_1010.getCode());
			res.setMsg(ReturnCode.ERROR_1010.getMsg());
			return res;
		}
		try {
			String userIp=HttpRequestUtil.getRemoteIp(request);
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			PayOrderInfo data=new PayOrderInfo();
			data.setChannel(502);
			data.setWxOpenid(user.getOpenId());
			data.setTotalBalance(withdrawDto.getTotalAmount());
			data.setType(PayTypeEnum.TYPE_101.getType());
			data.setRemark("提现");
			data.setUserId(user.getId());
			data.setStatus(0);
			data.setIpAddress(userIp);
			payOrderService.addPayOrder(data);
			TransactionInfo orderInfo=new TransactionInfo();
			orderInfo.setOrderNo(data.getOrderNo());
			orderInfo.setAmount(data.getTotalBalance());
			orderInfo.setWxOpenid(data.getWxOpenid());
			orderInfo.setUserIp(userIp);
			WXRechargeVo wvResult=wxPayService.toWithdrawals(orderInfo);
			logger.info("toWithdrawals_result={}",data.getId(),JSON.toJSONString(wvResult));
			if(!wvResult.getResultCode().equals("SUCCESS")){
				res.setCode(ReturnCode.ERROR_1005.getCode());
				res.setMsg(wvResult.getResultMsg());
				
				payOrderService.updateStatus(data.getId(), 2);
			}
		}catch (Exception e) {
			logger.error("withdrawalsService",e);
		}
		return res;
		
	}
	
	/**
	 * 提现
	 * @param withdrawDto
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryWithdrawalsList")
	@ResponseBody
	public Response<Object> queryWithdrawalsList(@RequestBody WithdrawDto dto, HttpServletRequest request) {
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		MemberVo user=(MemberVo)request.getAttribute("userInfo");
		Response<Object> res = new Response<Object>();
		int pStart=(dto.getpNo()-1)*dto.getpSize();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("type", 101);
		params.put("userId", user.getId());
		params.put("pStart", pStart);
		params.put("pSize", dto.getpSize());
		List<PayOrderInfo> orderList=payOrderService.queryPayOrderList(params);
		List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
		for(PayOrderInfo pi:orderList){
			Map<String,Object> obj=new HashMap<String,Object>();
			obj.put("createTime", sdf.format(pi.getCreateTime()));
			obj.put("totalAmount", pi.getTotalBalance());
			obj.put("status", pi.getStatus());
			obj.put("channel", pi.getChannel());
			dataList.add(obj);
		}
		res.setData(dataList);
		return res;
		
	}
	
	/**
	 * 支付订单回调
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/payNotice")
	public void payNotice(HttpServletRequest request,HttpServletResponse response){
		try{
			logger.info("payNotice_in....");
			InputStream inputStream ;  
	        StringBuffer sb = new StringBuffer();  
	        inputStream = request.getInputStream();  
	        String s=null;  
	        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
	        while ((s = in.readLine()) != null){  
	            sb.append(s);  
	        }  
	        in.close();  
	        inputStream.close(); 
	        String weixinResultXml  = sb.toString();//获取微信调用我们notify_url的返回信息
	        logger.info("payNotice_params={}",weixinResultXml);
	        WXRechargeBackVo wv=wxPayService.payNotice(weixinResultXml);
	        
	        BufferedOutputStream bs=new BufferedOutputStream(response.getOutputStream());
	        bs.write(wv.returnWX().getBytes());
	        bs.flush();
	        bs.close();
	        
	        if(wv.checkSuccess() && wv.getUserId()!=null){
	        	MemberVo user=memberService.getMemberDetail(wv.getUserId());
	        	String token=LocalCacheProvider.getInstance().getUserTokenMap().get(wv.getUserId().toString());
	    		LoginData ld=LocalCacheProvider.getInstance().getLoginMap().get(token);
	    		ld.setMember(user);
	        }
		}catch(Exception e){
			logger.error("payNotice_err=",e);
		}
	}
	
	/**
	 * 进度查询
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getOrderDetail")
	@ResponseBody
	public Response<Object> getOrderDetail(@RequestBody OrderDto dto, HttpServletRequest request,HttpServletResponse response){
		Response<Object> res=new Response<Object>();
		MemberVo user=(MemberVo)request.getAttribute("userInfo");
		Map<String,Object> orderMap=orderService.getLastOrderDetailByUser(user.getId());
		if(orderMap==null || orderMap.isEmpty()){
			res.setCode(ReturnCode.ERROR_1021.getCode());
			res.setMsg(ReturnCode.ERROR_1021.getMsg());
			return res;
		}
		List<Map<String,Object>> batchList=orderService.getFornameBatchList(orderMap.get("orderNo").toString());
		for(int i=0;i<batchList.size();i++){
			Map<String,Object> obj=batchList.get(i);
			obj.put("times", batchList.size()-i);
		}
		orderMap.put("headImage", "http://thirdwx.qlogo.cn/mmopen/vi_32/4Ijz7gf1qrkpDnus9FGpWhRcQ2ibY6guxMsN0BjJ781ekBRiafdtZ8iboLWPoLYKwSBm9pFdnEI7AwiaibnBQde64aQ/132");
		orderMap.put("batchList", batchList);
		res.setData(orderMap);
		return res;
	}

	/**
	 * 支付高分卡回调
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cardPayNotice")
	public void cardPayNotice(HttpServletRequest request,HttpServletResponse response){
		try{
			logger.info("cardPayNotice_in....");
			InputStream inputStream ;  
	        StringBuffer sb = new StringBuffer();  
	        inputStream = request.getInputStream();  
	        String s=null;  
	        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
	        while ((s = in.readLine()) != null){  
	            sb.append(s);  
	        }  
	        in.close();  
	        inputStream.close(); 
	        String weixinResultXml  = sb.toString();//获取微信调用我们notify_url的返回信息
	        logger.info("cardPayNotice_params={}",weixinResultXml);
	        WXRechargeBackVo wv=wxPayService.cardPayNotice(weixinResultXml);
	        
	        BufferedOutputStream bs=new BufferedOutputStream(response.getOutputStream());
	        bs.write(wv.returnWX().getBytes());
	        bs.flush();
	        bs.close();
		}catch(Exception e){
			logger.error("cardPayNotice_err=",e);
		}
	}

	/**
	 * 人工起名服务回调
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/personPayNotice")
	public void personPayNotice(HttpServletRequest request,HttpServletResponse response){
		try{
			logger.info("personPayNotice_in....");
			InputStream inputStream ;  
	        StringBuffer sb = new StringBuffer();  
	        inputStream = request.getInputStream();  
	        String s=null;  
	        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
	        while ((s = in.readLine()) != null){  
	            sb.append(s);  
	        }  
	        in.close();  
	        inputStream.close(); 
	        String weixinResultXml  = sb.toString();//获取微信调用我们notify_url的返回信息
	        logger.info("personPayNotice_params={}",weixinResultXml);
	        WXRechargeBackVo wv=wxPayService.personPayNotice(weixinResultXml);
	        
	        BufferedOutputStream bs=new BufferedOutputStream(response.getOutputStream());
	        bs.write(wv.returnWX().getBytes());
	        bs.flush();
	        bs.close();
		}catch(Exception e){
			logger.error("personPayNotice_err=",e);
		}
	}
}
