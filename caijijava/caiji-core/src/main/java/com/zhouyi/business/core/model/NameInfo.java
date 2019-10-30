package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class NameInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private SurnameInfo surnameInfo;    // 姓氏详情
    private CombineInfo combineInfo;    // 用字组合详情
    private String fullName;            // 名字全称
    private String surname;             // 姓氏
    private String combine;             // 用字组合

    private Long createBy;      //创建者
    private Date createTime;    //创建日期

    public NameInfo(String fullName) {
        this.fullName = fullName;
    }

    public NameInfo(String surname, String combine) {
        this.surname = surname;
        this.combine = combine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SurnameInfo getSurnameInfo() {
        return surnameInfo;
    }

    public void setSurnameInfo(SurnameInfo surnameInfo) {
        this.surnameInfo = surnameInfo;
    }

    public CombineInfo getCombineInfo() {
        return combineInfo;
    }

    public void setCombineInfo(CombineInfo combineInfo) {
        this.combineInfo = combineInfo;
    }

    public String getFullName() {
        if (fullName == null && surname != null && combine != null) {
            return surname + combine;
        }
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCombine() {
        return combine;
    }

    public void setCombine(String combine) {
        this.combine = combine;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
