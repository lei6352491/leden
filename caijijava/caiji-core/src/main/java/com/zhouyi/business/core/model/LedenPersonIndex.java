package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenPersonIndex implements Serializable {
    private String pkId;

    private Date createDatetime;

    private Short count;

    private String unitCode;

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Short getCount() {
        return count;
    }

    public void setCount(Short count) {
        this.count = count;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", count=").append(count);
        sb.append(", unitCode=").append(unitCode);
        sb.append("]");
        return sb.toString();
    }
}