package com.zhouyi.business.core.model.provincecomprehensive;

import lombok.Data;

/**
 * @Author: first
 * @Date: 上午11:42 2019/10/31
 * @Description: 虹膜信息
 * 虹膜除了IrisIndex,Score需要首字母转大写外其他的都不用
**/
@Data
public class IrisInfo {

    /**
     * 眼位代码
     */
    private String irisIndex;

    /**
     * 分数
     */
    private String score;

    /**
     *未采原因：0：正常，1：残缺，2系统设置不采集，3受伤未采集，9其它缺缺情况
     */
    private String reason;


    /**
     * 采集花的时间
     */
    private String cjtphs;

    /**
     * 采集状态
     */
    private String cjbs;

    /**
     * 设备型号
     */
    private String sbxh;

    /**
     * 设备编号
     */
    private String sbbh;

    /**
     * 设备厂商
     */
    private String sbcs;
}
