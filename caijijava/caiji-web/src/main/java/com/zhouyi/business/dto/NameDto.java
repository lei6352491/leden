package com.zhouyi.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NameDto extends PageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> externalNames;
    private String surname;
    private String yinjie;

    private Long pendingCombineId;
    private String combine;
    private Integer style;
    private Integer applyGender;
    private Integer meaningScore;
    private Integer soundScore;

    private Integer status;
    private Long userId;

    private Integer soundReject;
    private Integer meaningReject;
    private Integer writeReject;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<String> getExternalNames() {
        return externalNames;
    }

    public void setExternalNames(List<String> externalNames) {
        this.externalNames = externalNames;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getYinjie() {
        return yinjie;
    }

    public void setYinjie(String yinjie) {
        this.yinjie = yinjie;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSoundReject() {
        return soundReject;
    }

    public void setSoundReject(Integer soundReject) {
        this.soundReject = soundReject;
    }

    public Integer getMeaningReject() {
        return meaningReject;
    }

    public void setMeaningReject(Integer meaningReject) {
        this.meaningReject = meaningReject;
    }

    public Integer getWriteReject() {
        return writeReject;
    }

    public void setWriteReject(Integer writeReject) {
        this.writeReject = writeReject;
    }
}
