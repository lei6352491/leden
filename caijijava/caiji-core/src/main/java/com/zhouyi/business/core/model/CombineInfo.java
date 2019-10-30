package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class CombineInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long pendingCombineId;
    private String combine;
    private Integer style;
    private Integer applyGender;
    private Integer meaningScore;
    private Integer soundScore;
    private String explain;
    private Integer status;

    private Long createBy;      //创建者
    private Date createTime;    //创建日期

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPendingCombineId() {
        return pendingCombineId;
    }

    public void setPendingCombineId(Long pendingCombineId) {
        this.pendingCombineId = pendingCombineId;
    }

    public String getCombine() {
        return combine;
    }

    public void setCombine(String combine) {
        this.combine = combine;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getApplyGender() {
        return applyGender;
    }

    public void setApplyGender(Integer applyGender) {
        this.applyGender = applyGender;
    }

    public Integer getMeaningScore() {
        return meaningScore;
    }

    public void setMeaningScore(Integer meaningScore) {
        this.meaningScore = meaningScore;
    }

    public Integer getSoundScore() {
        return soundScore;
    }

    public void setSoundScore(Integer soundScore) {
        this.soundScore = soundScore;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
