package com.zhouyi.business.component.v2;

import com.zhouyi.business.core.model.provincecomprehensive.utils.DataInfo;
import com.zhouyi.business.core.model.provincecomprehensive.utils.MIS;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Author: first
 * @Date: 下午8:00 2019/11/10
 * @Description: 修改后的封装数据接口
**/
public interface PackgeDataInterface {

//
//    /**
//     * 在指定目录生成文件
//     * @param dir
//     * @param dataInfos
//     */
//    void generateImageFile(String dir,List<DataInfo> dataInfos);


    /**
     * 提取data集合中的数据
     * @param data
     * @Param dir
     * @return
     */
    List<DataInfo> extractData(String dir,List data,Class clazz);


    /**
     * 生成XML数据
     * @param mis
     * @param path
     */
    void generateXml(MIS mis,String path);


    /**
     * 压缩数据成ZIP文件
     * @param mis
     * @param path
     */
    void compressDataIntoZip(MIS mis,String path);



}
