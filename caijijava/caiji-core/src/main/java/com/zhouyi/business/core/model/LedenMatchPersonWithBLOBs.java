package com.zhouyi.business.core.model;

import java.io.Serializable;

public class LedenMatchPersonWithBLOBs extends LedenMatchPerson implements Serializable {
    private byte[] jdxp;

    private byte[] rxTxsj;

    private static final long serialVersionUID = 1L;

    public byte[] getJdxp() {
        return jdxp;
    }

    public void setJdxp(byte[] jdxp) {
        this.jdxp = jdxp;
    }

    public byte[] getRxTxsj() {
        return rxTxsj;
    }

    public void setRxTxsj(byte[] rxTxsj) {
        this.rxTxsj = rxTxsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jdxp=").append(jdxp);
        sb.append(", rxTxsj=").append(rxTxsj);
        sb.append("]");
        return sb.toString();
    }
}