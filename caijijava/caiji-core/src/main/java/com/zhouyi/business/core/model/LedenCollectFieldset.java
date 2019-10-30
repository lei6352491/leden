package com.zhouyi.business.core.model;

import java.io.Serializable;

public class LedenCollectFieldset implements Serializable {
    private String pkId;

    private String nodeCode;

    private String fieldName;

    private String field;

    private String rule;

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", nodeCode=").append(nodeCode);
        sb.append(", fieldName=").append(fieldName);
        sb.append(", field=").append(field);
        sb.append(", rule=").append(rule);
        sb.append("]");
        return sb.toString();
    }
}