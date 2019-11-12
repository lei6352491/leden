package com.zhouyi.business.component.v2;

import com.zhouyi.business.core.model.provincecomprehensive.utils.DataInfo;
import com.zhouyi.business.core.model.provincecomprehensive.utils.MIS;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: first
 * @Date: 下午8:30 2019/11/10
 * @Description: 封装数据实现类
**/
@Component
@Slf4j
public class PackgeDataImpl  implements PackgeDataInterface{

    public void generateImageFile(String dir, List<DataInfo> dataInfos) {

    }

    @Override
    public List<DataInfo> extractData(String dir,List data,Class clazz) {
        String className=clazz.getSimpleName();
        if(data==null||data.size()==0){
            log.info(String.format("%s:没有数据",className));
        }

        switch (className){
//            case "GDSI"
        }
        return null;
    }

    @Override
    public void generateXml(MIS mis, String path) {

    }

    @Override
    public void compressDataIntoZip(MIS mis, String path) {

    }

    private Class getTargetGeneric(List<?> target){
        Type clazz=target.getClass().getGenericSuperclass();
        ParameterizedType pt=(ParameterizedType)clazz;
        return (Class) pt.getActualTypeArguments()[0];

    }
}
