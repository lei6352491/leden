package com.zhouyi.business.core.model.provincecomprehensive.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: first
 * @Date: 上午9:44 2019/11/1
 * @Description: zip包中图片信息
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataInfo {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 数据
     */
    private byte[] data;
    /**
     * 后缀
     */
    private String suffix;



}
