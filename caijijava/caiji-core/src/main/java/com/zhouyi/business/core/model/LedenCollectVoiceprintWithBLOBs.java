package com.zhouyi.business.core.model;

import java.io.Serializable;

public class LedenCollectVoiceprintWithBLOBs extends LedenCollectVoiceprint implements Serializable {
    private byte[] ypsj;

    private byte[] swsj;

    private static final long serialVersionUID = 1L;

    public byte[] getYpsj() {
        return ypsj;
    }

    public void setYpsj(byte[] ypsj) {
        this.ypsj = ypsj;
    }
    public byte[] getSwsj() {
        return swsj;
    }

    public void setSwsj(byte[] swsj) {
        this.swsj = swsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ypsj=").append(ypsj);
        sb.append(", swsj=").append(swsj);
        sb.append("]");
        return sb.toString();
    }
}