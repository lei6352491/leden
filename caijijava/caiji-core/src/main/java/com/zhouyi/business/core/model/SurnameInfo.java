package com.zhouyi.business.core.model;

import java.io.Serializable;

public class SurnameInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String surname;
    private String pinyin;
    private String yinjie;
    private String description;
    private String rank;
    private String provinceTop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getYinjie() {
        return yinjie;
    }

    public void setYinjie(String yinjie) {
        this.yinjie = yinjie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getProvinceTop() {
        return provinceTop;
    }

    public void setProvinceTop(String provinceTop) {
        this.provinceTop = provinceTop;
    }
}
