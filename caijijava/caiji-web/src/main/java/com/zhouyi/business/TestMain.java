package com.zhouyi.business;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        DocumentBuilderFactory a = DocumentBuilderFactory.newInstance();
        try {
            //创建DocumentBuilder对象
            DocumentBuilder b = a.newDocumentBuilder();
            //通过DocumentBuilder对象的parse方法返回一个Document对象
            Document document = b.parse
                    ("C:\\Users\\lenovo\\Documents\\WeChat Files\\duchen00811\\FileStorage\\File\\2019-07\\GAB_ZIP_INDEX.xml");
            //通过Document对象的getElementsByTagName()返根节点的一个list集合
            NodeList itemlist = document.getElementsByTagName("ITEM");
            //过滤的容器
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i<itemlist.getLength(); i++){
                //循环遍历获取每一个item
                Node item = itemlist.item(i);
                //通过Node对象的getAttributes()方法获取全的属性值
                NamedNodeMap itemMap = item.getAttributes();
                //循环遍每一个item的属性值
                for(int j = 0; j<itemMap.getLength(); j++){
                    if ("eng".equals(itemMap.item(j).getNodeName())){
                        list.add(i);
                    }
                }
            }
            for (Integer index : list){
                Node item = itemlist.item(index);
                //通过Node对象的getAttributes()方法获取全的属性值
                NamedNodeMap itemMap = item.getAttributes();
                for(int j = 0; j<itemMap.getLength(); j++){
                    //通过Node对象的getNodeName()和getNodeValue()方法获取属性名和属性值
                    System.out.print(itemMap.item(j).getNodeName()+":");
                    System.out.println(itemMap.item(j).getNodeValue());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
