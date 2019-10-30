package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenMatch implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String bzbh;

    private Short querytype;

    private Date matchTime;

    private String matchFgp;

    private Integer matchScore;

    private String registUserId;

    private String registUserName;

    private String registUnitCode;

    private String registUnitName;

    private String reviewUserId;

    private String reviewUserName;

    private String reviewUnitCode;

    private String reviewUnitName;

    private String confirmUserId;

    private String confirmUserName;

    private String confirmUnitCode;

    private String confirmUnitName;

    private String deletag;

    private String annex;

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getBzbh() {
        return bzbh;
    }

    public void setBzbh(String bzbh) {
        this.bzbh = bzbh;
    }

    public Short getQuerytype() {
        return querytype;
    }

    public void setQuerytype(Short querytype) {
        this.querytype = querytype;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchFgp() {
        return matchFgp;
    }

    public void setMatchFgp(String matchFgp) {
        this.matchFgp = matchFgp;
    }

    public Integer getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(Integer matchScore) {
        this.matchScore = matchScore;
    }

    public String getRegistUserId() {
        return registUserId;
    }

    public void setRegistUserId(String registUserId) {
        this.registUserId = registUserId;
    }

    public String getRegistUserName() {
        return registUserName;
    }

    public void setRegistUserName(String registUserName) {
        this.registUserName = registUserName;
    }

    public String getRegistUnitCode() {
        return registUnitCode;
    }

    public void setRegistUnitCode(String registUnitCode) {
        this.registUnitCode = registUnitCode;
    }

    public String getRegistUnitName() {
        return registUnitName;
    }

    public void setRegistUnitName(String registUnitName) {
        this.registUnitName = registUnitName;
    }

    public String getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(String reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public String getReviewUnitCode() {
        return reviewUnitCode;
    }

    public void setReviewUnitCode(String reviewUnitCode) {
        this.reviewUnitCode = reviewUnitCode;
    }

    public String getReviewUnitName() {
        return reviewUnitName;
    }

    public void setReviewUnitName(String reviewUnitName) {
        this.reviewUnitName = reviewUnitName;
    }

    public String getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(String confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public String getConfirmUserName() {
        return confirmUserName;
    }

    public void setConfirmUserName(String confirmUserName) {
        this.confirmUserName = confirmUserName;
    }

    public String getConfirmUnitCode() {
        return confirmUnitCode;
    }

    public void setConfirmUnitCode(String confirmUnitCode) {
        this.confirmUnitCode = confirmUnitCode;
    }

    public String getConfirmUnitName() {
        return confirmUnitName;
    }

    public void setConfirmUnitName(String confirmUnitName) {
        this.confirmUnitName = confirmUnitName;
    }

    public String getDeletag() {
        return deletag;
    }

    public void setDeletag(String deletag) {
        this.deletag = deletag;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", bzbh=").append(bzbh);
        sb.append(", querytype=").append(querytype);
        sb.append(", matchTime=").append(matchTime);
        sb.append(", matchFgp=").append(matchFgp);
        sb.append(", matchScore=").append(matchScore);
        sb.append(", registUserId=").append(registUserId);
        sb.append(", registUserName=").append(registUserName);
        sb.append(", registUnitCode=").append(registUnitCode);
        sb.append(", registUnitName=").append(registUnitName);
        sb.append(", reviewUserId=").append(reviewUserId);
        sb.append(", reviewUserName=").append(reviewUserName);
        sb.append(", reviewUnitCode=").append(reviewUnitCode);
        sb.append(", reviewUnitName=").append(reviewUnitName);
        sb.append(", confirmUserId=").append(confirmUserId);
        sb.append(", confirmUserName=").append(confirmUserName);
        sb.append(", confirmUnitCode=").append(confirmUnitCode);
        sb.append(", confirmUnitName=").append(confirmUnitName);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append("]");
        return sb.toString();
    }
}