package com.zhouyi.business.core.model;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Head implements Serializable {

    private String userUnitCode;

    private String userCode;

    private String equipmentCode;

    public String getUserUnitCode() {
        return userUnitCode;
    }

    public void setUserUnitCode(String userUnitCode) {
        this.userUnitCode = userUnitCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    @Override
    public String toString() {
        return "Head{" +
                "userUnitCode='" + userUnitCode + '\'' +
                ", userCode='" + userCode + '\'' +
                ", equipmentCode='" + equipmentCode + '\'' +
                '}';
    }
}
