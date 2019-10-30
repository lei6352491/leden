package com.zhouyi.business.core.model.enums;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: DataSetEnum
 * @Description: 采集项 枚举类
 * @date 2019/7/11 18:03
 * @Version 1.0
 **/
public enum DataSetEnum {
     WA_MFORENSICS_010100("手机终端","LedenCollectPTerminal","WA_MFORENSICS_010100"),
    WA_MFORENSICS_010500("通讯录","LedenCollectPAddressbook","WA_MFORENSICS_010500"),
    WA_MFORENSICS_010600("通话记录","LedenCollectPCallrecords","WA_MFORENSICS_010600"),
    WA_MFORENSICS_010700("短信记录","LedenCollectPMessagerecords","WA_MFORENSICS_010700")
    ;


    private String name;
    private String className;
    private String dataSetCode;


    public String getDataSetCode() {
        return dataSetCode;
    }

    public void setDataSetCode(String dataSetCode) {
        this.dataSetCode = dataSetCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    DataSetEnum(String name) {
        this.name = name;
    }

    DataSetEnum(String name, String className) {
        this.name = name;
        this.className = className;
    }

    DataSetEnum(String name, String className, String dataSetCode) {
        this.name = name;
        this.className = className;
        this.dataSetCode = dataSetCode;
    }

    public static void main(String[] args) {
        Date date=new Date();
        date.setTime(1552276050);
        System.out.println(date);
    }

}
