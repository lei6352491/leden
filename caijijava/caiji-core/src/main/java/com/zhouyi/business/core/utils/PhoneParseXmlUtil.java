package com.zhouyi.business.core.utils;
import com.google.common.base.CaseFormat;
import com.zhouyi.business.core.model.LedenCollectPTotal;
import com.zhouyi.business.core.model.enums.CodesEnum;
import com.zhouyi.business.core.model.enums.DataSetEnum;
import com.zhouyi.business.core.vo.xml.PhonePackXml;
import io.swagger.annotations.Scope;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.io.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: PhoneParseXmlUtil
 * @Description: TODO
 * @date 2019/7/10 14:03
 * @Version 1.0
 **/



@Component
@Scope(name = "prototype",description = "prototype")
@ConfigurationProperties(prefix = "own")
public class PhoneParseXmlUtil {

    /**
     *模型包名
     */
    private static String modelClassPath;
    /**
     * bcp文件真实路径
     */
    private static String realPath;


    /**
     * 解析手机数据包
     * @param filePath
     * @return
     */
    public static PhonePackXml parsePhoneDataPack(String filePath){
        SAXReader saxReader=new SAXReader();
        //创建返回结果数据对象
        PhonePackXml phonePackXml=new PhonePackXml();


        try {
            Document document=saxReader.read(new File(filePath));
            //获取文件中的结果集节点
            Element dataSetElement=document.getRootElement().element("DATASET")
                    .element("DATA")
                    .element("DATASET");
            //获取Data节点集合
            List<Element> datas = dataSetElement.elements("DATA");
            //定义解析初始值
            Integer beginRow=1;
            // 定义数据文件路径
            String bcpFilepath=null;
            //定义记录数量
            Integer rowRecord=1;
            //数据项编码
            String dataSetCode=null;

            for (Element dataElement :
                    datas) {
                //dataElement为一个DATA节点
                List<Element> itemsElement=dataElement.elements("ITEM");

                /*
                获取其中的初始行属性
                遍历获取
                 */
                for (Element itemElement :
                        itemsElement) {
                    if (itemElement.attributeValue("key").equals("I010038")){
                        beginRow=Integer.parseInt(itemElement.attributeValue("val"));
                    }
                    else if(itemElement.attributeValue("key").equals("A010004")){
                        dataSetCode=itemElement.attributeValue("val");
                    }
                }

                //如果不在集合中的配置项则不解析
                try {
                    DataSetEnum.valueOf(dataSetCode);
                } catch (IllegalArgumentException e) {
                   //如果报错则为没有改元素
                    continue;
                }

                //每次解析一个data节点，则需记录一个P_Total对象
                LedenCollectPTotal ledenCollectPTotal=new LedenCollectPTotal();

                /*
                赋值采集项总数
                获取采集项名称
                赋值
                 */
                ledenCollectPTotal.setTotal(Short.valueOf(rowRecord.toString()));
                String collectName= DataSetEnum.valueOf(dataSetCode).getName();
                ledenCollectPTotal.setTarget(collectName);




                /*
                解析dataSet子节点
                获取其中的bcp文件路径和记录行数
                对另一个进行解析操作
                 */
                Element itemDataSetElement=null;
                List<Element> dataSetElements=dataElement.elements("DATASET");
                for (int i=0;i< dataSetElements.size();i++){
                    Element tempElement=dataSetElements.get(i);
                    if(tempElement.attributeValue("name").equals("WA_COMMON_010014")){
                        //如果为BCP文件数据结构
                        tempElement=tempElement.element("DATA");
                        List<Element> item = tempElement.elements("ITEM");
                        for (Element temp:
                             item) {
                            String key=temp.attributeValue("key");
                            String value=temp.attributeValue("val");
                            if(key.equals("H010020")){
                                //获取bcp文件路径
                                bcpFilepath=value;
                            }
                            else if(key.equals("I010034")){
                                //获取行记录书
                                rowRecord=Integer.parseInt(value);
                            }
                        }
                    }else{
                        //如果为BCP文件数据结构
                        //将节点对象提出
                        itemDataSetElement=tempElement;
                    }

                }


                //调用解析方法进行解析
                String realBcpFilePath=realPath+File.separator+bcpFilepath;
                Object dataObject=fetchSingleRecord(dataSetCode,realBcpFilePath,beginRow,rowRecord,itemDataSetElement.element("DATA"),ledenCollectPTotal);

                //将记录对象放入集合
                phonePackXml.getLedenCollectPTotals().add(ledenCollectPTotal);


                    /*
                    利用反射将数据存储总数据对象
                    1.将类名首字母小写转为属性名
                    2.获取字段
                    3.赋值
                     */
                String value=(DataSetEnum.valueOf(dataSetCode)).getClassName();
                String property = XmlParseUtil.firstCharToLowerCase(value);
                Field[] fields=PhonePackXml.class.getDeclaredFields();
                for (Field field :
                        fields) {
                    field.setAccessible(true);
                    if(field.getName().equals(property)){
                        field.set(phonePackXml,dataObject);
                        break;
                    }

                }
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return phonePackXml;
    }


    /**
     * 解析单个数据项
     * @param dataSetCode
     * @param filePath
     * @param beginRow
     * @param rowRecord
     * @param element
     * @Param ledenCollectPTotal 记录对象，赋值后带出
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     * @throws NoSuchFieldException
     */
    private static List<Object> fetchSingleRecord(String dataSetCode,
                                                  String filePath,
                                                  Integer beginRow,
                                                  Integer rowRecord,
                                                  Element element,
                                                  LedenCollectPTotal ledenCollectPTotal) throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            IOException,
            NoSuchFieldException, ParseException {
        //获取相应的全类名
        String completeClassName=modelClassPath+"."+(DataSetEnum.valueOf(dataSetCode)).getClassName();
        //构建目标数据项对象类型
        Class targetClass=Class.forName(completeClassName);
        List<Object> objects=new ArrayList<>();

        //开始解析文件
        File bcpFile=new File(filePath);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(bcpFile)));
        //创建数据结果对象
        String[] fileContent=new String[rowRecord];

        if(rowRecord==1){
            fileContent[0]=bufferedReader.readLine();
        }
        else{
            //将数据一行一行读入数据
            for (int i=beginRow,j=0;i<=rowRecord;i++,j++){
                fileContent[j]=bufferedReader.readLine();
            }
        }
        //读取完毕关闭流;
        bufferedReader.close();
        //开始解析节点
        List<Element> items = element.elements("ITEM");

        //循环遍历数据同时进行赋值
        for (int d=0;d<rowRecord;d++){
            //每一条数据创建一个数据对象
            Object targetObject= targetClass.newInstance();
            //获取数据文件中每一行数据
            String  everyData=fileContent[d];
            //如果时\t结束
            if(everyData.endsWith("\t")){
                everyData+=" ";
            }
            //将数据用\t分割成数据组
            String[] dataPoint=everyData.split("\\t");


            //每遍历一条数据，则遍历一次节点
            for (int i=0;i<items.size();i++){
                Element itemElement=items.get(i);
                //获取属性key对应的值
                String key = itemElement.attributeValue("key");
                //根据key到集合中取得相应的属性值
                Object mapValue= CodesEnum.valueOf(key).getPropertiy();

                if(mapValue instanceof String[]){
                    //如果是map则一个编码对应两个字段
                    String[] array=(String[])mapValue;
                    Field field=null;
                    for (String fieldName :
                            array) {
                        try {
                            field=targetClass.getDeclaredField(transferFieldToProperty(fieldName));
                            break;
                        } catch (NoSuchFieldException e) {
                            //如果类型不匹配则读取数组中下一个属性名
                            continue;
                        }
                    }
                    //获取字段对象后进行封装
                    field.setAccessible(true);
                    field.set(targetObject,dataPoint[i]);
                    //将数据对象添加入
                    objects.add(targetObject);
                }else if(mapValue.toString().equals("00000")){
                    //如果为00000则表示数据库没有列存储该项值
                    continue;
                }else if(mapValue.toString().equals("00001")){
                    /*
                    如果获取的是对象姓名，则应转为对象编码
                    将对应的对象编码存入记录对象
                    目前无法将名字转为对象编号
                     */


                }else{
                    //如果是目标编号，则需存入记录对象
                    if(key.equals("I050008")){
                        //赋值记录编号
                        ledenCollectPTotal.setCollecttargetId(dataPoint[i]);
                    }else if(key.equals("B010001")){
                        //如果为对象姓名则存储
                        ledenCollectPTotal.setDxxm(dataPoint[i]);
                    }



                    //进行数据封装
                    Field field= null;
                    try {
                        field = targetClass.getDeclaredField(transferFieldToProperty(mapValue.toString()));
                        field.setAccessible(true);
                    } catch (NoSuchFieldException e) {
                        //如果报错则表示没有该属性，跳过
                        continue;
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }

                    //得到字段类型
                    Class<?> type = field.getType();
                    //得到数据值
                    String value=dataPoint[i].trim();

                    if(type==Date.class&&!value.equals("")){
                        //如果字段为日期并且不为空，则判断格式
                        Date date=new Date();
                        date.setTime(Long.parseLong(value));
                        field.set(targetObject,date);
//                        if(value.length()==10)
//                            //如果10位则为毫秒数
//                            field.set(targetObject,new Date(value));
//                        else
//                            field.set(targetObject,new SimpleDateFormat("yyyyMMddhhmmss").parse(dataPoint[i].trim()));

                    }else if(type==String.class){
                        field.set(targetObject,dataPoint[i].trim());
                    }

                }


            }
            objects.add(targetObject);
        }
        //返回数据集合对象
        return objects;
    }







    public static void main(String[] args) {


        String a ="aa";
        Object b=a;
        System.out.println(b.toString());

    }


    public static void way2(String st,String M) {
        int count = (st.length()-st.replace(M, "").length())/M.length();
        System.out.println("出现："+count+"次");
    }



    /**
      * @author 李秸康
      * @Description 将数据库字段转为属性
      * @date 2019/7/11
      * @params
      * @return
     **/
    public static String transferFieldToProperty(String field){
        return XmlParseUtil.firstCharToLowerCase(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,field));
    }


    public static String getModelClassPath() {
        return modelClassPath;
    }

    public static void setModelClassPath(String modelClassPath) {
        PhoneParseXmlUtil.modelClassPath = modelClassPath;
    }


    public static String getRealPath() {
        return realPath;
    }

    public static void setRealPath(String realPath) {
        PhoneParseXmlUtil.realPath = realPath;
    }


}
