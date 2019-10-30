package com.zhouyi.business.core.model.xinzhen;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: XZPerson
 * @Description: TODO 刑侦人员数据模型
 * @date 2019/7/19 11:32
 * @Version 1.0
 **/
@Data
@ToString
public class XZPerson {

    private String ryjcxxcjbh;

    private String xm;

    private String xmhypy;


    private String gmsfhm;

    private String cyzjdm;

    private String zjhm;

    private String xbdm;

    private Date csrq;

    private String cym;

    private String wwxm;

    private String bmch;

    private String gjdm;

    private String mzdm;

    private String hjdXzqhdm; //户籍地行政区规划对应---HJDSSXDM

    private String hjdDzmc; //户籍地详情对应---HJDXZ

    private String xzdXzqhdm; //现住地行政区规划对应----XZDQHDM

    private String xzdDzmc; //现住地详情对应---XZDXZ

    private String csdXzqhdm; //出生地行政区规划对应---CSDSSXDM

    private String csdDzmc; //出生地详情对应---CSDXZ

    private String zzmmdm;

    private String hyzkdm;

    private String zjxydm;

    private String xldm;

    private String grsfdm;

    private String tssfdm;

    private String bcjrylbdm; //被采集人人员类别代码对应----RYLBDM

    private String cjrXm; //采集人姓名对应----CJRXM

    private String cjrSfhm; //采集人身份号码---数据库不存在

    private String cjdwGajgjgdm; //采集单位机构代码对应----CJDWDM

    private String cjdwDwmc; //采集单位名称对应----CJDWMC

    private Date cjsj; //采集时间

    private String cjcslxdm; //采集场所代码---数据库不存在

    private String sbcsdm; //设别厂商代码---数据库不存在

    private String cjsbbh; //采集设备编号---对应EQUIPMENT_CODE

    private String cjsbrjbbh; //采集设备软件版本号----数据库不存在

    private String lxdh; //被采集人联系电话----数据库不存在


}
