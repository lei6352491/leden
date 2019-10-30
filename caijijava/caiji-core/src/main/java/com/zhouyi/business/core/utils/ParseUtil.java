package com.zhouyi.business.core.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author 李秸康
 * @ClassNmae: ParseUtil
 * @Description: 类转换工具类
 * @date 2019/6/25 10:56
 * @Version 1.0
 **/
public class ParseUtil {

    /**
     * 属性复制
     * @param origin
     * @param target
     * @return
     */
    public static void parseObject(Object origin,Object target){
        BeanUtils.copyProperties(origin,target);
    }
}
