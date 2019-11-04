package com.zhouyi.business.core.utils;

import com.google.common.base.CaseFormat;
import com.google.gson.internal.$Gson$Preconditions;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.vo.headvo.HeaderVo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeanUtils;
import org.w3c.dom.traversal.NodeIterator;
import sun.misc.BASE64Decoder;
import sun.net.www.protocol.ftp.FtpURLConnection;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 李秸康
 * @ClassNmae: XmlParseUtil
 * @Description: 解析xml工具类
 * @date 2019/7/1 8:49
 * @Version 1.0
 **/
public class XmlParseUtil {

    public static ThreadLocal<String> userCodeThreadLocal=new ThreadLocal();

    public static ThreadLocal<String> createDatetimeThreadLocal=new ThreadLocal<>();
    //数据结果对象

    /**
     * 解析xml文件返回数据对象
     * @param path
     * @return
     */
    @Deprecated
    public static Object parseXmlOld(String path,Class clazz){
        //创建读取对象
        SAXReader saxReader=new SAXReader();
        Object dataResult=null;
        try {
            Document document=saxReader.read(new File(path));
            Element dataObject=document.getRootElement(); //获取节点
            Iterator it=dataObject.elementIterator(); //获取迭代
            //创建数据结果对象
            dataResult=clazz.newInstance();
            while(it.hasNext()){
                Element childrenElement=(Element)it.next();  //获取root下的子节点
                if(childrenElement.getName().equals("head")){
//                    //将得到的head对象赋值给结果对象
                }else if (childrenElement.getName().equalsIgnoreCase("datalist")){

                    //创建datalist对象存储数据
                    Object dataList=clazz.getField("data").getType().newInstance();

                    if(clazz.getField("data").getType().isAssignableFrom(List.class)){
                        //如果为List对象
                        dealMulti(clazz,childrenElement,dataList);
                    }else{
                        //如果不为集合类型
                        return null;
                    }
                }
            }
            return dataResult;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 解析xml得到数据对象
     * @param path 文件路径
     * @param clazz 目标类型
     * @return 结果对象
     */
    public static Object parseXml(String path,Class<?> clazz) throws XmlParseException{
        SAXReader saxReader=new SAXReader();
        saxReader.setEncoding("UTF-8");
        Object dataResult=null;
        try {
            Document document=null;
            String path2=path.substring(0,3);
           if(path2.equalsIgnoreCase("ftp")){
              //表明是ftp文件
               document=saxReader.read(path);
           }else{
               //表明是字符串
               document=saxReader.read(new ByteArrayInputStream(path.getBytes("GBK")));
           }
            Element rootElement=document.getRootElement();
            Iterator rootIterator=rootElement.elementIterator();
            //创建数据结果对象
            dataResult=clazz.newInstance();

            while(rootIterator.hasNext()){
                Element childrenElement=(Element)rootIterator.next();
                if(childrenElement.getName().equals("head")){
                    //如果是header节点,则创建一个Head对象
                    Iterator headerIterator=childrenElement.elementIterator();
                    Object headObject = clazz.getField("head").getType().newInstance();
                    while(headerIterator.hasNext()){
                        //获取数据
                        Element headerChildrenElement=(Element)headerIterator.next(); //获取到值的节点
                        //获取头部信息字段
                        Field headField=clazz.getField("head");
                        //获取头部字段类型
                        Class headClass=headField.getType();
                        Field[] headFields=headClass.getFields();
                        for (Field field :
                                headFields) {
                            //循环遍历
                            if(field.getName().equalsIgnoreCase(headerChildrenElement.getName()))
                                field.set(headObject,headerChildrenElement.getStringValue());
                        }
                    }
                    //使用ThreadLocal存储值
                    userCodeThreadLocal.set(((HeaderVo)headObject).getUSER_CODE());
                    //将得到的head对象赋值给结果对象
                    clazz.getField("head").set(dataResult,headObject);
                }else{
                    //data字段
                    Field dataField=clazz.getField("data");
                    //结果对象
                    Object result=null;
                    //判断数据类型
                    if(List.class.isAssignableFrom(dataField.getType())){
                        //如果为集合
                        List<Element> nodeList=childrenElement.elements();
                        Class<?> cs=getGenericType(dataField);
                        result=dealMulti(cs,nodeList,null);
                    }else{
                        //如果不为集合
                        result=dealSingleton(dataField.getType(),childrenElement.element("data"));
                    }
                    /**
                     * 11-2
                     */
                    //将采集人写入对象
//                    Field field= null;
//                    try {
//                        field = result.getClass().getField("createUserId");
//                        boolean flag=predicateFiledExistAndIsNull(field,result);
//                        if(flag){
//                            field.set(result,userCodeThreadLocal.get());
//                        }
//                        //将创建时间写入
//                        field=result.getClass().getField("createDatetime");
//                        flag=predicateFiledExistAndIsNull(field,result);
//                        if(flag){
//                            field.set(result,new Date());
//                        }
//                    } catch (Exception e) {
//                    }



                    clazz.getField("data").set(dataResult,result);
                }

            }

        } catch (DocumentException e) {

            e.printStackTrace();
            throw new XmlParseException("文档解析失败:"+e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new XmlParseException("实例化属性对象失败:"+e.getMessage());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new XmlParseException("文档定义的属性未找到:"+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new XmlParseException("时间格式转换错误:"+e.getMessage());
        } catch (NullPointerException e){
            throw new XmlParseException("文件中没有数据");
        }
        return dataResult;
    }



    private static Pattern linePattern = Pattern.compile("_(\\w)");
    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /**
      * @author 李秸康
      * @Description 处理单个数据方法
      * @date 2019/7/3
      * @param clazz 返回的数据类型
     * @Param 数据节点对象
      * @return 结果对象
     **/
    private static Object dealSingleton(Class clazz,Element element) throws IllegalAccessException, InstantiationException, IOException, NoSuchFieldException, ParseException {
        //实例化结果对象
        Object resultDate=clazz.newInstance();
        Iterator dataElement=element.elementIterator();

        /*
        定义物品编号，用作解析记录
         */
        String wpbh=null;


        while (dataElement.hasNext()){
            Element valueNode=(Element)dataElement.next();
            //将节点名称转为属性名称
            String propertiesName=firstCharToLowerCase(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,valueNode.getName()));
            Field field=null;
            //修改：直接获取
            if(!propertiesName.equalsIgnoreCase("datalist")){
                //非list集合字段
                try {
                    field=clazz.getField(propertiesName);
                } catch (NoSuchFieldException e) {
                    //如果字段不匹配,则全部转为小写
                    propertiesName=propertiesName.toLowerCase();
                    field=clazz.getField(propertiesName);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }

                if(field.getType().isAssignableFrom(byte[].class)){
                    //如果为字节类型
//                    String base64Data=valueNode.getText();
//                    byte[] bytes=new BASE64Decoder().decodeBuffer(base64Data);
                    byte[] bytes=valueNode.getText().getBytes();
                    field.set(resultDate,bytes);
                }else if(field.getType()== BigDecimal.class){
                    //将值转为BigDecimal类型
                    field.set(resultDate,new BigDecimal(valueNode.getStringValue()));
                }else if(field.getType()== Date.class){
                    //如果为日期类型
                    try {
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        field.set(resultDate,simpleDateFormat.parse(valueNode.getStringValue()));
                    }catch (Exception e){
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        field.set(resultDate,simpleDateFormat.parse(valueNode.getStringValue()));
                    }

                }else{
                    //普通字段
                    field.set(resultDate,valueNode.getStringValue());
                    //将物品编号存储到属性
                    if(field.getName().equals("wpbh")){
                        wpbh=valueNode.getStringValue();
                    }

                }
            }else{
                //如果为datalist则表示是个数据集合、
                field=clazz.getField("dataSon");
                Class<?> genericClazz=null;
                //获取其Generic类型
                Type genericType=field.getGenericType();
                if(genericType instanceof ParameterizedType){
                    //如果为泛型
                    ParameterizedType parameterizedType=(ParameterizedType)genericType;
                    genericClazz=(Class<?>)parameterizedType.getActualTypeArguments()[0];
                }
                //如果为集合类型表示嵌套集合
                List<Element> listElement=valueNode.elements();

                Object result=dealMulti(genericClazz,listElement,wpbh);
                field.set(resultDate,result);
                break;

            }

        }



        return resultDate;
    }

    private static boolean predicateFiledExistAndIsNull(Field field,Object resultDate) throws IllegalAccessException {
        field.setAccessible(true);
        if(field!=null){
            if(field.getType()==String.class){
                return field.get(resultDate).equals("")||field.get(resultDate)==null;
            }else if(field.getType()==Date.class){
                return field.get(resultDate)==null;
            }
        }
        return false;

    }

    /**
     * 处理多个数据方法
     * @param clazz
     * @param elements
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object dealMulti(Class clazz,List<Element> elements,String wpbh) throws IllegalAccessException, InstantiationException, IOException, NoSuchFieldException, ParseException {
        List dataList=new LinkedList(); //构建结果数据对象
        for (Element element :
                elements) {
            //创建一个数据结果对象
           Object resultData=dealSingleton(clazz,element);

           //如果为随身物品的图片
            if(wpbh!=null){
                clazz.getField("wpbh").set(resultData,wpbh);
            }

            //将数据添加入集合
            dataList.add(resultData);
        }

        return dataList;
    }


    /**
     * 获取字段的泛型类型
     * @param field
     * @return
     */
    private static Class<?> getGenericType(Field field){
        Class<?> clazz=null;
        Type genericType=field.getGenericType();
        if(genericType instanceof ParameterizedType){
            //如果为泛型
            ParameterizedType parameterizedType=(ParameterizedType)genericType;
            clazz=(Class<?>)parameterizedType.getActualTypeArguments()[0];
        }
        return clazz;
    }

    /**
     * 复制属性到新对象中
     * @param origin
     * @param targets
     */
    public static void copyProperties(Object origin,List<Object> targets){
        for (Object object :
                targets) {
            BeanUtils.copyProperties(origin,object);
        }
    }


    /**
     * 首字母转小写
     * @param str
     * @return
     */
    public static String firstCharToLowerCase(String str){
        char[] chars=str.toCharArray();
        chars[0]+=32;
        return new String(chars);
    }

    /**
     * 从目标字符串中剔除目标字符
     * @param targetStr
     * @param targetChar
     */
    private static String eliminateChar(String targetStr,char targetChar){
        StringBuffer stringBuffer=new StringBuffer();
        for (int i=0;i<targetStr.length();i++){
            if(targetStr.charAt(i)!=targetChar)
                stringBuffer.append(targetStr.charAt(i));
        }
        return stringBuffer.toString();
    }

    /**
      * @author 李秸康
      * @Description 处理多个数据方法
      * @date 2019/7/3
      * @param clazz 结果数据类型
      * @Param element dataList节点
     * @Param dataList 数据结果对象
      * @return
     **/
    @Deprecated
    public static void dealMulti(Class clazz,Element element,Object dataList) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        Object dataResult=null;
        //泛型中的数据类型
        Class fieldType=null;
        //获取其中的泛型的类型
        Type type =clazz.getField("data").getGenericType();
        //获取list中的对象的字段
        if(ParameterizedType.class.isAssignableFrom(type.getClass())){
            for (Type t1 : ((ParameterizedType) type).getActualTypeArguments()) {
                fieldType= Class.forName(t1.getTypeName());
            }
        }
        Field[] fields=fieldType.getFields();
        //如果为datalist标签,则将里面的标签进行迭代
        Iterator dataListIterator=element.elementIterator();
        while(dataListIterator.hasNext()){
            //创建数据集合对象List（对应数据对象中的属性）
            Element dataElements=(Element)dataListIterator.next();
            //每一个dataElement即为一个数据对象,迭代每一个
            Iterator iterator=dataElements.elementIterator();
            //实例化一个实际Data类型对象
            Object data = fieldType.newInstance();
            while(iterator.hasNext()){
                //得到data中的数据并赋值
                Element dataField=(Element)iterator.next();
                for (Field field :
                        fields) {
                    if (field.getName().equalsIgnoreCase(dataField.getName())){
                        //如果属性和节点名相同
                        //根据类型匹配
                        if(field.getType()== byte[].class){
                            //如果为数组类型
                            String base64Data=dataField.getText();
                            byte[] bytes=new BASE64Decoder().decodeBuffer(base64Data);
                            field.set(data,bytes);
                        }else{
                            field.set(data,dataField.getStringValue());
                        }
                        break;
                    }
                }
            }
            //将data写入集合
            ((ArrayList)dataList).add(data);
        }
        //将集合添加入vo对象
        clazz.getField("data").set(dataResult,dataList);
    }




    public static void main(String[] args) {

//        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,"SAMPLE_DES"));

        System.out.println("ef74dda7-995a-4092-9b02-1b2d0c4ee5a1".length());

        System.out.println("CREATE_USER_ID".toLowerCase());
    }


    /**
     * 转xml对象vo-pojo
     * @param orign
     * @param target
     */
    public static List<Object> transferDataObject(Object orign,Object target){
        return null;

    }


    /**
     * 复制头部信息
     * @param origin
     */
    public static  void copyHeader(Object origin,Object... objectList){
        try {
            Object headObject=origin.getClass().getField("head").get(origin);
            Class fieldClass=headObject.getClass();
            for (Object object :
                    objectList) {
                try {
                    object.getClass().getField("equipmentCode").set(object,fieldClass.getField("EQUIPMENT_CODE").get(headObject));
                    object.getClass().getField("createUserId").set(object,fieldClass.getField("USER_CODE").get(headObject));
                    object.getClass().getField("cjdwdm").set(object,fieldClass.getField("USER_UNIT_CODE").get(headObject));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    //如果没有该字段则跳过
                    continue;
                } catch (SecurityException e) {
                    e.printStackTrace();
                }

            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }






//    private class HeadField{
//        private String EQUIPMENT_CODE;
//        private String CREATE_USER_ID;
//        private String USER_UNIT_CODE;
//
//        public String getEQUIPMENT_CODE() {
//            return EQUIPMENT_CODE;
//        }
//
//        public void setEQUIPMENT_CODE(String EQUIPMENT_CODE) {
//            this.EQUIPMENT_CODE = EQUIPMENT_CODE;
//        }
//
//        public String getCREATE_USER_ID() {
//            return CREATE_USER_ID;
//        }
//
//        public void setCREATE_USER_ID(String CREATE_USER_ID) {
//            this.CREATE_USER_ID = CREATE_USER_ID;
//        }
//
//        public String getUSER_UNIT_CODE() {
//            return USER_UNIT_CODE;
//        }
//
//        public void setUSER_UNIT_CODE(String USER_UNIT_CODE) {
//            this.USER_UNIT_CODE = USER_UNIT_CODE;
//        }
//    }


}
