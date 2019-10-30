package com.zhouyi.business.utils;

import com.zhouyi.business.core.model.Head;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.net.www.protocol.ftp.FtpURLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;


public class XMLParamUtils {

    private Field[] getFields(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        return fields;
    }

    private Method[] getMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        return methods;
    }

    public Map parseXmlToMap(String path){
        Map<String,Object> map = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc;
            File file = new File(path);
            System.out.println(file.getName());
            System.out.println(file.getPath());
            doc = builder.parse(file);
            //得到一个element根元素,获得根节点
            Element root = doc.getDocumentElement();
            //获取head数据节点
            NodeList headNodes = root.getElementsByTagName("head");
            Head head = new Head();
            for (int i = 0; i<headNodes.getLength();i++) {
                Element headElement = (Element) headNodes.item(i);
                NodeList childNodes = headElement.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        if ("EQUIPMENT_CODE".equalsIgnoreCase(childNodes.item(j).getNodeName())) {
                            if (childNodes.item(j).getFirstChild() == null) {
                                continue;
                            }
                            head.setEquipmentCode(childNodes.item(j).getFirstChild().getNodeValue());
                        }
                        if ("USER_CODE".equalsIgnoreCase(childNodes.item(j).getNodeName())) {
                            if (childNodes.item(j).getFirstChild() == null) {
                                continue;
                            }
                            head.setUserCode(childNodes.item(j).getFirstChild().getNodeValue());
                        }
                        if ("USER_UNIT_CODE".equalsIgnoreCase(childNodes.item(j).getNodeName())) {
                            if (childNodes.item(j).getFirstChild() == null) {
                                continue;
                            }
                            head.setUserUnitCode(childNodes.item(j).getFirstChild().getNodeValue());
                        }
                    }
                }
            }
            map.put("head",head);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public Map parseXmlToMap(String xmlContent, Class clazz , Class childClazz){
        Map<String,Object> map = new HashMap<>();
        List list = new ArrayList<>();
        List listChild = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        InputStream inputStream = null;
        try{
            builder = factory.newDocumentBuilder();
            /*URL url = new URL(path);
            FtpURLConnection ftpURLConnection = (FtpURLConnection) url.openConnection();
            InputStream inputStream = ftpURLConnection.getInputStream();*/
            String substring = xmlContent.substring(0, 3);
            Document doc = null;
            if ("ftp".equalsIgnoreCase(substring)){
                doc = builder.parse(xmlContent);
            }else {
                inputStream = new ByteArrayInputStream(xmlContent.getBytes());
                doc = builder.parse(inputStream);
            }
            //得到一个element根元素,获得根节点
            Element root = doc.getDocumentElement();

            //获取head数据节点
            NodeList headNodes = root.getElementsByTagName("head");
            //解析xml文件中的head标签并封装到bean对象中
            Head head = new Head();
            for (int i = 0; i<headNodes.getLength();i++){
                Element headElement = (Element) headNodes.item(i);
                NodeList childNodes = headElement.getChildNodes();
                for (int j = 0;j < childNodes.getLength(); j++){
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        if ("EQUIPMENT_CODE".equalsIgnoreCase(childNodes.item(j).getNodeName())) {
                            if (childNodes.item(j).getFirstChild() == null){
                                continue;
                            }
                            head.setEquipmentCode(childNodes.item(j).getFirstChild().getNodeValue());
                        }
                        if ("USER_CODE".equalsIgnoreCase(childNodes.item(j).getNodeName())) {
                            if (childNodes.item(j).getFirstChild() == null){
                                continue;
                            }
                            head.setUserCode(childNodes.item(j).getFirstChild().getNodeValue());
                        }
                        if ("USER_UNIT_CODE".equalsIgnoreCase(childNodes.item(j).getNodeName())) {
                            if (childNodes.item(j).getFirstChild() == null){
                                continue;
                            }
                            head.setUserUnitCode(childNodes.item(j).getFirstChild().getNodeValue());
                        }
                    }
                }
            }

            //获取DATA数据节点
            NodeList dataNodes = root.getElementsByTagName("data");
            //获取bean字节码对象中的成员变量名称
            Field[] fields = getFields(clazz);
            //获取bean字节码对象中的成员函数
            Method[] methods = getMethods(clazz);

            Field[] childFields = null;
            Method[] childMethods = null;

            if (childClazz != null){
                //获取childData的成员变量名称
                childFields = getFields(childClazz);
                //获取childData字节码对象中的成员函数
                childMethods = getMethods(childClazz);
            }

            Object obj;
            Object objChild = null;
            //解析xml文件中的data标签并封装到bean对象中
            for(int i = 0; i<dataNodes.getLength();i++) {
                Element dataElement = (Element) dataNodes.item(i);
                NodeList childNodes = dataElement.getChildNodes();
                obj = clazz.newInstance();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        //判断XMLdata下是否包含子datachild节点
                        String nodeName = childNodes.item(j).getNodeName();
                        if ("datachild".equals(nodeName)){
                            NodeList datachildNodes = root.getElementsByTagName("datachild");
                            for (int e = 0;e < datachildNodes.getLength();e++){
                                Element dataElementChild = (Element)datachildNodes.item(e);
                                NodeList childNodesChild = dataElementChild.getChildNodes();
                                //生成一个pojo实例
                                if (childClazz != null){
                                    objChild = childClazz.newInstance();
                                    for (int t = 0; t < childNodesChild.getLength(); t++){
                                        //若有把子节点的数据封装到对应的pojo中
                                        if (childNodesChild.item(t).getNodeType() == Node.ELEMENT_NODE){
                                            if (childFields != null || childMethods != null){
                                                for (Field field : childFields){

                                                    if (field.getName().equalsIgnoreCase(childNodesChild.item(t).getNodeName())){
                                                        if (childNodes.item(t).getFirstChild() == null){
                                                            continue;
                                                        }
                                                        for (Method method : childMethods){
                                                            if (("Set" + field.getName()).equalsIgnoreCase(method.getName())){
                                                                if (field.getGenericType().getTypeName().equals(String.class.getTypeName())){
                                                                    method.invoke(objChild,childNodesChild.item(t).getFirstChild().getNodeValue());
                                                                }
                                                                if (field.getGenericType().getTypeName().equals(byte[].class.getTypeName())){
                                                                    method.invoke(objChild,childNodesChild.item(t).getFirstChild().getNodeValue().getBytes());
                                                                }
                                                                if (field.getGenericType().getTypeName().equals(Long.class.getTypeName())){
                                                                    method.invoke(objChild,Long.parseLong(childNodesChild.item(t).getFirstChild().getNodeValue()));
                                                                }
                                                                if (field.getGenericType().getTypeName().equals(Date.class.getTypeName())){
                                                                    method.invoke(objChild,new Date(Long.parseLong(childNodesChild.item(t).getFirstChild().getNodeValue())));
                                                                }
                                                                if (field.getGenericType().getTypeName().equals(BigDecimal.class.getTypeName())){
                                                                    method.invoke(objChild,new BigDecimal(childNodesChild.item(t).getFirstChild().getNodeValue()));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            //把子pojo添加到集合中
                            //System.out.println(objChild);
                            listChild.add(objChild);
                        }

                        for(Field field : fields){
                            if (field.getName().equalsIgnoreCase(childNodes.item(j).getNodeName())){
                                if (childNodes.item(j).getFirstChild() == null){
                                    continue;
                                }
                                for (Method method : methods){
                                    if (("Set" + field.getName()).equalsIgnoreCase(method.getName())){
                                        if (field.getGenericType().getTypeName().equals(String.class.getTypeName())){
                                            method.invoke(obj,childNodes.item(j).getFirstChild().getNodeValue());
                                        }
                                        if (field.getGenericType().getTypeName().equals(byte[].class.getTypeName())){
                                            method.invoke(obj,childNodes.item(j).getFirstChild().getNodeValue().getBytes());
                                        }
                                        if (field.getGenericType().getTypeName().equals(Long.class.getTypeName())){
                                            method.invoke(obj,Long.parseLong(childNodes.item(j).getFirstChild().getNodeValue()));
                                        }
                                        if (field.getGenericType().getTypeName().equals(Date.class.getTypeName())){
                                            method.invoke(obj,new Date(Long.parseLong(childNodes.item(j).getFirstChild().getNodeValue())));
                                        }
                                        if (field.getGenericType().getTypeName().equals(BigDecimal.class.getTypeName())){
                                            method.invoke(obj,new BigDecimal(childNodes.item(j).getFirstChild().getNodeValue()));
                                        }
                                        if (listChild.size() > 0){
                                            if (field.getGenericType().getTypeName().equals(List.class.getTypeName())){
                                                method.invoke(obj,objChild);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                list.add(obj);
            }
            map.put("head",head);
            map.put("data",list);
            map.put("dataChild",listChild);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
