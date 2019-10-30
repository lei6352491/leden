package com.zhouyi.business.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.zhouyi.business.core.common.WeChatUserInfo;
import com.zhouyi.business.model.PictInfo;
import com.zhouyi.business.model.TextMessage;

/**
 * 微信工具类
 * 
 *
 */
public class WXMessageUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WXMessageUtil.class);
	
	public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";

    public static final String EVENT_SUB = "subscribe";
    public static final String EVENT_UNSUB = "unsubscribe";
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_VIEW = "VIEW";
	
	
	
    public static String textMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", TextMessage.class);
        return xstream.toXML(textMessage);

    }
	
    public static String newMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", TextMessage.class);
        xstream.alias("item", new PictInfo().getClass());
        return xstream.toXML(textMessage);

    }
    
    public static String imageMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", TextMessage.class);
//        xstream.alias("item", new PictInfo().getClass());
        return xstream.toXML(textMessage);

    }
	/**
	 * 微信公众号自动回复关键字
	 * @param request
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request ) throws DocumentException, IOException
    {
        Map<String,String> map = new HashMap<String, String>();

        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }
	
}
