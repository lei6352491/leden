//package com.zhouyi.business.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.zhouyi.business.core.common.ReturnCode;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.zhouyi.business.common.LocalCacheProvider;
//import com.zhouyi.business.core.model.Response;
//import com.zhouyi.business.core.service.MemberService;
//import com.zhouyi.business.core.vo.MemberVo;
//
//@Controller
//@RequestMapping("/api/user")
//public class AppController {
//	private static final Logger logger = LoggerFactory.getLogger(AppController.class);
//
//	@Autowired
//	private MemberService memberService;
//
//	/**
//	 * 登录
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/getUserInfoById")
//	@ResponseBody
//	public Response<Object> getUserInfoById(@RequestBody Map<String,Long> dto, HttpServletRequest request,HttpServletResponse response){
//		logger.info("app_login_params={}",JSON.toJSONString(dto));
//		Response<Object> res=new Response<Object>();
//		Map<String,Object> resData=new HashMap<String,Object>();
//
//		MemberVo userVo=new MemberVo();
//		if(StringUtils.isEmpty(dto.get("id"))){
//			res.setCode(ReturnCode.ERROR_03.getCode());
//			res.setMsg(ReturnCode.ERROR_03.getMsg());
//			return res;
//		}
//
//		userVo=memberService.getMemberDetail(dto.get("id"));
//
//		resData.put("id", userVo.getId());
//		resData.put("nickName", userVo.getNickName());
//		resData.put("userType", userVo.getUserType());
//		resData.put("headImage", userVo.getHeadImage());
//		resData.put("sex", userVo.getSex());
//		resData.putAll(memberService.getUserStatData(userVo.getId()));
//		resData.put("unLock", userVo.getUnLock());
//		resData.put("cardNum", userVo.getCardNum());
//		resData.put("usedNum", userVo.getUsedNum());
// 		res.setData(resData);
//		return res;
//	}
//
//	/**
//	 * 获取统计信息
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/getVersion")
//	@ResponseBody
//	public Response<Object> getVersion(@RequestBody Map<String,String> params,HttpServletRequest request,HttpServletResponse response){
//		Response<Object> res=new Response<Object>();
//		if(StringUtils.isEmpty(params.get("code")) || StringUtils.isEmpty(params.get("channel"))){
//			res.setCode(ReturnCode.ERROR_02.getCode());
//			res.setMsg(ReturnCode.ERROR_02.getMsg());
//			return res;
//		}
//		logger.info("getVersion_params={}",JSON.toJSONString(params));
//		String channel=params.get("channel");
//		Integer code=Integer.parseInt(params.get("code"));
//		int type = 0;
//		if (StringUtils.isEmpty(params.get("type"))) {
//			type =0;
//		} else {
//			type =Integer.parseInt(params.get("type"));
//		}
//		Map<String,Object> resData=new HashMap<String,Object>();
//		Map<String,String> configMap=LocalCacheProvider.getInstance().getConfigMap();
//		String appStr="";
//		if(type == 0) {
//			appStr=configMap.get("ZHOU_YI_VERSION");
//		}
//		if(type == 1) {
//			appStr=configMap.get("MEI_XIANG_VERSION");
//		}
//		JSONObject json=JSONObject.parseObject(appStr);
//		String app=json.getString(channel);
//		if(StringUtils.isEmpty(app)) {
//			app = json.getString("developer-default");
//		}
//		String apps[]=app.split("\\|");
//		resData.put("isRelogin", apps[5]);
//		resData.put("curVersion", apps[1]);
//		resData.put("isForce", apps[0]);
//		if(code<Integer.parseInt(apps[1])){
//			resData.put("isUpdate", 1);
//		}else{
//			resData.put("isUpdate", 0);
//		}
//		resData.put("description", apps[3]);
//		resData.put("url", apps[4]);
// 		res.setData(resData);
//		return res;
//	}
//}
