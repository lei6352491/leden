package com.zhouyi.business.core.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author 杜承旭
 * @ClassNmae: InitializationPageUtils
 * @Description: TODO
 * @date 2019/8/5 13:32
 * @Version 1.0
 **/

@Component
public class InitializationPageUtils<T> {

    private Class clazz;

    private Field[] fields;

    /*public InitializationPageUtils(){
        //获取父类的Class对象
        Class clazz = this.getClass();
        //通过Class对象获取参数化类型
        Type type = clazz.getGenericSuperclass();
        ParameterizedType pType = (ParameterizedType) type;
        //获取实际化参数
        Type[] types = pType.getActualTypeArguments();
        //实际化参数的实现类是Class，可以把参数强转问Class对象，得到了子类中泛型的Class对象
        this.clazz = (Class) types[0];
        this.fields = this.clazz.getDeclaredFields();
    }*/

    public T initializationPage(T t){
        clazz = t.getClass();
        fields = clazz.getDeclaredFields();
        //获取clazz的实例对象
        try {
            Field fieldPage = null;
            Field fieldSize = null;
            for (Field field : fields){
                if (field.getName().equalsIgnoreCase("page")){
                    field.setAccessible(true);
                    if (field.get(t) == null || (Integer)field.get(t) < 1){
                        field.set(t,1);
                    }
                    fieldPage=field;
                }
                if (field.getName().equalsIgnoreCase("size")){
                    field.setAccessible(true);
                    if (field.get(t) == null || (Integer)field.get(t) < 1){
                        field.set(t,10);
                    }
                    fieldSize = field;
                }
            }
            Integer page = (Integer)fieldPage.get(t);
            Integer size = (Integer)fieldSize.get(t);
            for (Field field : fields){
                if (field.getName().equalsIgnoreCase("startNo")){
                    field.setAccessible(true);
                    field.set(t,(page - 1) * size +1);
                }
                if (field.getName().equalsIgnoreCase("endNo")){
                    field.setAccessible(true);
                    field.set(t,(page - 1) * size +1 +size);
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
