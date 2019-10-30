package com.zhouyi.business.core.model;

import java.io.Serializable;

public class LedenCollectPTotal implements Serializable {
    private String pkId;

    private String personid;

    private String collecttargetId;

    private Short total;

    private String target;

    private String dxxm; //新增字段：对象姓名


    public String getDxxm() {
        return dxxm;
    }

    public void setDxxm(String dxxm) {
        this.dxxm = dxxm;
    }

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getCollecttargetId() {
        return collecttargetId;
    }

    public void setCollecttargetId(String collecttargetId) {
        this.collecttargetId = collecttargetId;
    }

    public Short getTotal() {
        return total;
    }

    public void setTotal(Short total) {
        this.total = total;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", personid=").append(personid);
        sb.append(", collecttargetId=").append(collecttargetId);
        sb.append(", total=").append(total);
        sb.append(", target=").append(target);
        sb.append("]");
        return sb.toString();
    }
}