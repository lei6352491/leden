package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class SysUnitTransfer implements Serializable {
    private String transferCode;

    private String lastUnitCode;

    private String newUnitCode;

    private String lastUpperunitCode;

    private String newUpperunitCode;

    private Date transferDate;

    private String transferReason;

    private static final long serialVersionUID = 1L;

    public String getTransferCode() {
        return transferCode;
    }

    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode;
    }

    public String getLastUnitCode() {
        return lastUnitCode;
    }

    public void setLastUnitCode(String lastUnitCode) {
        this.lastUnitCode = lastUnitCode;
    }

    public String getNewUnitCode() {
        return newUnitCode;
    }

    public void setNewUnitCode(String newUnitCode) {
        this.newUnitCode = newUnitCode;
    }

    public String getLastUpperunitCode() {
        return lastUpperunitCode;
    }

    public void setLastUpperunitCode(String lastUpperunitCode) {
        this.lastUpperunitCode = lastUpperunitCode;
    }

    public String getNewUpperunitCode() {
        return newUpperunitCode;
    }

    public void setNewUpperunitCode(String newUpperunitCode) {
        this.newUpperunitCode = newUpperunitCode;
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
        sb.append(", lastUnitCode=").append(lastUnitCode);
        sb.append(", newUnitCode=").append(newUnitCode);
        sb.append(", lastUpperunitCode=").append(lastUpperunitCode);
        sb.append(", newUpperunitCode=").append(newUpperunitCode);
        sb.append(", transferDate=").append(transferDate);
        sb.append(", transferReason=").append(transferReason);
        sb.append("]");
        return sb.toString();
    }
}