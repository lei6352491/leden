package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: LedenEquipmentResult
 * @Description: TODO
 * @date 2019/8/5 10:53
 * @Version 1.0
 **/

@Data
@ToString
public class LedenEquipmentResult implements Serializable {

    private String pkId;

    private String equipmentCode;

    private String chamberType;

    private String equipmentName;

    private String factoryNum;

    private String equipmentVersion;

    private String manufacturer;

    private Date manufacturerDate;

    private String equipmentIp;

    private String equipmentMac;

    private String status;

    private String unitCode;

    private String deleteFlag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;


    private String name;

    private String sign;


    private String unitName;

    private String unitFullName;

    private String unitLeader;

    private String unitPhone;

    private BigDecimal unitLongitude;

    private BigDecimal unitLatitude;

    private String upperUnitCode;

    private String unitLevel;

    private String unitCategory;

    private String remark;

}
