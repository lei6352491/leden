package com.zhouyi.business.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhouyi.business.core.common.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.common.LocalCacheProvider;
import com.zhouyi.business.core.common.Config;
import com.zhouyi.business.core.common.WeChatUserInfo;
import com.zhouyi.business.core.model.MemberAuth;
import com.zhouyi.business.core.model.MemberInfo;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.MemberService;
import com.zhouyi.business.core.utils.TokenUtil;
import com.zhouyi.business.core.utils.WXUtil;
import com.zhouyi.business.core.vo.MemberVo;
import com.zhouyi.business.dto.UserDto;
import com.zhouyi.business.model.LoginData;
import com.zhouyi.business.model.UserInfoDto;

@Controller
@RequestMapping("/api/user")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Config config;
	
	/*@Autowired
	private ConfigService configService;*/
	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Response<Object> login(@RequestBody UserDto dto, HttpServletRequest request,HttpServletResponse response){
		logger.info("wx_login_params={}",JSON.toJSONString(dto));
		Response<Object> res=new Response<Object>();
		Map<String,Object> resData=new HashMap<String,Object>();

		MemberVo userVo=new MemberVo();
		if(dto.getCode()==null){
			res.setCode(ReturnCode.ERROR_1001.getCode());
			res.setMsg(ReturnCode.ERROR_1001.getMsg());
			return res;
		}
		WeChatUserInfo weChatInfo=null;
		if(!dto.getCode().equals("test")){
			weChatInfo=WXUtil.getWeChatInfo(dto.getCode(), config.getWxAppId(), config.getWxAppSecret());
			if(weChatInfo.getOpenid()==null){
				res.setCode(ReturnCode.ERROR_1004.getCode());
				res.setMsg(ReturnCode.ERROR_1004.getMsg());
				return res;
			}
		}else{
			String json="{\"openid\":\"oZMcW0pGwGAGlYwGXOEkcFd3XH0c\",\"nickname\":\"啊健\",\"sex\":1,\"language\":\"zh_CN\",\"city\":\"深圳\",\"province\":\"广东\",\"country\":\"中国\",\"headimgurl\":\"http://thirdwx.qlogo.cn/mmopen/vi_32/4Ijz7gf1qrkpDnus9FGpWhRcQ2ibY6guxMsN0BjJ781ekBRiafdtZ8iboLWPoLYKwSBax3ib5RETXQiakib8Xh8S0nfw/132\",\"privilege\":[\"chinaunicom\"]}";
			weChatInfo=JSON.parseObject(json, WeChatUserInfo.class);
		}
		Map<String,LoginData> loginMap=LocalCacheProvider.getInstance().getLoginMap();
		Map<String,String> tokenMap=LocalCacheProvider.getInstance().getUserTokenMap();
		MemberAuth auth=memberService.getAuthInfoByOpenId(weChatInfo.getOpenid(), 101);
		Long memberId=null;
		String token=null;
		if(auth==null){
			MemberInfo user=new MemberInfo();
			user.setNickName(weChatInfo.getNickname());
			user.setHeadImage(weChatInfo.getHeadimgurl());
			String sex=(weChatInfo.getSex()==null || weChatInfo.getSex().equals("2"))?"0":weChatInfo.getSex();
			user.setSex(Integer.parseInt(sex));
			user.setUnionId(weChatInfo.getUnionId());
			user.setUserType(0);
			user.setPortalType(101);
			
			memberService.saveMemberInfo(user, weChatInfo);
			memberId=user.getId();
		}else{
			memberId=auth.getMemberId();
			token=tokenMap.get(memberId.toString());
			if(token!=null){
				loginMap.remove(token);
			}
			
		}
		token=TokenUtil.createToken();
		tokenMap.put(memberId.toString(), token);
		userVo=memberService.getMemberDetail(memberId);
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		LoginData ld=new LoginData();
		ld.setAccessToken(token);
		ld.setExpiresTime(cal.getTime());
		ld.setMember(userVo);
		loginMap.put(token, ld);
		
		resData.put("id", userVo.getId());
		resData.put("nickName", userVo.getNickName());
		resData.put("userType", userVo.getUserType());
		resData.put("headImage", userVo.getHeadImage());
		resData.put("sex", userVo.getSex());
		resData.put("openMemberFee", LocalCacheProvider.getInstance().getConfigMap().get("OPEN_MEMBER_COST"));
		resData.putAll(memberService.getUserStatData(userVo.getId()));
		resData.put("unLock", userVo.getUnLock());
		resData.put("cardNum", userVo.getCardNum());
		resData.put("usedNum", userVo.getUsedNum());
		TokenUtil.setCookie("access_token", token, response);
 		res.setData(resData);
		return res;
	}
	
	/*@RequestMapping("/reloadConfig")
    public void reloadConfig(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> map=LocalCacheProvider.getInstance().getConfigMap();
		List<ConfigInfo> list=configService.getAllList();
		for(ConfigInfo ci:list){
			map.put(ci.getConfNo(), ci.getConfValue());
		}
    }*/
	
	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/selfLogin")
	@ResponseBody
	public Response<Object> selfLogin(@RequestBody UserDto dto, HttpServletRequest request,HttpServletResponse response){
		logger.info("wx_login_params={}",JSON.toJSONString(dto));
		Response<Object> res=new Response<Object>();
		Map<String,Object> resData=new HashMap<String,Object>();

		MemberVo userVo=new MemberVo();
		if(dto.getOpenId()==null){
			res.setCode(ReturnCode.ERROR_1001.getCode());
			res.setMsg(ReturnCode.ERROR_1001.getMsg());
			return res;
		}
		WeChatUserInfo weChatInfo=WXUtil.getWeChatDetail(dto.getOpenId(), config.getWxAppId(), config.getWxAppSecret());
		if(weChatInfo.getOpenid()==null){
			res.setCode(ReturnCode.ERROR_1004.getCode());
			res.setMsg(ReturnCode.ERROR_1004.getMsg());
			return res;
		};
		
		MemberAuth auth=memberService.getAuthInfoByOpenId(weChatInfo.getOpenid(), 101);
		Long memberId=null;
		if(auth==null){
			MemberInfo user=new MemberInfo();
			user.setNickName(weChatInfo.getNickname());
			user.setHeadImage(weChatInfo.getHeadimgurl());
			String sex=(weChatInfo.getSex()==null || weChatInfo.getSex().equals("2"))?"0":weChatInfo.getSex();
			user.setSex(Integer.parseInt(sex));
			user.setUnionId(weChatInfo.getUnionId());
			user.setUserType(0);
			user.setPortalType(101);
			
			memberService.saveMemberInfo(user, weChatInfo);
			memberId=user.getId();
		}else{
			memberId=auth.getMemberId();
		}
		userVo=memberService.getMemberDetail(memberId);
		Map<String,LoginData> loginMap=LocalCacheProvider.getInstance().getLoginMap();
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String token=TokenUtil.createToken();
		LoginData ld=new LoginData();
		ld.setAccessToken(token);
		ld.setExpiresTime(cal.getTime());
		ld.setMember(userVo);
		loginMap.put(token, ld);
		
		resData.put("id", userVo.getId());
		resData.put("nickName", userVo.getNickName());
		resData.put("userType", userVo.getUserType());
		resData.put("headImage", userVo.getHeadImage());
		resData.put("sex", userVo.getSex());
		resData.put("openMemberFee", LocalCacheProvider.getInstance().getConfigMap().get("OPEN_MEMBER_COST"));
		resData.putAll(memberService.getUserStatData(userVo.getId()));
		TokenUtil.setCookie("access_token", token, response);
 		res.setData(resData);
		return res;
	}
	
	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/appLogin")
	@ResponseBody
	public Response<Object> appLogin(@RequestBody UserInfoDto dto, HttpServletRequest request,HttpServletResponse response){
		logger.info("app_login_params={}",JSON.toJSONString(dto));
		Response<Object> res=new Response<Object>();
		Map<String,Object> resData=new HashMap<String,Object>();

		MemberVo userVo=new MemberVo();
		if(StringUtils.isEmpty(dto.getOpenId())){
			res.setCode(ReturnCode.ERROR_03.getCode());
			res.setMsg(ReturnCode.ERROR_03.getMsg());
			return res;
		}
		
		Map<String,LoginData> loginMap=LocalCacheProvider.getInstance().getLoginMap();
		Map<String,String> tokenMap=LocalCacheProvider.getInstance().getUserTokenMap();
		MemberAuth auth=memberService.getAuthInfoByOpenId(dto.getOpenId(), 102);
		Long memberId=null;
		String token=null;
		if(auth==null){
			MemberInfo user=new MemberInfo();
			user.setNickName(dto.getNickName());
			user.setHeadImage(dto.getHeadimgurl());
			String sex=(dto.getSex()==null || dto.getSex().equals("2"))?"0":dto.getSex();
			user.setSex(Integer.parseInt(sex));
			user.setUnionId(dto.getUnionId());
			user.setUserType(0);
			user.setPortalType(102);
			WeChatUserInfo weChatInfo=new WeChatUserInfo();
			BeanUtils.copyProperties(dto, weChatInfo);
			weChatInfo.setOpenid(dto.getOpenId());
			weChatInfo.setNickname(dto.getNickName());
			memberService.saveMemberInfo(user, weChatInfo);
			memberId=user.getId();
		}else{
			memberId=auth.getMemberId();
			token=tokenMap.get(memberId.toString());
			if(token!=null){
				loginMap.remove(token);
			}
			
		}
		token=TokenUtil.createToken();
		tokenMap.put(memberId.toString(), token);
		userVo=memberService.getMemberDetail(memberId);
		memberService.updateMemberToken(userVo.getId(), token);
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		LoginData ld=new LoginData();
		ld.setAccessToken(token);
		ld.setExpiresTime(cal.getTime());
		ld.setMember(userVo);
		loginMap.put(token, ld);
		
		resData.put("id", userVo.getId());
		resData.put("nickName", userVo.getNickName());
		resData.put("userType", userVo.getUserType());
		resData.put("headImage", userVo.getHeadImage());
		resData.put("sex", userVo.getSex());
		resData.put("openMemberFee", LocalCacheProvider.getInstance().getConfigMap().get("OPEN_MEMBER_COST"));
		resData.putAll(memberService.getUserStatData(userVo.getId()));
		resData.put("unLock", userVo.getUnLock());
		resData.put("cardNum", userVo.getCardNum());
		resData.put("usedNum", userVo.getUsedNum());
		resData.put("token", token);
 		res.setData(resData);
		return res;
	}
}
