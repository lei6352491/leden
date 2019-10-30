package com.zhouyi.business.core.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 李秸康
 * @ClassNmae: DataSetCode
 * @Description: 数据集对象
 * @date 2019/7/12 8:39
 * @Version 1.0
 **/
public class DataSetCode {

    private String name;
    private String className;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
