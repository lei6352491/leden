package com.zhouyi.business.core.vo;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectEquipmentVo
 * @Description: 设备vo对象
 * @date 2019/7/5 13:51
 * @Version 1.0
 **/
public class LedenCollectEquipmentVo {

    private String chamberType;

    private String equipmentName;

    private String factoryNum;

    private String equipmentVersion;

    private String manufacturer;

    private Date manufacturerDate;

    private String equipmentIp;

    private String equipmentMac;

    private String unitCode;


    public String getChamberType() {
        return chamberType;
    }

    public void setChamberType(String chamberType) {
        this.chamberType = chamberType;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getEquipmentVersion() {
        return equipmentVersion;
    }

    public void setEquipmentVersion(String equipmentVersion) {
        this.equipmentVersion = equipmentVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getManufacturerDate() {
        return manufacturerDate;
    }

    public void setManufacturerDate(Date manufacturerDate) {
        this.manufacturerDate = manufacturerDate;
    }

    public String getEquipmentIp() {
        return equipmentIp;
    }

    public void setEquipmentIp(String equipmentIp) {
        this.equipmentIp = equipmentIp;
    }

    public String getEquipmentMac() {
        return equipmentMac;
    }

    public void setEquipmentMac(String equipmentMac) {
        this.equipmentMac = equipmentMac;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
