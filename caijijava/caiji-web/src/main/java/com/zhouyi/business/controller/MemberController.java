package com.zhouyi.business.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhouyi.business.core.common.ReturnCode;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.common.LocalCacheProvider;
import com.zhouyi.business.core.common.Config;
import com.zhouyi.business.core.model.CardOrderInfo;
import com.zhouyi.business.core.model.FornameRequirements;
import com.zhouyi.business.core.model.MemberInfo;
import com.zhouyi.business.core.model.OrderInfo;
import com.zhouyi.business.core.model.PayOrderInfo;
import com.zhouyi.business.core.model.PayTypeEnum;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.TestNameInfo;
import com.zhouyi.business.core.model.TransactionInfo;
import com.zhouyi.business.core.model.UserCardInfo;
import com.zhouyi.business.core.model.UserNames;
import com.zhouyi.business.core.service.CardService;
import com.zhouyi.business.core.service.FornameRequirementsService;
import com.zhouyi.business.core.service.MemberService;
import com.zhouyi.business.core.service.OrderNoService;
import com.zhouyi.business.core.service.OrderService;
import com.zhouyi.business.core.service.PayOrderService;
import com.zhouyi.business.core.service.TestNameInfoService;
import com.zhouyi.business.core.service.UserGiftService;
import com.zhouyi.business.core.service.WXPayService;
import com.zhouyi.business.core.utils.HttpRequestUtil;
import com.zhouyi.business.core.vo.MemberVo;
import com.zhouyi.business.core.vo.UserGiftVo;
import com.zhouyi.business.core.vo.WXRechargeVo;
import com.zhouyi.business.dto.BaseDto;
import com.zhouyi.business.dto.OrderDto;
import com.zhouyi.business.dto.PageDto;
import com.zhouyi.business.dto.RequirementsDto;

@Controller
@RequestMapping("/api/member")
@Api(hidden = true)
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserGiftService userGiftService;
	
	@Autowired
	private PayOrderService payOrderService;
	
	@Autowired
	private WXPayService wxPayService;

	@Autowired
	private Config config;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private FornameRequirementsService fornameRequirementsService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private OrderNoService orderNoService;
	
	@Autowired
    private TestNameInfoService testNameInfoService;
	/**
	 * 查询用户礼品明细
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryUserGiftList")
	@ResponseBody
	public Response<List<UserGiftVo>> queryUserGiftList(@RequestBody BaseDto dto, HttpServletRequest request,HttpServletResponse response){
		logger.info("queryUserGiftList_params={}",dto);
		Response<List<UserGiftVo>> res=new Response<List<UserGiftVo>>();
		MemberVo user=(MemberVo)request.getAttribute("userInfo");
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", user.getId());
		List<UserGiftVo> list=userGiftService.queryUserGiftList(params);
		List<Map<String,Object>> puList=userGiftService.queryProductUserGiftList(user.getId());
		Map<Long,Integer> puCountMap=userGiftService.getUserGiftCount(user.getId());
		Map<Long,List<Map<String,Object>>> productData=new HashMap<Long,List<Map<String,Object>>>();
		for(Map<String,Object> obj:puList){
			Long productId=Long.valueOf(obj.get("productId").toString());
			List<Map<String,Object>> d=productData.get(productId);
			if(d==null){
				d=new ArrayList<Map<String,Object>>();
				productData.put(productId, d);
			}
			d.add(obj);
		}
		for(UserGiftVo uv:list){
			List<Map<String,Object>> subPuList=productData.get(uv.getProductId());
			uv.setProductUserList(subPuList);
			uv.setTotalCount(puCountMap.get(uv.getProductId()));
		}
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
	@RequestMapping("/openMember")
	@ResponseBody
	public Response<WXRechargeVo> openMember(@RequestBody OrderDto dto, HttpServletRequest request,HttpServletResponse response){
		logger.info("openMember_params={}",JSON.toJSONString(dto));
		Response<WXRechargeVo> res=new Response<WXRechargeVo>();
		String app=request.getHeader("App");
		try{
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			Date now=new Date();
			PayOrderInfo data=new PayOrderInfo();
			data.setChannel(502);
			data.setCreateTime(now);
			data.setUpdateTime(now);
			data.setWxOpenid(user.getOpenId());
			Map<String,String> configMap=LocalCacheProvider.getInstance().getConfigMap();
			String cost=configMap.get("OPEN_MEMBER_COST");
			String whiteList=configMap.get("WHITE_LIST");
			if(whiteList.contains(user.getOpenId())){
				cost="1";
			}
			data.setTotalBalance(Integer.valueOf(cost));
			data.setType(PayTypeEnum.TYPE_102.getType());
			data.setRemark("开通会员");
			data.setUserId(user.getId());
			data.setStatus(0);
			payOrderService.addPayOrder(data);
	
			TransactionInfo ti=new TransactionInfo();
			ti.setAmount(data.getTotalBalance());
			ti.setOrderNo(data.getOrderNo());
			ti.setRemark("开通会员");
			ti.setUserId(data.getUserId().toString());
			ti.setWxOpenid(user.getOpenId());
			WXRechargeVo result=null;
			if(app!=null){
				result=wxPayService.payAppOrder(ti);
			}else{
				result=wxPayService.payOrder(ti);
			}
			if(!result.checkSuccess()){
				logger.info("openMember_err={}",JSON.toJSONString(result));
				res.setCode(ReturnCode.ERROR_01.getCode());
				res.setMsg(result.getErrCodeDes());
				return res;
			}
			res.setData(result);
		}catch(Exception e){
			logger.error("openMember_err=",e);
		}
		
		return res;
	}
	
	/**
	 * 升级姓名库
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/upgradeNameLib")
	@ResponseBody
	public Response<WXRechargeVo> upgradeNameLib(@RequestBody MemberInfo dto, HttpServletRequest request,HttpServletResponse response){
		logger.info("upgradeNameLib_params={}",JSON.toJSONString(dto));
		Response<WXRechargeVo> res=new Response<WXRechargeVo>();
		if(dto.getNameLib()==null){
			res.setCode(ReturnCode.ERROR_03.getCode());
			res.setMsg(ReturnCode.ERROR_03.getMsg());
			return res;
		}
		try{
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			Date now=new Date();
			PayOrderInfo data=new PayOrderInfo();
			data.setChannel(502);
			data.setCreateTime(now);
			data.setUpdateTime(now);
			data.setWxOpenid(user.getOpenId());
			String cost=LocalCacheProvider.getInstance().getConfigMap().get("OPEN_MEMBER_COST");
			data.setTotalBalance(Integer.valueOf(cost));
			if(dto.getNameLib()==1){
				data.setType(PayTypeEnum.TYPE_103.getType());
			}else{
				data.setType(PayTypeEnum.TYPE_104.getType());
			}
			data.setRemark("升级名字库");
			data.setUserId(user.getId());
			data.setStatus(0);
			payOrderService.addPayOrder(data);
	
			TransactionInfo ti=new TransactionInfo();
			ti.setAmount(data.getTotalBalance());
			ti.setOrderNo(data.getOrderNo());
			ti.setRemark("升级名字库");
			ti.setUserId(data.getUserId().toString());
			ti.setWxOpenid(user.getOpenId());
			WXRechargeVo result=wxPayService.payOrder(ti);
			if(!result.checkSuccess()){
				logger.info("upgradeNameLib_err={}",JSON.toJSONString(result));
				res.setCode(ReturnCode.ERROR_01.getCode());
				res.setMsg(result.getErrCodeDes());
				return res;
			}
			res.setData(result);
		}catch(Exception e){
			logger.error("upgradeNameLib_err=",e);
		}
		
		return res;
	}
	
	/**
	 * 智能取名
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/intelForname")
	@ResponseBody
	public Response<Map<String,Object>> intelForname(@RequestBody RequirementsDto require, HttpServletRequest request,HttpServletResponse response){
		Response<Map<String,Object>> res=new Response<Map<String,Object>>();
		logger.info("intelForname_params={}",JSON.toJSONString(require));
		MemberVo user=(MemberVo)request.getAttribute("userInfo");
		UserCardInfo uci=cardService.getUserCardByUserId(user.getId());
		
		if(require.getIsUsedCard()==null || require.getIsUsedCard()==0){
			res=this.freeIntelForname(require, request);
		}else if((user.getUserType()!=null && user.getUserType()==1) || (uci!=null && uci.getUnLock()!=1 && uci.getUsedNum()<uci.getTotalNum())){
			//收费起名
			Long batchId=(new Date()).getTime();
			if(uci!=null){
				batchId=Long.valueOf(batchId+""+(uci.getUsedNum()+1));
			}
			res=this.chargeIntelForname(require, request,batchId);
		}else{
			res=this.freeIntelForname(require, request);
		}
		return res;
	}
	
	/**
	 * 取名结果列表
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryNameResultList")
	@ResponseBody
	public Response<Object> queryNameResultList(@RequestBody PageDto dto, HttpServletRequest request,HttpServletResponse response){
		Response<Object> res=new Response<Object>();
		
		try{
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			Integer pStart=(dto.getpNo()-1)*dto.getpSize();
			List<UserNames> list=memberService.queryNameResultList(user.getId(), pStart, dto.getpSize());
			res.setData(list);
		}catch(Exception e){
			logger.error("queryNameResultList_err=",e);
		}
		
		return res;
	}
	
	/**
	 * 取名结果列表
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryNameResultListByRequireId")
	@ResponseBody
	public Response<Object> queryNameResultListByRequireId(@RequestBody OrderDto dto, HttpServletRequest request,HttpServletResponse response){
		Response<Object> res=new Response<Object>();
		
		try{
			if(dto.getRequireId()==null){
				logger.info("queryNameResultListByRequireId_no_requireId");
				res.setCode(ReturnCode.ERROR_03.getCode());
				res.setData(ReturnCode.ERROR_03.getMsg());
				return res;
			}
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			List<UserNames> list=memberService.queryNameResultListByRequireId(dto.getRequireId(), user.getId());
			res.setData(list);
		}catch(Exception e){
			logger.error("queryNameResultList_err=",e);
		}
		
		return res;
	}
	
	/**
	 * 名字详情
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getNameExplain")
    @ResponseBody
    public Response<Object> getNameExplain(@RequestBody Map<String,String> params, HttpServletRequest request, HttpServletResponse response) {
		logger.info("getNameExplain_params={}",JSON.toJSONString(params));
        Response<Object> res = new Response<Object>();
        MockApiClientFactory mf=MockApiClientFactory.newBuilder().appKey(config.getMockApiKey()).appSecret(config.getMockApiSecret()).build();
        if(!StringUtils.isEmpty(params.get("id"))){
        	Map<String,String> userNames=memberService.getUserNamesById(Long.valueOf(params.get("id").toString()));
        	params.clear();
        	params.putAll(userNames);
        	String sex=String.valueOf(userNames.get("sex"));
        	if("0".equals(sex)){
        		params.put("sex", "2");
        	}
        }else if(!StringUtils.isEmpty(params.get("testId"))){
        	TestNameInfo ti=testNameInfoService.getObjectById(Long.valueOf(params.get("testId")));
        	params.put("sex", String.valueOf(ti.getSex()));
        	params.put("surname", ti.getFamilyName());
        	params.put("combine",ti.getCombine());
        	params.put("birthday", ti.getBirthday());
        	params.put("homePlace", ti.getHomePlace());
        	if(ti.getLatitude()!=null && ti.getLongitude()!=null){
        		params.put("longitude", String.valueOf(ti.getLongitude()));
        		params.put("latitude",String.valueOf(ti.getLatitude()));
        	}
        	if("0".equals(String.valueOf(ti.getSex()))){
        		params.put("sex", "2");
        	}
        }
        
        ApiResponse apiResp=mf.NameExplain(params);
        logger.info("getNameExplain_params_1={}-{}-{}",JSON.toJSONString(params),apiResp.getStatusCode(),apiResp.getMessage());
        if(apiResp.getStatusCode()==200){
	        JSONObject result=JSON.parseObject(new String(apiResp.getBody()));
	        res.setData(result.getJSONObject("data"));
        }else{
        	logger.info("getNameExplain_err={}",apiResp.getStatusCode(),apiResp.getMessage());
        }
        

        return res;
    }
	
	/**
	 * 名字测评
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/testNameExplain")
    @ResponseBody
    public Response<Object> testNameExplain(@RequestBody Map<String,String> params, HttpServletRequest request, HttpServletResponse response) {
		logger.info("testNameExplain_params={}",JSON.toJSONString(params));
        Response<Object> res = new Response<Object>();
        MemberVo user=(MemberVo)request.getAttribute("userInfo");
        if(user!=null){
        	params.put("user_id", user.getId().toString());
        }else{
        	params.put("user_id","-1");
        }
        if(params.get("id")!=null){
        	TestNameInfo ti=testNameInfoService.getObjectById(Long.valueOf(params.get("id")));
        	params.put("sex", String.valueOf(ti.getSex()));
        	params.put("surname", ti.getFamilyName());
        	params.put("combine",ti.getCombine());
        	params.put("birthday", ti.getBirthday());
        	params.put("homePlace", ti.getHomePlace());
        	if(ti.getLatitude()!=null && ti.getLongitude()!=null){
        		params.put("longitude", String.valueOf(ti.getLongitude()));
        		params.put("latitude",String.valueOf(ti.getLatitude()));
        	}
        }
        if(params.get("sex")!=null && params.get("sex").equals("0")){
        	params.put("sex","2");
        }
        MockApiClientFactory mf=MockApiClientFactory.newBuilder().appKey(config.getMockApiKey()).appSecret(config.getMockApiSecret()).build();
        ApiResponse apiResp=mf.NameExplain(params);
        logger.info("testNameExplain_params_1={}-{}-{}",params,apiResp.getStatusCode(),apiResp.getMessage());
        if(apiResp.getStatusCode()==200){
	        JSONObject result=JSON.parseObject(new String(apiResp.getBody()));
	        res.setData(result.getJSONObject("data"));
        }
        return res;
    }
	
	/**
	 * 取消收藏
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doCollectionName")
    @ResponseBody
    public Response<Object> doCollectionName(@RequestBody UserNames params, HttpServletRequest request, HttpServletResponse response) {
		logger.info("doCollectionName_params={}",JSON.toJSONString(params));
        Response<Object> res = new Response<Object>();
        memberService.collectionName(params.getId(),params.getIsFavorite());

        return res;
    }
	
	/**
	 * 查询名字收藏
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryCollectionNameList")
    @ResponseBody
    public Response<Object> queryCollectionNameList(@RequestBody PageDto dto, HttpServletRequest request, HttpServletResponse response) {
        Response<Object> res = new Response<Object>();
        MemberVo user=(MemberVo)request.getAttribute("userInfo");
        Integer pStart=(dto.getpNo()-1)*dto.getpSize();
        List<UserNames> list=memberService.queryCollectionName(user.getId(), pStart, dto.getpSize());
        res.setData(list);
        return res;
    }
	
	/**
	 * 转发名字
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/transferCallback")
    @ResponseBody
    public Response<Object> transferCallback(@RequestBody UserNames params, HttpServletRequest request, HttpServletResponse response) {
		logger.info("transferCallback_params={}",JSON.toJSONString(params));
        Response<Object> res = new Response<Object>();
        if(params.getId()!=null){
        	memberService.addTransferNum(params.getId());
        }
        return res;
    }
	
	/**
	 * app起名登记
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/intelFornameRegister")
	@ResponseBody
	public Response<Object> intelFornameRegister(@RequestBody FornameRequirements dto, HttpServletRequest request,HttpServletResponse response){
		Response<Object> res=new Response<Object>();
		logger.info("intelFornameRegister_params={}",JSON.toJSONString(dto));
		try{
			if(StringUtils.isEmpty(dto.getPhone())){
				res.setCode(ReturnCode.ERROR_03.getCode());
				res.setMsg(ReturnCode.ERROR_03.getMsg());
				return res;
			}
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			if(user.getPhone()==null){
				memberService.bindingUserById(user.getId(), dto.getPhone());
				//更新订单的用户id
    			orderService.updateOrderUserByPhone(dto.getPhone(), user.getId());
			}
			dto.setUserId(user.getId());
			dto.setCreateTime(new Date());
			dto.setOperateType(0);
			OrderInfo oi=orderService.getLastOrderByUser(user.getId());
//			if(oi==null){
//				logger.info("intelFornameRegister_params_1={}",dto.getPhone(),user.getId());
//				res.setCode(ReturnCode.ERROR_1023.getCode());
//				res.setMsg(ReturnCode.ERROR_1023.getMsg());
//				return res;
//			}
			if(oi!=null){
				dto.setOrderNo(oi.getOrderNo());
			}
//			Integer count=memberService.isRegisterRequirements(user.getId());
//			if(count==0){
				memberService.addFornameRequirements(dto);
				res.setData(oi.getOrderNo());
//			}else{
//				logger.info("intelFornameRegister_repeat={}",dto.getPhone());
//				res.setCode(ReturnCode.ERROR_1013.getCode());
//				res.setMsg(ReturnCode.ERROR_1013.getMsg());
//			}
			return res;
		}catch(Exception e){
			logger.error("intelFornameRegister_err=",e);
		}
		
		return res;
	}
	
	/**
	 * 购买卡
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addCard")
	@ResponseBody
	public Response<WXRechargeVo> addCard(@RequestBody Map<String,Integer> params, HttpServletRequest request,HttpServletResponse response){
		Response<WXRechargeVo> res=new Response<WXRechargeVo>();
		try{
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			Map<String,String> cardMap=LocalCacheProvider.getInstance().getConfigMap();
			Integer cardAmount=Integer.valueOf(cardMap.get("CARD_"+params.get("cardNum")));
			if(params.isEmpty() || cardAmount==null){
				logger.info("addCard_err={}-{}",user.getId(),params);
				res.setCode(ReturnCode.ERROR_1024.getCode());
				res.setMsg(ReturnCode.ERROR_1024.getMsg());
				return res;
			}
			String whiteList=cardMap.get("WHITE_LIST");
			if(whiteList.contains(user.getOpenId())){
				cardAmount=1;
			}
			Date now=new Date();
			CardOrderInfo order=new CardOrderInfo();
			order.setAmount(cardAmount);
			order.setCreateTime(now);
			order.setNum(params.get("cardNum"));
			order.setOrderStatus(0);
			order.setPayTime(now);
			order.setPayType(0);
			order.setRefundStatus(0);
			order.setUpdateBy(user.getId());
			order.setUpdateTime(now);
			order.setUserId(user.getId());
			order.setUnLock(0);
			cardService.addCardOrder(order);
			
			TransactionInfo ti=new TransactionInfo();
			ti.setAmount(order.getAmount());
			ti.setOrderNo(order.getOrderNo());
			ti.setRemark("购买高分卡");
			ti.setUserId(order.getUserId().toString());
			ti.setWxOpenid(user.getOpenId());
			ti.setBusinessType(PayTypeEnum.TYPE_106.getType());
			WXRechargeVo result=wxPayService.payOrder(ti);
			logger.info("addCard_result={}",result);
			if(!result.checkSuccess()){
				logger.info("addCard_err={}",JSON.toJSONString(result));
				res.setCode(ReturnCode.ERROR_01.getCode());
				res.setMsg(result.getErrCodeDes());
				return res;
			}
			res.setData(result);
		}catch(Exception e){
			logger.error("addCard_err=",e);
		}
		return res;
	}
	
	/**
	 * 解锁高分模式
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/unlock")
	@ResponseBody
	public Response<WXRechargeVo> unlock(@RequestBody Map<String,Integer> params, HttpServletRequest request,HttpServletResponse response){
		Response<WXRechargeVo> res=new Response<WXRechargeVo>();
		try{
			String app=request.getHeader("App");
			Map<String,String> configMap=LocalCacheProvider.getInstance().getConfigMap();
			//白名单
			String whiteList=configMap.get("WHITE_LIST");
			Integer cardAmount=Integer.valueOf(configMap.get("CARD_0"));
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			if(whiteList.contains(user.getOpenId())){
				cardAmount=1;
			}
			Date now=new Date();
			CardOrderInfo order=new CardOrderInfo();
			order.setAmount(cardAmount);
			order.setCreateTime(now);
			order.setNum(0);
			order.setOrderStatus(0);
			order.setPayTime(now);
			order.setPayType(0);
			order.setRefundStatus(0);
			order.setUpdateBy(user.getId());
			order.setUpdateTime(now);
			order.setUserId(user.getId());
			order.setUnLock(1);
			cardService.addCardOrder(order);
			
			TransactionInfo ti=new TransactionInfo();
			ti.setAmount(order.getAmount());
			ti.setOrderNo(order.getOrderNo());
			ti.setRemark("解锁高分卡");
			ti.setUserId(order.getUserId().toString());
			ti.setWxOpenid(user.getOpenId());
			ti.setBusinessType(PayTypeEnum.TYPE_106.getType());
			WXRechargeVo result=null;
			if(app!=null){
				result=wxPayService.payAppOrder(ti);
			}else{
				result=wxPayService.payOrder(ti);
			}
			logger.info("unlock_result={}",JSON.toJSONString(result));
			if(!result.checkSuccess()){
				logger.info("addCard_err={}",JSON.toJSONString(result));
				res.setCode(ReturnCode.ERROR_01.getCode());
				res.setMsg(result.getErrCodeDes());
				return res;
			}
			res.setData(result);
		}catch(Exception e){
			logger.error("addCard_err=",e);
		}
		return res;
	}
	
	
	/**
	 * 高分卡使用记录
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryCardRecordList")
	@ResponseBody
	public Response<Object> queryCardRecordList(@RequestBody Map<String,Integer> params, HttpServletRequest request,HttpServletResponse response){
		Response<Object> res=new Response<Object>();
		try{
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			Set<Long> requireSet=new HashSet<Long>();
			List<UserNames> nameList=cardService.queryCardRecordList(user.getId());
			for(UserNames un:nameList){
				requireSet.add(un.getRequireId());
			}
			if(requireSet.size()==0){
				return res;
			}
			List<Long> requireIds=new ArrayList<Long>(requireSet);
			List<List<Map<String,Object>>> resultList=new ArrayList<List<Map<String,Object>>>();
			Map<Long,FornameRequirements> requirementsMap=fornameRequirementsService.getRequirementsMapByIds(requireIds);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			int i=0;

			List<Map<String,Object>> dataList=null;
			Map<String,Object> data=new HashMap<String,Object>();
			Long batchId=null;
			for(UserNames un:nameList){
				if(!un.getBatchId().equals(batchId)){
					i=0;
					data=new HashMap<String,Object>();
					dataList=new ArrayList<Map<String,Object>>();
					resultList.add(dataList);
					batchId=un.getBatchId();
				}
				if(i==0){
					FornameRequirements require=requirementsMap.get(un.getRequireId());
					data.put("familyName", require.getFamilyName());
					data.put("sex", require.getSex());
					data.put("usedTime", sdf.format(un.getCreateTime()));
					dataList.add(data);
				}
				Map<String,Object> subData=new HashMap<String,Object>();
				subData.put("fullName", un.getSurname()+un.getCombine());
				subData.put("id", un.getId());
				dataList.add(subData);
				i++;
			}
			res.setData(resultList);
		}catch(Exception e){
			logger.error("queryCardRecordList_err=",e);
		}
		return res;
	}
	
	/**
	 * 人工取名起名登记
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/personFornameRegister")
	@ResponseBody
	public Response<Object> personFornameRegister(@RequestBody RequirementsDto dto, HttpServletRequest request,HttpServletResponse response){
		Response<Object> res=new Response<Object>();
		logger.info("personFornameRegister_params={}",JSON.toJSONString(dto));
		try{
			if(StringUtils.isEmpty(dto.getPhone()) || dto.getMealType()==null || (dto.getMealType()!=0 && dto.getMealType()!=1)){
				res.setCode(ReturnCode.ERROR_02.getCode());
				res.setMsg(ReturnCode.ERROR_02.getMsg());
				return res;
			}
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			Date now=new Date();

			String orderNo="WX_"+orderNoService.getOrderNo(dto.getPhone());
			OrderInfo data=new OrderInfo();
			Map<String,String> configMap=LocalCacheProvider.getInstance().getConfigMap();
			String whiteList=configMap.get("WHITE_LIST");
			String amount=configMap.get("MEAL_"+dto.getMealType());
			if(whiteList.contains(user.getOpenId())){
				amount="1";
			}
			data.setAmount(Integer.valueOf(amount));
			data.setActualAmount(data.getAmount());
			data.setOrderNo(orderNo);
			data.setOrderStatus(0);
			data.setCreateTime(now);
			data.setUpdateTime(now);
			data.setPhone(dto.getPhone());
			data.setUserId(user.getId());
			data.setPayTime(now);
			data.setChannel(0);
			orderService.addOrder(data);
			
			FornameRequirements require=new FornameRequirements();
			BeanUtils.copyProperties(dto, require);
			require.setUserId(user.getId());
			require.setCreateTime(new Date());
			require.setOrderNo(data.getOrderNo());
			memberService.addFornameRequirements(require);
			
			TransactionInfo ti=new TransactionInfo();
			ti.setAmount(data.getActualAmount());
			ti.setOrderNo(data.getOrderNo());
			ti.setRemark("人工起名服务");
			ti.setUserId(data.getUserId().toString());
			ti.setWxOpenid(user.getOpenId());
			ti.setBusinessType(PayTypeEnum.TYPE_107.getType());
			WXRechargeVo result=wxPayService.payOrder(ti);
			if(!result.checkSuccess()){
				logger.info("personFornameRegister_err={}",JSON.toJSONString(result));
				res.setCode(ReturnCode.ERROR_01.getCode());
				res.setMsg(result.getErrCodeDes());
				return res;
			}
			res.setData(result);
			return res;
		}catch(Exception e){
			logger.error("personFornameRegister_err=",e);
		}
		
		return res;
	}
	
	/**
	 * 变换名字
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/changeNameList")
	@ResponseBody
	public Response<Object> changeNameList(@RequestBody Map<String,Object> dto, HttpServletRequest request,HttpServletResponse response){
		Response<Object> res=new Response<Object>();
		logger.info("changeName_params={}",JSON.toJSONString(dto));
		try{
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			Date now=new Date();
			if(dto.get("id")==null || (dto.get("middleName")==null && dto.get("lastName")==null)){
				res.setCode(ReturnCode.ERROR_03.getCode());
				res.setMsg(ReturnCode.ERROR_03.getMsg());
				return res;
			}
			
			return res;
		}catch(Exception e){
			logger.error("changeName_err=",e);
		}
		
		return res;
	}
	
	/**
	 * 免费起名
	 * @param require
	 * @param request
	 * @param response
	 * @return
	 */
	private Response<Map<String,Object>> freeIntelForname(RequirementsDto require, HttpServletRequest request){
		Response<Map<String,Object>> res=new Response<Map<String,Object>>();
		logger.info("freeIntelForname_params={}",JSON.toJSONString(require));
		Map<String,Object> data=new HashMap<String,Object>();
		Long requireId=require.getRequireId();
		try{
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			FornameRequirements dto=null;
			if(requireId!=null){
				dto=fornameRequirementsService.getObjectById(requireId);
			}else{
				dto=new FornameRequirements();
				BeanUtils.copyProperties(require, dto);
				dto.setUserId(user.getId());
				dto.setCreateTime(new Date());
				requireId=memberService.addFornameRequirements(dto);
			}
			String sex=String.valueOf(dto.getSex());
			if(sex.equals("0")){
				sex="2";
			}
			MockApiClientFactory mf=MockApiClientFactory.newBuilder().appKey(config.getMockApiKey()).appSecret(config.getMockApiSecret()).build();
	        Map<String,String> params=new HashMap<String,String>();
	        params.put("user_id", user.getId().toString());
	        params.put("surname", dto.getFamilyName());
	        params.put("sex", sex);
	        params.put("birthday", dto.getBirthday());
	        params.put("name_type", dto.getNameType().toString());
	        params.put("exclude_char", dto.getLimitName());
	        if(dto.getMiddleName()!=null && !dto.getMiddleName().equals("")){
	        	params.put("specify_char1", dto.getMiddleName());
	        }else if(dto.getLastName()!=null && !dto.getLastName().equals("")){
	        	params.put("specify_char2", dto.getLastName());
	        }
	        params.put("record_count", "20");
	        logger.info("freeIntelForname_params_1={}",JSON.toJSONString(params));
	        long t=System.currentTimeMillis();
			ApiResponse apiResp=mf.NameGeneratorLimit(params);
			JSONObject result=JSON.parseObject(new String(apiResp.getBody()));
			if(result==null || result.getInteger("code")!=0){
				logger.info("freeIntelForname_params_2={}",result);
				res.setCode(result.getInteger("code"));
				res.setMsg(result.getString("error_msg"));
				return res;
			}
			logger.info("freeIntelForname_time={}",System.currentTimeMillis()-t);
			JSONObject nameData=result.getJSONObject("data");
			
			if(result.getInteger("code")==0 && !nameData.isEmpty()){
				JSONObject list=result.getJSONObject("data");
				List<UserNames> userList=memberService.addUserNames(list,user.getId(),requireId,null,0);
				data.put("list", userList);
				data.put("count", userList.size());
			}

		}catch(Exception e){
			logger.error("freeIntelForname_err=",e);
		}
		data.put("requireId", requireId);
		res.setData(data);
		return res;
	}
	
	/**
	 * 收费起名
	 * @param require
	 * @param request
	 * @return
	 */
	private Response<Map<String,Object>> chargeIntelForname(RequirementsDto require, HttpServletRequest request,Long batchId){
		Response<Map<String,Object>> res=new Response<Map<String,Object>>();
		logger.info("chargeIntelForname_params={}",JSON.toJSONString(require));
		Map<String,Object> data=new HashMap<String,Object>();
		Long requireId=require.getRequireId();
		try{
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			FornameRequirements dto=null;
			if(requireId!=null){
				dto=fornameRequirementsService.getObjectById(requireId);
			}else{
				dto=new FornameRequirements();
				BeanUtils.copyProperties(require, dto);
				dto.setUserId(user.getId());
				dto.setCreateTime(new Date());
				requireId=memberService.addFornameRequirements(dto);
			}
			
			MockApiClientFactory mf=MockApiClientFactory.newBuilder().appKey(config.getMockApiKey()).appSecret(config.getMockApiSecret()).build();
			String sex=String.valueOf(dto.getSex());
			if(sex.equals("0")){
				sex="2";
			}
			Map<String,String> params=new HashMap<String,String>();
	        params.put("user_id", user.getId().toString());
	        params.put("surname", dto.getFamilyName());
	        params.put("sex", sex);
	        params.put("birthday", dto.getBirthday());
	        params.put("name_type", dto.getNameType().toString());
	        params.put("exclude_char", dto.getLimitName());
	        if(dto.getMiddleName()!=null && !dto.getMiddleName().equals("")){
	        	params.put("specify_char1", dto.getMiddleName());
	        }else if(dto.getLastName()!=null && !dto.getLastName().equals("")){
	        	params.put("specify_char2", dto.getLastName());
	        }
	        if(dto.getMidFiveElements()!=null && !dto.getMidFiveElements().equals("")){
	        	params.put("specify_wuxing1", dto.getMidFiveElements());
	        }
	        if(dto.getLastFiveElements()!=null && !dto.getLastFiveElements().equals("")){
	        	params.put("specify_wuxing2", dto.getLastFiveElements());
	        }
	        params.put("wuxing_recommend_flag", dto.getSysRecommend().toString());
	        params.put("shengxiao_suitable_flag", dto.getIsPunching().toString());
	        params.put("shengxiao_bushou_suitable_flag", dto.getIsTaboo().toString());
	        params.put("wuge_all_suitable_flag", dto.getFiveLuckyNum().toString());
	        params.put("wuge_primary_suitable_flag", dto.getThreeLuckyNum().toString());
	        params.put("record_count", "20");
	        logger.info("chargeIntelForname_params_1={}",JSON.toJSONString(params));
	        long t=System.currentTimeMillis();
			ApiResponse apiResp=mf.NameGenerator(params);
			JSONObject result=JSON.parseObject(new String(apiResp.getBody()));
			if(result==null || result.getInteger("code")!=0){
				logger.info("chargeIntelForname_params_2={}",result);
				res.setCode(result.getInteger("code"));
				res.setMsg(result.getString("error_msg"));
				return res;
			}
			logger.info("chargeIntelForname_time={}",System.currentTimeMillis()-t);
			JSONObject nameData=result.getJSONObject("data");
			
			if(result.getInteger("code")==0 && !nameData.isEmpty()){
				JSONObject list=result.getJSONObject("data");
				List<UserNames> userList=memberService.addUserNames(list,user.getId(),requireId,batchId,1);
				//减少高分卡数
				if(userList.size()>=20){
					cardService.reduceUserCard(user.getId());
				}
				data.put("list", userList);
				data.put("count", userList.size());
			}

		}catch(Exception e){
			logger.error("chargeIntelForname_err=",e);
		}
		data.put("requireId", requireId);
		res.setData(data);
		return res;
	}
	
	/**
	 * 获取统计信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getStatisData")
	@ResponseBody
	public Response<Object> getStatisData(HttpServletRequest request,HttpServletResponse response){
		Response<Object> res=new Response<Object>();
		Map<String,Object> resData=new HashMap<String,Object>();
		Map<String,String> configMap=LocalCacheProvider.getInstance().getConfigMap();
		int card1=Integer.parseInt(configMap.get("CARD_10"));
		int card2=Integer.parseInt(configMap.get("CARD_25"));
		int card3=Integer.parseInt(configMap.get("CARD_45"));
		int card4=Integer.parseInt(configMap.get("CARD_0"));
		String cardJson="[{\"key\":10,\"value\":"+card1+"},{\"key\":25,\"value\":"+card2+"},{\"key\":45,\"value\":"+card3+"},{\"key\":68,\"value\":"+card4+"}]";
		JSONArray cardList=JSON.parseArray(cardJson);
		
		int meal1=Integer.parseInt(configMap.get("MEAL_29800"));
		int meal2=Integer.parseInt(configMap.get("MEAL_68800"));
		String mealJSON="[{\"key\":298,\"value\":"+meal1+"},{\"key\":688,\"value\":"+meal2+"}]";
		JSONArray mealList=JSON.parseArray(mealJSON);
		MemberVo user=(MemberVo)request.getAttribute("userInfo");
		
		MemberVo userVo=memberService.getMemberDetail(user.getId());
		resData.putAll(memberService.getUserStatData(userVo.getId()));
		resData.put("unLock", userVo.getUnLock());
		resData.put("cardNum", userVo.getCardNum());
		resData.put("usedNum", userVo.getUsedNum());
		resData.put("cardList", cardList);
		resData.put("mealList", mealList);
		resData.put("userType", userVo.getUserType());
 		res.setData(resData);
		return res;
	}
	
	/**
	 * app购买卡
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addAppCard")
	@ResponseBody
	public Response<WXRechargeVo> addAppCard(@RequestBody Map<String,Integer> params, HttpServletRequest request,HttpServletResponse response){
		Response<WXRechargeVo> res=new Response<WXRechargeVo>();
		try{
			String userIp = HttpRequestUtil.getRemoteIp(request);
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			Map<String,String> cardMap=LocalCacheProvider.getInstance().getConfigMap();
			Integer cardAmount=Integer.valueOf(cardMap.get("CARD_"+params.get("cardNum")));
			if(params.isEmpty() || cardAmount==null){
				logger.info("addAppCard_err={}-{}",user.getId(),params);
				res.setCode(ReturnCode.ERROR_1024.getCode());
				res.setMsg(ReturnCode.ERROR_1024.getMsg());
				return res;
			}
			String whiteList=cardMap.get("WHITE_LIST");
			if(whiteList.contains(user.getOpenId())){
				cardAmount=1;
			}
			Date now=new Date();
			CardOrderInfo order=new CardOrderInfo();
			order.setAmount(cardAmount);
			order.setCreateTime(now);
			order.setNum(params.get("cardNum"));
			order.setOrderStatus(0);
			order.setPayTime(now);
			order.setPayType(0);
			order.setRefundStatus(0);
			order.setUpdateBy(user.getId());
			order.setUpdateTime(now);
			order.setUserId(user.getId());
			order.setUnLock(0);
			cardService.addCardOrder(order);
			
			TransactionInfo ti=new TransactionInfo();
			ti.setUserIp(userIp);
			ti.setSource(1);
			ti.setAmount(order.getAmount());
			ti.setOrderNo(order.getOrderNo());
			ti.setRemark("购买高分卡");
			ti.setUserId(order.getUserId().toString());
			ti.setWxOpenid(user.getOpenId());
			ti.setBusinessType(PayTypeEnum.TYPE_106.getType());
			WXRechargeVo result=wxPayService.payAppOrder(ti);
			logger.info("addAppCard_result={}",result);
			if(!result.checkSuccess()){
				logger.info("addAppCard_err={}",JSON.toJSONString(result));
				res.setCode(ReturnCode.ERROR_01.getCode());
				res.setMsg(result.getErrCodeDes());
				return res;
			}
			res.setData(result);
		}catch(Exception e){
			logger.error("addAppCard_err=",e);
		}
		return res;
	}
	
	/**
	 * app人工取名起名登记
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/appPersonFornameRegister")
	@ResponseBody
	public Response<Object> appPersonFornameRegister(@RequestBody RequirementsDto dto, HttpServletRequest request,HttpServletResponse response){
		Response<Object> res=new Response<Object>();
		logger.info("appPersonFornameRegister_params={}",JSON.toJSONString(dto));
		try{
			Map<String,String> configMap=LocalCacheProvider.getInstance().getConfigMap();
			Integer mealType=dto.getMealType();
			String amount=configMap.get("MEAL_"+mealType);
			if(StringUtils.isEmpty(dto.getPhone()) || mealType==null || amount==null){
				res.setCode(ReturnCode.ERROR_02.getCode());
				res.setMsg(ReturnCode.ERROR_02.getMsg());
				return res;
			}
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
			Date now=new Date();

			String orderNo="MNS_"+orderNoService.getOrderNo(dto.getPhone());
			OrderInfo data=new OrderInfo();
			
			String whiteList=configMap.get("WHITE_LIST");
			if(whiteList.contains(user.getOpenId())){
				amount="1";
			}
			data.setAmount(Integer.valueOf(amount));
			data.setActualAmount(data.getAmount());
			data.setOrderNo(orderNo);
			data.setOrderStatus(0);
			data.setCreateTime(now);
			data.setUpdateTime(now);
			data.setPhone(dto.getPhone());
			data.setUserId(user.getId());
			data.setPayTime(now);
			data.setChannel(2);
			orderService.addOrder(data);
			
			FornameRequirements require=new FornameRequirements();
			BeanUtils.copyProperties(dto, require);
			require.setUserId(user.getId());
			require.setCreateTime(new Date());
			require.setOrderNo(data.getOrderNo());
			memberService.addFornameRequirements(require);
			
			TransactionInfo ti=new TransactionInfo();
			ti.setAmount(data.getActualAmount());
			ti.setOrderNo(data.getOrderNo());
			ti.setRemark("人工起名服务");
			ti.setUserId(data.getUserId().toString());
			ti.setWxOpenid(user.getOpenId());
			ti.setBusinessType(PayTypeEnum.TYPE_107.getType());
			WXRechargeVo result=wxPayService.payAppOrder(ti);
			if(!result.checkSuccess()){
				logger.info("appPersonFornameRegister_err={}",JSON.toJSONString(result));
				res.setCode(ReturnCode.ERROR_01.getCode());
				res.setMsg(result.getErrCodeDes());
				return res;
			}
			res.setData(result);
			return res;
		}catch(Exception e){
			logger.error("appPersonFornameRegister_err=",e);
		}
		
		return res;
	}
	
}
