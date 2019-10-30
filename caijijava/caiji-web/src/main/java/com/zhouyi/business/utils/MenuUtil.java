package com.zhouyi.business.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.core.utils.HttpCall;
import com.zhouyi.business.model.Button;
import com.zhouyi.business.model.ClickButton;
import com.zhouyi.business.model.ComplexButton;
import com.zhouyi.business.model.Menu;
import com.zhouyi.business.model.ViewButton;

/**
 * 微信工具类
 * 
 *
 */
public class MenuUtil {
	private static final Logger logger = LoggerFactory.getLogger(MenuUtil.class);
	/**
	* 封装菜单数据
	* */
	public static Menu getMenu(){

		ViewButton cb1 = new ViewButton();
		cb1.setName("起名");
		cb1.setType("view");
		cb1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7abd963e43f0d6e5&redirect_uri=https%3a%2f%2fwww.zhouyilive.com%2fzhouyiApp%2f%23%2fHome&response_type=code&scope=snsapi_userinfo&state=d5172e54#wechat_redirect");
		
		ViewButton cb2 = new ViewButton();
		cb2.setName("姓名测评");
		cb2.setType("view");
		cb2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7abd963e43f0d6e5&redirect_uri=https%3a%2f%2fwww.zhouyilive.com%2fzhouyiApp%2f%23%2fNameScore&response_type=code&scope=snsapi_userinfo&state=d5172e54#wechat_redirect");
		
//		ClickButton cb3 = new ClickButton();
//		cb3.setName("手机绑定");
//		cb3.setType("click");
//		cb3.setKey("bangding");
		
		ClickButton cb4 = new ClickButton();
		cb4.setName("专属客服");
		cb4.setType("click");
		cb4.setKey("customer001");
		
		ViewButton vb1 = new ViewButton();
		vb1.setName("起名测名");
		vb1.setType("view");
		//需要使用网页授权获取微信用户的信息
		vb1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7abd963e43f0d6e5&redirect_uri=https%3a%2f%2fwww.zhouyilive.com%2fzhouyiApp%2f%23%2fHome&response_type=code&scope=snsapi_userinfo&state=d5172e54#wechat_redirect");
		
		ViewButton cb5 = new ViewButton();
		cb5.setName("在线客服");
		cb5.setType("view");
		//需要使用网页授权获取微信用户的信息
		cb5.setUrl("https://www.sobot.com/chat/h5/index.html?sysNum=830150dacf4842feb6d457bd7c8505c2");
		
		
		
		//创建第二个一级菜单
		ComplexButton cx = new ComplexButton();
		cx.setName("起名");
		cx.setSub_button(new Button[]{cb1,cb2,cb4});
	
		Button[] subs=new Button[]{vb1,cb5};
		//封装菜单数据
		Menu menu=new Menu();
		menu.setButton(subs);
	
		return menu;
	}


	/**
	* 创建自定义菜单(每天限制1000次)
	* */
	public static int createMenu(Menu menu,String token){
		String jsonMenu=JSON.toJSONString(menu);
	
		int status=0;
		logger.info("菜单："+jsonMenu);
		String path="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token;
		try {
			URL url=new URL(path);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(jsonMenu.getBytes("UTF-8"));
			os.close();
		
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] bt = new byte[size];
			is.read(bt);
			String message=new String(bt,"UTF-8");
			JSONObject jsonMsg = JSON.parseObject(message);
			status = Integer.parseInt(jsonMsg.getString("errcode"));
		} catch (Exception e) {
			logger.error("createMenu_err=",e);
		}
		return status;
	}

	public static int deleteMenu(Menu menu,String token){
		String jsonMenu=JSON.toJSONString(menu);
	
		int status=0;
		logger.info("菜单："+jsonMenu);
		String path="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+token;
		try {
			URL url=new URL(path);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(jsonMenu.getBytes("UTF-8"));
			os.close();
		
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] bt = new byte[size];
			is.read(bt);
			String message=new String(bt,"UTF-8");
			JSONObject jsonMsg = JSON.parseObject(message);
			status = Integer.parseInt(jsonMsg.getString("errcode"));
		} catch (Exception e) {
			logger.error("createMenu_err=",e);
		}
		return status;
	}
	public static void main(String[] args){
//		ViewButton cb4 = new ViewButton();
//		cb4.setName("智能起名");
//		cb4.setType("view");
//		//需要使用网页授权获取微信用户的信息
//		cb4.setUrl("https://www.zhouyilive.com/zhouyiApp/#/Author");
//	
//		//创建第二个一级菜单
//		ComplexButton cx = new ComplexButton();
//		cx.setName("起名");
//		cx.setSub_button(new ViewButton[]{cb4});
//		Menu menu=getMenu();
//		System.out.println(JSON.toJSONString(menu));
		
//		String token=WXTokenUtil.getAccessToken("wx7abd963e43f0d6e5", "1df7f091921a3564b196003cd93a9570");
//		int status = MenuUtil.createMenu(menu,token);
//		if(status==0){
//			System.out.println("createMenu_result="+status+"菜单创建成功！");
//		}else{
//			System.out.println("createMenu_result="+status+"菜单创建失败！");
//		}
//		TextMessage tm=new TextMessage();
//		tm.setToUserName("dddd");
//		tm.setArticleCount(1);
//		ImageMessage im=new ImageMessage();
//		im.setMediaId("dddddd");
//		tm.setImage(im);
//		System.out.println(WXMessageUtil.imageMessageToXml(tm));
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map<String,Object> data=new HashMap<String,Object>();
		Map<String,String> first=new HashMap<String,String>();
		first.put("value", "客户下单了");
		Map<String,String> keyword1=new HashMap<String,String>();
		keyword1.put("value", "人工起名服务");
		Map<String,String> keyword2=new HashMap<String,String>();
		keyword2.put("value", sdf.format(now));
		Map<String,String> keyword3=new HashMap<String,String>();
		keyword3.put("value", "1240973459238450012471092");
		Map<String,String> remark=new HashMap<String,String>();
		remark.put("value", "请点击详情查看订单明细。");
		data.put("first", first);
		data.put("keyword1", keyword1);
		data.put("keyword2", keyword2);
		data.put("keyword3", keyword3);
		data.put("remark", remark);
		HttpCall httpCall = new HttpCall();
		String token=WXTokenUtil.getAccessToken("wx7abd963e43f0d6e5", "1df7f091921a3564b196003cd93a9570");
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ token;
        JSONObject json = new JSONObject();
        json.put("touser", "oZMcW0pGwGAGlYwGXOEkcFd3XH0c");
        json.put("template_id", "AaNjht-oTH81mltJn2X5d5jhLa_ehPAYFXqIu3PnNP0");
        json.put("url", "");
//        json.put("topcolor", topcolor);
        json.put("data", data);
        try{
            String result = (String)httpCall.httpPostCall(tmpurl, json.toString());
//            JSONObject resultJson = new JSONObject(result);
            System.out.println("发送微信消息返回信息：" + result);
//            String errmsg = (String) resultJson.get("errmsg");
            
         }catch(Exception e){
            e.printStackTrace();
        }
	}
	
}
