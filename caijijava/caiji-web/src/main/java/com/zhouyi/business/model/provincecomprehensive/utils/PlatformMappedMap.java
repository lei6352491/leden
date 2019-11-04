package com.zhouyi.business.model.provincecomprehensive.utils;

import com.zhouyi.business.model.provincecomprehensive.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @Author: first
 * @Date: 下午9:15 2019/11/1
 * @Description: 平台之间数据映射集合
**/
public class PlatformMappedMap {

   public static Map<String, String> fieldNameMapping = new HashMap<>();



    private static final Logger logger = LoggerFactory.getLogger(PlatformMappedMap.class);

    static {
        logger.info("开始填充省综-标采平台映射关系----");
        //PersonInfo封装
        packingPersonInfo();
        logger.info("个人信息映射完毕");
        packingGoodsInfo();
        logger.info("随身物品映射完毕");
        packingSignInfo();
        logger.info("体貌特征映射完毕");
        packingIrisInfo();
        logger.info("虹膜信息映射完毕");
        packingVoiceInfo();
        logger.info("声纹信息映射完毕");
    }

    /**
     * 将字段的名字提取成数组
     * @param fields
     * @return
     */
    private static String[] extractFieldName(Field[] fields){
        List<String> fieldNames=new ArrayList<>(10);
        for (Field field : fields) {
            if(Modifier.isFinal(field.getModifiers())){
                //如果为final则为外部类引用
                continue;
            }
            field.setAccessible(true);
            fieldNames.add(field.getName());
        }

        String[] extractResult=new String[fieldNames.size()];
        fieldNames.toArray(extractResult);
        return  extractResult;
    }

    /**
     * 封装字段映射方法
     * @param provinceFields
     * @param ownFields
     */
    private static void packingFiledMappingData(String[] provinceFields, String[] ownFields) {
        if (provinceFields.length == ownFields.length) {
            for (int i = 0; i < provinceFields.length; i++) {
                fieldNameMapping.put(provinceFields[i], ownFields[i]);
            }
        } else {
            logger.error("省字段和本平台字段数量不匹配");
        }

    }


    /**
     * 个人信息
     */
    private static void packingPersonInfo() {
        Field[] fields = PersonInfo.class.getDeclaredFields();
        String[] fieldNames = extractFieldName(fields);

        /**
         * 1.zwbh指纹编号：编号去掉R
         * 2.cjxxyy 我们为采集原因代码，从字段表中读取出采集原因
         * 3.cjddm:采集点代码，为省综平台注册返回的编号
         * 4.idCardPhoto:身份证照片
         * 5.hjdxzqh: 户籍地的详情地址
         * 6.xzdxzqh: 现住地行政区划
         */
        String[] standardPlatform = {
                "ryjcxxcjbh", "zwbh", "rydnabh", "xmhypy", "bmch", "csrq",
                "xbdm", "sg", "tz", "zc", "gjdm", "xldm", "zzmmdm", "jgssxdm", "zydm","gzdw",
                "lxdh",null,null,null,"mzdm","gmsfhm","cyzjdm",null,"hjdssxdm","hjdxzqh","hjdxz",
                "xzdqhdm","xzdxzqh","xzdxz","rylbdm","ajlbdm",null,null,null,null,"cjxxyy","cjdwdm","cjdwmc","cjddm","cjrxm",
                "cjsj","gmsfhm","annex","idCardPhoto",null,null,null,null
        };
        packingFiledMappingData(fieldNames,standardPlatform);
    }




    /**
     * 随身物品信息
     */
    private static void packingGoodsInfo(){
        //物品信息字段
        Field[] declaredFields = GDSInfo.class.getDeclaredFields();
        String[] fieldNames=extractFieldName(declaredFields);


        String[] standardPlatform={"xdwpWpmc",null,null,"wpysdm","wpysdmbcms",null,null,null,"annex",
        "imageInfos"};
        packingFiledMappingData(fieldNames,standardPlatform);

        //图片字段
        Field[] imagesFields=GDSInfo.ImageInfo.class.getDeclaredFields();
        String[] imagesFieldNames=extractFieldName(imagesFields);
        String[] imagesStandardPlatform={"image","remark"};
        packingFiledMappingData(imagesFieldNames,imagesStandardPlatform);

    }


    /**
     * 体貌特征
     * 1: photo：文件名
     */
    private static void packingSignInfo(){
        Field[] declaredFields = BodySignInfo.class.getDeclaredFields();
        String[] fieldNames=extractFieldName(declaredFields);
        String[] standardPlatform={"tstzBw","tstzFw","tstzSl","signCode","annex","photo"};
        packingFiledMappingData(fieldNames,standardPlatform);
    }


    /**
     * 虹膜信息
     */
    public static void packingIrisInfo(){
        Field[] declaredFields = IrisInfo.class.getDeclaredFields();
        String[] fieldNames=extractFieldName(declaredFields);
        String[] standardPlatform={"hmywdm","xxzldf","hmqsqkdm","hmcjhs",null,null,"sbbh","sbcsdm"};
        packingFiledMappingData(fieldNames,standardPlatform);
    }


    /**
     * 声纹信息
     * 1.speakTypeName:发音方式中文
     * 2.languageTypeName:语种中文
     * 3.dialectName:方言名称
     */
    public static void packingVoiceInfo(){
        Field[] declaredFields = VoiceInfo.class.getDeclaredFields();
        String[] fieldNames=extractFieldName(declaredFields);
        String[] standardPlatform={"fyfsdm","speakTypeName","lyyzdm","languageTypeName",
        "hyfydm","dialectName","lysb",null,null,"xzb","nlz",null};
        packingFiledMappingData(fieldNames,standardPlatform);
    }


    public static void main(String[] args) {
        Set<String> strings = fieldNameMapping.keySet();
        strings.forEach(x->{
            System.out.println(x);
        });
    }





}
