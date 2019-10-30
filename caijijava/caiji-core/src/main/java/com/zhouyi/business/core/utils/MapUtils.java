package com.zhouyi.business.core.utils;




import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 李秸康
 * @ClassNmae: MapUtils
 * @Description: Map集合操作工具类
 * @date 2019/6/24 10:07
 * @Version 1.0
 **/
public class MapUtils {

    /**
     * 设置分页属性
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static Map<String,Object> setPageConditions(Integer pageNo,Integer pageSize){
        Map<String,Object> maps=new HashMap<>();
        Integer start=(pageNo-1)*pageSize+1;
        maps.put("start",start);
        maps.put("end",pageNo*pageSize);
        maps.put("pSize",pageSize);
        return maps;
    }


    /**
     * 设置分页属性
     * @param pageNo
     * @param pageSize
     * @param map
     */
    public static void setPageConditions(Integer pageNo,Integer pageSize,Map<String,Object> map){
        Integer start=(pageNo-1)*pageSize+1;
        map.put("start",start);
        map.put("end",pageNo*pageSize);
        map.put("pSize",pageSize);
    }

    /**
     * 将对象属性分装成集合
     * @param targetObject
     * @param <T>
     * @return
     */
    public static <T>Map<String,Object> objectTransferToMap(T targetObject){
        Map<String,Object> resultMap=new HashMap<>();
        Field[] fields=targetObject.getClass().getDeclaredFields();
        try {
            for (Field field :
                    fields) {
                field.setAccessible(true);
                resultMap.put(field.getName(),field.get(targetObject));
            }
            //判断是否继承PageData
            if(targetObject.getClass().getSuperclass()!=null){
                //定义分页数据
                Integer pageNo=1;
                Integer pageSize=5;

                //如果不为空表示继承子PageDto
                //获取父类字段值封装分页条件
                Class<?> superClass=targetObject.getClass().getSuperclass();
                Field[] superFields=superClass.getDeclaredFields();
                for (Field superField :
                        superFields) {
                    superField.setAccessible(true);
                    if (superField.getName().equals("pNo"))
                        pageNo=(Integer)superField.get(targetObject);
                    else if(superField.getName().equals("pSize"))
                        pageSize=(Integer)superField.get(targetObject);
                    else
                        resultMap.put(superField.getName(),superField.get(targetObject));
                }


                //进行条件封装
                setPageConditions(pageNo,pageSize,resultMap);
            }
            return resultMap;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将请求中的数据封装入map
     * @param request
     * @return
     */
    public Map<String,Object> pageData(HttpServletRequest request){
        Map properteis=request.getParameterMap();
        Iterator iterator=properteis.entrySet().iterator();
        Map<String,Object> resultMap=new HashMap<>();
        Map.Entry entry;
        String name;
        String value;
        while (iterator.hasNext()){
            entry=(Map.Entry)iterator.next();
            name=(String)entry.getKey();
           value=(String)entry.getValue();
           resultMap.put(name,value);
        }
        return resultMap;
    }



}
