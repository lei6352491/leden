package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class SysUserTransfer implements Serializable {
    private String transferCode;

    private String userCode;

    private String lastUserCode;

    private String newUserCode;

    private Date transferDate;

    private String transferReason;

    private static final long serialVersionUID = 1L;

    public String getTransferCode() {
        return transferCode;
    }

    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLastUserCode() {
        return lastUserCode;
    }

    public void setLastUserCode(String lastUserCode) {
        this.lastUserCode = lastUserCode;
    }

    public String getNewUserCode() {
        return newUserCode;
    }

    public void setNewUserCode(String newUserCode) {
        this.newUserCode = newUserCode;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", transferCode=").append(transferCode);
        sb.append(", userCode=").append(userCode);
        sb.append(", lastUserCode=").append(lastUserCode);
        sb.append(", newUserCode=").append(newUserCode);
        sb.append(", transferDate=").append(transferDate);
        sb.append(", transferReason=").append(transferReason);
        sb.append("]");
        return sb.toString();
    }
}