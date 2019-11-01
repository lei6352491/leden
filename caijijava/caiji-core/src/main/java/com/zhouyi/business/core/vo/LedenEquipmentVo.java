package com.zhouyi.business.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentVo
 * @Description: TODO
 * @date 2019/6/25 10:31
 * @Version 1.0
 **/
@ApiModel(value = "接入注册模型")
public class LedenEquipmentVo implements Serializable {

    @ApiModelProperty(value = "主键id(新增可不传)")
    private String pkId;

    @ApiModelProperty(value = "设备编码")
    private String equipmentCode;

    @ApiModelProperty(value = "采集场所类型")
    private String chamberType;

    @ApiModelProperty(value = "设备名称")
    private String equipmentName;

    @ApiModelProperty(value = "出厂编码")
    private String factoryNum;

    @ApiModelProperty(value = "设备型号")
    private String equipmentVersion;

    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;


    @ApiModelProperty(value = "生产日期")
    private Date manufacturerDate;

    @ApiModelProperty(value = "IP地址")
    private String equipmentIp;

    @ApiModelProperty(value = "设备MAC地址")
    private String equipmentMac;

    @ApiModelProperty(value = "设备状态")
    private String status;

    @ApiModelProperty(value = "部门编码")
    private String unitCode;

    @ApiModelProperty(value = "删除标志")
    private String deleteFlag;

    @ApiModelProperty(value = "备注")
    private String annex;


    @ApiModelProperty(value = "记录创建人")
    private String createUserId;

    @ApiModelProperty(value = "创建时间（新增不传）")
    private Date createDatetime;

    @ApiModelProperty(value = "更新人id")
    private String updateUserId;

    @ApiModelProperty(value = "更新时间(不传)")
    private Date updateDatetime;


    private String provincialEquipmentCode;

    public String getProvincialEquipmentCode() {
        return provincialEquipmentCode;
    }

    public void setProvincialEquipmentCode(String provincialEquipmentCode) {
        this.provincialEquipmentCode = provincialEquipmentCode;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
