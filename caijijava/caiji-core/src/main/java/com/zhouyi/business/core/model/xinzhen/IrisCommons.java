package com.zhouyi.business.core.model.xinzhen;

import lombok.Data;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: first
 * @Date: 上午11:55 2019/12/1
 * @Description: 虹膜请求数据公共数据
 **/
@Data
public class IrisCommons extends IdSecretVersion {
    static {
       setYwfssj();
    }

    /**
     * 请求任务号
     * 规则：HMBD-12 位请求方公安机关机构代码-18 位设备厂商代码-8 位请求日期-8 位日流水号
     */
    private String request_id;

    /**
     * 业务发生时间
     * 业务发生时间， 14 位日期时间字符串 ， 精确到秒，格式要求为YYYYMMDDHH24MISS
     */
    private String ywfssj;

    /**
     * 用户id
     */
    private String user_id;


    /**
     * 用户部门代码
     */
    private String user_dept;


    /**
     * 用户部门名称
     */
    private String user_deptname;

    /**
     * 设备厂商代码
     */
    private String sbcsdm;


    /**
     * 虹膜采集设备型号代码
     */
    private String hmcjsbxhdm;


    /**
     * 设备编号
     */
    private String sbbh;


    /**
     * 采集场地代码
     */
    private String cjcd;

    /**
     * 业务发生地点
     */
    private String dzmc;

    /**
     * 经度
     */
    private String jd;


    /**
     * 纬度
     */
    private String wd;


    /**
     * 采集编号
     */
    private String cjbh;


    /**
     * 采集人姓名
     */
    private String cjr_xm;


    /**
     * 用户公民身份号码
     */
    private String cjr_gmsfhm;

    /**
     * 强制采集标志
     */
    private String qzcjbz="0";


    /**
     * 左右眼采集代码
     */
    private String zyycjdm="3";


    /**
     * 左眼缺失情况代码
     */
    private String zyqsqkdm="0";


    /**
     * 右眼确实情况代码
     */
    private String yyqsqkdm="0";


    /**
     * 虹膜左眼照片
     */
    @ToString.Exclude
    private byte[] hmzp_zy;


    /**
     * 虹膜右眼代码
     */
    @ToString.Exclude
    private byte[] hmzp_yy;


    /**
     * 采集耗时
     */
    private String cjtphs;


    /**
     * 左眼虹膜照片信息质量评分。3 位数值型字符串,根据左右眼采集
     * 代码选择填写
     */
    private String zy_xxzlpf;

    /**
     * 右眼虹膜照片信息质量评分
     */
    private String yy_xxzlpf;


    /**
     * 设置业务发生时间
     */
    private static void setYwfssj() {
        this.ywfssj = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
