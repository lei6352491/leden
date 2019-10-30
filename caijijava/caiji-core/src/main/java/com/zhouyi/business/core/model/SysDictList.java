package com.zhouyi.business.core.model;

import java.io.Serializable;

public class SysDictList implements Serializable {
    private String code;

    private String name;

    private String sign;

    private String deleteFlag;

    private Short ord;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Short getOrd() {
        return ord;
    }

    public void setOrd(Short ord) {
        this.ord = ord;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", sign=").append(sign);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", ord=").append(ord);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}