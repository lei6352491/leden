package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class CharacterInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String hanzi;       //中文汉字
    private String pinyin;      //拼音
    private String yinjie;      //音节
    private String wuxing;      //五行
    private int applyGender;    //适用的性别
    private int popularScore;   //流行度
    private int appearPosition; //在名字中最常出现的位置

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHanzi() {
        return hanzi;
    }

    public void setHanzi(String hanzi) {
        this.hanzi = hanzi;
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

    public String getWuxing() {
        return wuxing;
    }

    public void setWuxing(String wuxing) {
        this.wuxing = wuxing;
    }

    public int getApplyGender() {
        return applyGender;
    }

    public void setApplyGender(int applyGender) {
        this.applyGender = applyGender;
    }

    public int getPopularScore() {
        return popularScore;
    }

    public void setPopularScore(int popularScore) {
        this.popularScore = popularScore;
    }

    public int getAppearPosition() {
        return appearPosition;
    }

    public void setAppearPosition(int appearPosition) {
        this.appearPosition = appearPosition;
    }
}
