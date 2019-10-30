package com.zhouyi.business.utils;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.Head;
import com.zhouyi.business.core.utils.ResponseUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.net.www.protocol.ftp.FtpURLConnection;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 * @author 杜承旭
 * @ClassNmae: JsoupParseXmlUtils
 * @Description: TODO
 * @date 2019/7/18 10:54
 * @Version 1.0
 **/

public class JsoupParseXmlUtils {

    private static Field[] getFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return fields;
    }

    /*private static Method[] getMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        return methods;
    }*/

    public static Map jsoupPerseXmlIrisInfo(String xmlContext, Class clazz) {
        try {
            String substring = xmlContext.substring(0, 3);
            Document document = null;
            if ("ftp".equalsIgnoreCase(substring)) {
                //获取xml文档对象
                URL url = new URL(xmlContext);
                FtpURLConnection ftpURLConnection = new FtpURLConnection(url);
                InputStream inputStream = ftpURLConnection.getInputStream();
                document = Jsoup.parse(inputStream,"utf-8",xmlContext);
                //document = Jsoup.parse(xmlContext, "utf-8");
            } else {
                document = Jsoup.parse(xmlContext);
            }
            //获取head标签的元素对象
            Element RootElement = document.getElementsByTag("root").first();
            //创建一个用来返回的Map集合
            Map<String, Object> map = new HashMap<>();
            //创建一个用来存储父节点对象的集合
            List dataList = new ArrayList();

            if (RootElement != null) {
                //获取head标签的子元素集
                Elements headChildElements = RootElement.children();
                //把子元素集的内容封装到Head对象中
                Head head = new Head();
                for (Element headChildElement : headChildElements) {
                    if ("EQUIPMENT_CODE".equalsIgnoreCase(headChildElement.tagName())) {
                        head.setEquipmentCode(headChildElement.text());
                    }
                    if ("USER_CODE".equalsIgnoreCase(headChildElement.tagName())) {
                        head.setUserCode(headChildElement.text());
                    }
                    if ("USER_UNIT_CODE".equalsIgnoreCase(headChildElement.tagName())) {
                        head.setUserUnitCode(headChildElement.text());
                    }
                }
                //返回解析数据
                map.put("head", head);
            }

            String ryjcxxcjbh = document.getElementsByTag("RYJCXXCJBH").get(0).text();
            map.put("ryjcxxcjbh", ryjcxxcjbh);

            //获取datalist标签的元素对象
            Element dataListElement = document.getElementsByTag("datalist").first();
            //获取data标签集合
            Elements dataElments = dataListElement.children();
            for (Element element : dataElments) {
                if (clazz != null) {
                    Elements leafElements = element.children();
                    Field[] parentFields = getFields(clazz);
                    Object objectParent = clazz.newInstance();

                    for (Element leafElement : leafElements) {
                        for (Field field : parentFields) {
                            if (leafElement.tagName().replace("_", "").equalsIgnoreCase(field.getName())) {
                                field.setAccessible(true);
                                if (field.getGenericType().getTypeName().equals(String.class.getTypeName())) {
                                    field.set(objectParent, leafElement.text());
                                }
                                if (field.getGenericType().getTypeName().equals(byte[].class.getTypeName())) {
                                    field.set(objectParent, leafElement.text().getBytes());
                                }
                                if (field.getGenericType().getTypeName().equals(Long.class.getTypeName())) {
                                    field.set(objectParent, Long.parseLong(leafElement.text()));
                                }
                                if (field.getGenericType().getTypeName().equals(Date.class.getTypeName())) {
                                    field.set(objectParent, new Date(Long.parseLong(leafElement.text())));
                                }
                                if (field.getGenericType().getTypeName().equals(BigDecimal.class.getTypeName())) {
                                    field.set(objectParent, new BigDecimal(leafElement.text()));
                                }
                            }
                        }
                    }
                    dataList.add(objectParent);
                }
            }
            map.put("data", dataList);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析xml文件
     */
    public static Map jsoupParseXml(String filePath, Class parentClass, Class childClass) {
        try {
            String substring = filePath.substring(0, 3);
            Document document = null;
            if ("ftp".equalsIgnoreCase(substring)) {
                //获取xml文档对象
                document = Jsoup.parse(filePath, "utf-8");
            } else {
                document = Jsoup.parse(filePath);
            }
            //获取head标签的元素对象
            Element headElement = document.getElementsByTag("root").first();
            //创建一个用来返回的Map集合
            Map<String, Object> map = new HashMap<>();
            //创建一个用来存储父节点对象的集合
            List parenList = new ArrayList();
            //创建一个用来存储子节点对象的集合
            List childList = new ArrayList();
            if (headElement != null) {
                //获取head标签的子元素集
                Elements headChildElements = headElement.children();
                //把子元素集的内容封装到Head对象中
                Head head = new Head();
                for (Element headChildElement : headChildElements) {
                    if ("EQUIPMENT_CODE".equalsIgnoreCase(headChildElement.tagName())) {
                        head.setEquipmentCode(headChildElement.text());
                    }
                    if ("USER_CODE".equalsIgnoreCase(headChildElement.tagName())) {
                        head.setUserCode(headChildElement.text());
                    }
                    if ("USER_UNIT_CODE".equalsIgnoreCase(headChildElement.tagName())) {
                        head.setUserUnitCode(headChildElement.text());
                    }
                }
                //获取datalist标签的元素对象
                Element dataListElement = document.getElementsByTag("datalist").first();
                //获取data标签集合
                Elements dataElments = dataListElement.children();
                for (Element element : dataElments) {
                    if (parentClass != null) {
                        Field[] parentFields = getFields(parentClass);
                        //Method[] parentMethods = getMethods(parentClass);
                        Object objectParent = parentClass.newInstance();
                        //获取data标签集合下的子节点集合
                        Elements dataElements = element.children();
                        for (Element dataChildElement : dataElements) {
                            for (Field field : parentFields) {
                                if (dataChildElement.tagName().replace("_", "").equalsIgnoreCase(field.getName())) {
                                    field.setAccessible(true);
                                    if (field.getGenericType().getTypeName().equals(String.class.getTypeName())) {
                                        field.set(objectParent, dataChildElement.text());
                                    }
                                    if (field.getGenericType().getTypeName().equals(byte[].class.getTypeName())) {
                                        field.set(objectParent, dataChildElement.text().getBytes());
                                    }
                                    if (field.getGenericType().getTypeName().equals(Long.class.getTypeName())) {
                                        field.set(objectParent, Long.parseLong(dataChildElement.text()));
                                    }
                                    if (field.getGenericType().getTypeName().equals(Date.class.getTypeName())) {
                                        field.set(objectParent, new Date(Long.parseLong(dataChildElement.text())));
                                    }
                                    if (field.getGenericType().getTypeName().equals(BigDecimal.class.getTypeName())) {
                                        field.set(objectParent, new BigDecimal(dataChildElement.text()));
                                    }
                                }
                            }
                            if ("datalist".equalsIgnoreCase(dataChildElement.tagName())) {
                                //获取data标签集合
                                Elements dataChildElements = dataChildElement.children();
                                for (Element dataChildElementChild : dataChildElements) {
                                    //获取data标签集合下的子节点集合
                                    Elements elements = dataChildElementChild.children();
                                    Field[] childFields = getFields(childClass);
                                    Object objectChild = childClass.newInstance();
                                    //变量子标签集合
                                    for (Element elementChild : elements) {
                                        for (Field field : childFields) {
                                            if (elementChild.tagName().replace("_", "").equalsIgnoreCase(field.getName())) {
                                                field.setAccessible(true);
                                                if (field.getGenericType().getTypeName().equals(String.class.getTypeName())) {
                                                    field.set(objectChild, elementChild.text());
                                                }
                                                if (field.getGenericType().getTypeName().equals(byte[].class.getTypeName())) {
                                                    field.set(objectChild, elementChild.text().getBytes());
                                                }
                                                if (field.getGenericType().getTypeName().equals(Long.class.getTypeName())) {
                                                    field.set(objectChild, Long.parseLong(elementChild.text()));
                                                }
                                                if (field.getGenericType().getTypeName().equals(Date.class.getTypeName())) {
                                                    field.set(objectChild, new Date(Long.parseLong(elementChild.text())));
                                                }
                                                if (field.getGenericType().getTypeName().equals(BigDecimal.class.getTypeName())) {
                                                    field.set(objectChild, new BigDecimal(elementChild.text()));
                                                }
                                            }
                                        }
                                    }
                                    //把子节点对象添加到集合中
                                    childList.add(objectChild);
                                }
                            }
                        }
                        //把子节点集合添加到父节点对象中
                        for (Field field : parentFields) {
                            if (field.getGenericType().getTypeName().equals(List.class.getTypeName())) {
                                field.setAccessible(true);
                                field.set(objectParent, childList);
                            }
                        }
                        //把父节点添加到集合中
                        parenList.add(objectParent);
                    }
                }
                //返回解析数据
                map.put("head", head);
                map.put("data", parenList);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_1027));
        }
        return null;
    }

    /*public static void main(String[] args) {
        Map map = new JsoupParseXmlUtils().jsoupParseXml("D:/xml/pom.xml", LedenCollectBankcard.class, LedenCollectBRecord.class);
        Head head = (Head)map.get("head");
        List list = (List)map.get("data");
        System.out.println(map);
    }*/
}
