package com.zhouyi.business.core.utils;

import com.zhouyi.business.core.model.LedenCollectBRecord;
import com.zhouyi.business.core.model.LedenCollectBankcard;
import com.zhouyi.business.core.model.xinzhen.XZPerson;
import com.zhouyi.business.core.vo.combine.LedenCollectBankCombine;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.hibernate.validator.internal.xml.ParameterType;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author 李秸康
 * @ClassNmae: DataExchangeUtil
 * @Description: TODO 数据交换生成工具
 * @date 2019/7/18 13:54
 * @Version 1.0
 **/
public class DataExchangeUtil {

    /**
      * @author 李秸康
      * @Description 根据数据对象生成xml文件
      * @date 2019/7/18
      * @params
      * @return
     **/
    public static void generatorXml(Object dataObject,String storePath,Class targetClass,String rrbh) throws IllegalAccessException {
        Field[] fields=targetClass.getDeclaredFields();
        //生成xml
        Document document= DocumentHelper.createDocument();
        Element dataElement=document.addElement("data");
        //定义type节点和rows节点
        Element typeElement=dataElement.addElement("type");
        Element rowElement=dataElement.addElement("rows").addElement("row");
        //如果为是银行卡记录则节点不一样
        if(targetClass== LedenCollectBankCombine.class){
            //添加两个节点
            for (Field field :
                    fields) {
                field.setAccessible(true);
                /*
                向type中添加银行卡标识
                 */
                Element tempTypeElement = typeElement.addElement(field.getName());
                Element tempRowElement=null; //记录节点
                //拿到其中得字段放入
                if(field.getType()==List.class){
                    //如果是集合表明是记录则只添加一条信息
                    //获取其中得泛型对象
                    Type genericType=field.getGenericType();
                    ParameterizedType parameterType=(ParameterizedType)genericType;
                    Class<?> realDataClass=(Class<?>)parameterType.getActualTypeArguments()[0];//获取集合中得真实类型
                    //将真实得对象类型中的字段放入
                    addItems(tempTypeElement,realDataClass.getDeclaredFields());

                    //添加多条记录信息
                    List<LedenCollectBRecord> records=(List<LedenCollectBRecord>)field.get(dataObject);
                    //进行遍历添加
                    for (LedenCollectBRecord record :
                            records) {
                        //每一条数据添加一个节点
                        tempRowElement=rowElement.addElement(field.getName());
                        //向节点中添加数据
                        addRow(tempRowElement,record.getClass().getDeclaredFields(),record);
                    }
                    continue;
                }
               tempRowElement=rowElement.addElement(field.getName());
                //向节点中添加数据
                addRow(tempRowElement,field.getType().getDeclaredFields(),field.get(dataObject));
                //如果是银行卡信息记录则直接添加
                addItems(tempTypeElement,field.getType().getDeclaredFields());




            }
        }else{
            //如果是其他数据
            //则直接添加type与row
            addItems(typeElement,targetClass.getDeclaredFields());
            addRow(rowElement,targetClass.getDeclaredFields(),dataObject);
        }

        //将数据写成文件
        OutputFormat format=OutputFormat.createPrettyPrint();
        XMLWriter writer=null;
        try {
             writer=new XMLWriter(new FileOutputStream(new File(storePath+"/"+File.separator+"JBXX-"+rrbh+".xml")),format);
            writer.write(document);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    /**
     * 向节点中插入Items数据项
     * @param targetElement
     * @param fields
     */
     private static void addItems(Element targetElement,Field[] fields) throws IllegalAccessException {
         for (Field field : fields) {
             field.setAccessible(true);
             targetElement.addElement("item").setText(field.getName().toUpperCase());
         }
     }
     
     
     /**
       * @author 李秸康
       * @Description 添加row信息
       * @date 2019/7/18
       * @params 
       * @return 
      **/
     private static void addRow(Element targetElement,Field[] fields,Object dataObject) throws IllegalAccessException {
         for (Field field :
                 fields) {
             field.setAccessible(true);
             Object value=field.get(dataObject);
             if(value!=null){
                 targetElement.addElement("row").setText(value.toString());
             }else{
                 targetElement.addElement("row").setText("");
             }

         }

     }


    public static void main(String[] args) {
        LedenCollectBankCombine xzPerson=new LedenCollectBankCombine();
        LedenCollectBankcard ledenCollectBankcard=new LedenCollectBankcard();
        xzPerson.setYhkxi(ledenCollectBankcard);
        xzPerson.getYhkxi().setBlxxJyqk("fdsfdz");

       LedenCollectBRecord record=new LedenCollectBRecord();
       record.setJyddXzqhdm("fdsfds");

       xzPerson.setJyjlxx(new ArrayList<>());
       xzPerson.getJyjlxx().add(record);

        try {
            generatorXml(xzPerson,"d:/output/",LedenCollectBankCombine.class,"rb10101010");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }



}

