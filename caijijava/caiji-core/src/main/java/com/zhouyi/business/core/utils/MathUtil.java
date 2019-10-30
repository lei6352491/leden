package com.zhouyi.business.core.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author 李秸康
 * @ClassNmae: MathUtil
 * @Description: 数学工具
 * @date 2019/7/5 13:42
 * @Version 1.0
 **/
public class MathUtil {


    private static Random random=new Random();
    /**
     * 生成制定长度的随机码
     * @param length
     * @return
     */
    public static String generateRandomCode(int length){
        StringBuffer randomCode=new StringBuffer();
        for (int i=1;i<length;i++){
            randomCode.append(random.nextInt(10));
        }
        return randomCode.toString();
    }

    /**
     * 生成UUID
     * @return
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
