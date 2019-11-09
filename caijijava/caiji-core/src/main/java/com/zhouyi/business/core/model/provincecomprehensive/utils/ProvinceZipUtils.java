package com.zhouyi.business.core.model.provincecomprehensive.utils;

import com.zhouyi.business.core.model.provincecomprehensive.*;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: first
 * @Date: 下午12:09 2019/10/31
 * @Description: 省综生成xml
**/
@Slf4j
public class ProvinceZipUtils {

    /**
     * 根据数据对象构建ZIP文件
     * @param request
     * @param personInfo
     * @param goodInfo
     * @param bodySignInfo
     * @param irisInfo
     * @param voiceInfo
     */



    /**
     * 生成ZIP压缩包
     * @param classpath
     * @param mis
     */
    public static String generatorZip(String classpath,MIS mis,List<DataInfo> dataInfos) throws Exception{
        //生成的名称为： 人员编号.zip
        log.info("文件的存储路径为:"+classpath);
        StringBuffer fileBuffer=new StringBuffer(classpath);
        fileBuffer.append(mis.getPersonInfo().getPersonId());
        fileBuffer.append(".zip");

        ZipOutputStream zipOutputStream=null;
        File zipFile=new File(fileBuffer.toString());
        try {
            zipOutputStream=new ZipOutputStream(new FileOutputStream(zipFile));
            //生成MISxml文件并存入zip
            generatorXml(classpath,zipOutputStream,mis);
            //生成其他文件并存入zip
            generatorDataFile(classpath,zipOutputStream,dataInfos);
            try {
                zipOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("关闭ZIP流异常");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("文件未找到");
            throw new Exception(e.getMessage());
        }

        return fileBuffer.toString();

    }


    /**
     * 生成其他数据文件并放入zip
     * @param contextPath
     * @param outputStream
     * @param dataInfos
     */
    private static void generatorDataFile(String contextPath,ZipOutputStream outputStream,List<DataInfo> dataInfos){
        dataInfos.forEach(x->{
            //使用StringBuffer构建整个文件名
            StringBuffer fileNameBuffer=new StringBuffer(contextPath);
            fileNameBuffer.append(x.getFileName());

            try {
                //创建文件并写入zip
                File dataFile=new File(fileNameBuffer.toString());
                FileOutputStream fileOutputStream = new FileOutputStream(dataFile);
                fileOutputStream.write(x.getData());
                outputStream.close();
                pushFileIntoZip(outputStream, dataFile);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("数据文件压入zip失败");
            }

        });
    }

    /**
     * 生成xml并放入zip
     * @param outputStream zip的流
     * @param mis
     */
    private static void generatorXml(String contextPath,ZipOutputStream outputStream, MIS mis){
        StringBuffer xml=new StringBuffer(contextPath);
        xml.append(mis.getPersonInfo().getPersonId());
        xml.append(".xml");

        //构建xml对象
        File infoXml=new File(xml.toString());
        OutputFormat format=OutputFormat.createCompactFormat();
        format.setEncoding("UTF-8");
        try {
            XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(infoXml),format);
            //关闭xml的特殊字符转义
            xmlWriter.setEscapeText(false);
            Document document = createDocument(mis);
            xmlWriter.write(document);
            pushFileIntoZip(outputStream, infoXml);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("省综生成xml编码错误,编码不支持");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("省综xml文件不存在");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error("封装xml错误");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("生成xml错误");
        }
    }

    /**
     * @Author: first
     * @Date: 上午11:53 2019/11/1
     * @Description: 将文件存入zip
    **/
    private static void pushFileIntoZip(ZipOutputStream outputStream, File infoXml) throws IOException {
        FileInputStream infoXmlFileInputStream = new FileInputStream(infoXml);
        ZipEntry zipEntry=new ZipEntry(infoXml.getName());
        outputStream.putNextEntry(zipEntry);
        int temp=0;
        while((temp=infoXmlFileInputStream.read())!=-1){
            outputStream.write(temp);
            outputStream.flush();
        }
    }


    /**
     * 生成xml文档对象
     * @param mis
     * @return
     */
    private static Document createDocument(MIS mis) throws IllegalAccessException {
        //文档对象
        Document document= DocumentHelper.createDocument();

        Element misElement=document.addElement("MIS");

        Field[] fields = mis.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.getType()!=List.class){
                Element singleElement = misElement.addElement(firstLetterToUpperCase(field.getName()));
                packageNodeData(singleElement,field.get(mis));
            }else{
                //如果为list
                packageNodeDatas(misElement,(List)field.get(mis),field.getName());
            }
        }
        return document;

    }


    /**
     * 封装多个数据方法
     * @param element
     * @param data
     */
    private static void packageNodeDatas(Element element, List<?> data,String nodeName){
        Element multiElement = element.addElement(firstLetterToUpperCase(nodeName));
        //后去泛型的类型
        Type actualTypeArgument = ((ParameterizedType) data.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        data.forEach(x->{
            Element singleElement = multiElement.addElement(firstLetterToUpperCase(actualTypeArgument.getTypeName()));
            try {
                packageNodeData(singleElement,x);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.error("封装singleElement数据节点时发生错误");
            }
        });
    }


    /**
     * 封装单个节点和数据
     * @param object
     */
    private static void packageNodeData(Element element,Object object) throws IllegalAccessException {
        Field[] fields=object.getClass().getDeclaredFields();
        if(object instanceof IrisInfo){
            //如果是虹膜类型
            for (Field field : fields) {
                field.setAccessible(true);
                String nodeName=null;

                Object value=field.get(object);

                if("irisIndex".equals(field.getName())||"score".equals(field.getName())){
                    nodeName=firstLetterToUpperCase(field.getName());
                    element.addElement(nodeName).setText(value!=null?value.toString():"");
                }
            }
        }else{
            //非虹膜类型,统一首字母转大写
            for (Field field : fields) {
                field.setAccessible(true);
                String nodeName=firstLetterToUpperCase(field.getName());


                Object value=field.get(object);
                if(field.getType()== Date.class&&value!=null){
                    //如果为日期类型
                    String format = new SimpleDateFormat("yyyyMMdd").format(field.get(object));
                    element.addElement(nodeName).setText(format);
                }
                element.addElement(nodeName).setText(value==null?"":value.toString());
            }
        }


    }


    /**
     * 首字母转大写
     * @param str
     * @return
     */
    private static String firstLetterToUpperCase(String str){
        char[] chars = str.toCharArray();
        chars[0]-=32;
        return new String(chars);
    }




}
