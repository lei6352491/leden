package com.zhouyi.business.core.utils;

/**
 * @Author: first
 * @Date: 下午12:07 2019/11/28
 * @Description: 单位工具类
**/
public class UnitUtil {


    /**
     * 获取单位编码的头部
     * @param unitCode
     * @return
     */
    public static String getUnitHead(String unitCode,int index){
        StringBuffer stringBuffer=new StringBuffer();
        String suffix=unitCode.substring(index);
        StringBuffer zero=new StringBuffer();
        for(int i=index;i<12;i++){
            zero.append("0");
        }
        if(suffix.equals(zero.toString())){
            //如果末尾数匹配上了
            return stringBuffer.append(unitCode.substring(0,index)).append("%").toString();
        }else if("".equals(zero)||index==12){
            //如果直接是不带0的
            return unitCode;
        }else{
            //如果没有匹配上则继续调用
           return getUnitHead(unitCode,(index+2));
        }


    }


    public static void main(String[] args) {
        System.out.println(getUnitHead("372323123344",2));
    }
}
