package com.zhouyi.business.model.provincecomprehensive.utils;

import com.zhouyi.business.model.provincecomprehensive.GDSInfo;
import com.zhouyi.business.model.provincecomprehensive.PersonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: first
 * @Date: 下午9:15 2019/11/1
 * @Description: 平台之间数据映射集合
**/
public class PlatformMappedMap {

   public static Map<String, String> fieldNameMapping = new HashMap<>();

   private static final Logger loggger=LoggerFactory.getLogger(PlatformMappedMap.class);


    private static final Logger logger = LoggerFactory.getLogger(PlatformMappedMap.class);

    static {
        logger.info("开始填充省综-标采平台映射关系----");
        //PersonInfo封装
        packingPersonInfo();
        logger.info("个人信息映射完毕");

    }

    /**
     * 将字段的名字提取成数组
     * @param fields
     * @return
     */
    private static String[] extractFieldName(Field[] fields){
        List<String> fieldNames=new ArrayList<>(10);
        for (Field field : fields) {
            field.setAccessible(true);
            fieldNames.add(field.getName());
        }
        return (String[])fieldNames.toArray();
    }

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
         */
        String[] standardPlatform = {
                "ryjcxxcjbh", "zwbh", "rydnabh", "xmhypy", "bmch", "csrq",
                "xbdm", "sg", "tz", "zc", "gjdm", "xldm", "zzmmdm", "jgssxdm", "zydm","gzdw",
                "lxdh",null,null,null,"mzdm","gmsfhm","cyzjdm",null,null,"hjdssxdm","hjdxz",
                "xzdxz","rylbdm","ajlbdm",null,null,null,null,"cjxxyy","cjdwdm","cjdwmc","cjddm","cjrxm",
                "cjsj","gmsfhm","annex","idCardPhoto",null,null,null,null
        };
        packingFiledMappingData(fieldNames,standardPlatform);


    }


    /**
     * 随身物品信息
     */
    private static void packingGoodsInfo(){
        Field[] declaredFields = GDSInfo.class.getDeclaredFields();
        List<String> fieldNames=new ArrayList<>();
        for (Field declaredField : declaredFields) {
           declaredField.setAccessible(true);

        }

    }
}
