package com.zhouyi.business.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.common.Config;
import com.zhouyi.business.core.model.MemberInfo;
import com.zhouyi.business.core.service.MemberService;
import com.zhouyi.business.core.service.OrderService;
import com.zhouyi.business.core.utils.WXSignUtil;
import com.zhouyi.business.model.ComplexButton;
import com.zhouyi.business.model.ImageMessage;
import com.zhouyi.business.model.Menu;
import com.zhouyi.business.model.TextMessage;
import com.zhouyi.business.model.ViewButton;
import com.zhouyi.business.utils.MenuUtil;
import com.zhouyi.business.utils.WXMessageUtil;
import com.zhouyi.business.utils.WXTokenUtil;

@Controller
@RequestMapping("/api/wxplatform")
@Api(hidden = true)
public class WXController {
	private static final Logger logger = LoggerFactory.getLogger(WXController.class);
	
	@Autowired
    private Config config;
	
	@Autowired
    private MemberService memberService;
	
	@Autowired
    private OrderService orderService;
	
	/**
	 * 自动回复
	 * @param dto
	 * @param request
	 * @return
	 */
	@RequestMapping("/autoReply")
	public void autoReply(HttpServletRequest request,HttpServletResponse response){
		
		TextMessage txtmsg=new TextMessage();
		PrintWriter out=null;
		try{
			logger.info("autoReply_params=");
			out = response.getWriter();
			if (StringUtils.isNotBlank(request.getParameter("signature"))) {
	            String signature = request.getParameter("signature");
	            String timestamp = request.getParameter("timestamp");
	            String nonce = request.getParameter("nonce");
	            String echostr = request.getParameter("echostr");
	            logger.info("signature[{}], timestamp[{}], nonce[{}], echostr[{}]", signature, timestamp, nonce, echostr);
	            if (WXSignUtil.checkSignature(signature, timestamp, nonce) && echostr!=null) {
	                logger.info("数据源为微信后台，将echostr[{}]返回！", echostr);
	                out.write(echostr);
	            }else{
	            	String outTxtmsg=null;
	            	Map<String,String> map = WXMessageUtil.xmlToMap(request);
	    			logger.info("autoReply_1={}",JSON.toJSONString(map));
	    	        String toUserName = map.get("ToUserName");
	    	        String fromUserName = map.get("FromUserName");
	    	        String msgType = map.get("MsgType");
	    	        String content = map.get("Content"); 
	    	        String msgId=map.get("MsgId");
	    	        txtmsg.setMsgId(msgId);
	    	        txtmsg.setToUserName(fromUserName);
	    	        txtmsg.setFromUserName(toUserName);
	    	        txtmsg.setCreateTime(new Date().getTime());
	    	        txtmsg.setMsgType(WXMessageUtil.MESSAGE_TEXT);
	    	        if(msgType.equals(WXMessageUtil.MESSAGE_TEXT)){
	    	        	if(content.equals("绑定")){
	    	        		txtmsg.setContent("请输入订单登记时的联系信息：BD|xxxxx");
	    	        	}else if(content.startsWith("BD")){
	    	        		logger.info("autoReply_content_1={}",content);
	    	        		String strs[]=content.split("\\|");
	    	        		MemberInfo mem=memberService.bindingUser(fromUserName, strs[1]);
	    	        		if(mem!=null){
	    	        			txtmsg.setContent("感谢您的关注,手机绑定成功.");
	    	        			//更新订单的用户id
	    	        			orderService.updateOrderUserByPhone(strs[1], mem.getId());
	    	        		}else{
	    	        			txtmsg.setContent("手机绑定失败,请重试或联系客服.");
	    	        		}
	    	        	}else{
	    	        		txtmsg.setContent("感谢关注周易起名大师,我们将为您提供专业的服务.");
	    	        	}
	    	        }else if(msgType.equals(WXMessageUtil.MESSAGE_EVENT)){
	    	        	String eventType = map.get("Event");  
	    	        	String eventKey = map.get("EventKey");
	    	        	if(eventType.equals(WXMessageUtil.EVENT_CLICK)){
		    	        	if(eventKey.equals("bangding")){
		    	        		txtmsg.setContent("请回复\"BD|xxxxxx\"绑定号码,以便跟踪订单信息.");
		    	        	}else if(eventKey.equals("customer001")){
		    	        		ImageMessage im=new ImageMessage();
		    	        		im.setMediaId("mYmW1199wp8RWWmyQbGLNwQod7D3YVeFODgqCnO1e6g");
		    	        		txtmsg.setImage(im);
		    	        		txtmsg.setMsgType(WXMessageUtil.MESSAGE_IMAGE);
		    	        		outTxtmsg=WXMessageUtil.newMessageToXml(txtmsg);
		    	        	}
	    	        	}
	    	        }else{
	    	        	txtmsg.setContent("感谢关注周易起名大师,我们将为您提供专业的服务.");
	    	        }
	    	        if(outTxtmsg==null){
	    	        	outTxtmsg=WXMessageUtil.textMessageToXml(txtmsg);
	    	        }
	    	        logger.info("autoReply_result={}",outTxtmsg);
	    	        out.write(outTxtmsg);
	            }
	        }
			out.flush();
			
		}catch(Exception e){
			logger.info("autoReply_err=",e);
			txtmsg.setContent("服务在升级中.");
			out.write(WXMessageUtil.textMessageToXml(txtmsg));
		}
		out.close();
		
	}
	
	/**
	 * 创建菜单
	 * @param request
	 * @param response
	 */
	@RequestMapping("/createMenu")
	public void createMenu(HttpServletRequest request,HttpServletResponse response){
		logger.info("createMenu_params=111111");
		String token=WXTokenUtil.getAccessToken(config.getWxAppId(), config.getWxAppSecret());
		int status = MenuUtil.createMenu(MenuUtil.getMenu(),token);
		if(status==0){
			logger.info("createMenu_result={}-{}",status,"菜单创建成功！");
		}else{
			logger.info("createMenu_result={}-{}",status,"菜单创建失败！");
		}
	}
	
	/**
	 * 删除菜单
	 * @param request
	 * @param response
	 */
	@RequestMapping("/deleteMenu")
	public void deleteMenu(HttpServletRequest request,HttpServletResponse response){
		logger.info("deleteMenu_params=111111");
		String token=WXTokenUtil.getAccessToken(config.getWxAppId(), config.getWxAppSecret());
		ViewButton cb4 = new ViewButton();
		cb4.setName("智能起名");
		cb4.setType("view");
		cb4.setUrl("https://www.zhouyilive.com/zhouyiApp/#/Author");
		ComplexButton cx = new ComplexButton();
		cx.setName("起名");
		cx.setSub_button(new ViewButton[]{cb4});
		Menu menu=new Menu();
		menu.setButton(new ComplexButton[]{cx});
		int status = MenuUtil.deleteMenu(MenuUtil.getMenu(),token);
		if(status==0){
			logger.info("deleteMenu_result={}-{}",status,"菜单删除成功！");
		}else{
			logger.info("deleteMenu_result={}-{}",status,"菜单删除失败！");
		}
	}
}
