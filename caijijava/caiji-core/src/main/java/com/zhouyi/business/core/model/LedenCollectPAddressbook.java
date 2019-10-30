package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectPAddressbook extends LedenCollectPAddressbookKey implements Serializable {
    private String txlzdlxdm;

    private String lxdhlx;

    private String gxrlxfs;

    private String xxscPdbz;

    private String txlxmc;

    private Date xxscClsj;

    private static final long serialVersionUID = 1L;

    public String getTxlxmc() {
        return txlxmc;
    }

    public void setTxlxmc(String txlxmc) {
        this.txlxmc = txlxmc;
    }

    public String getTxlzdlxdm() {
        return txlzdlxdm;
    }

    public void setTxlzdlxdm(String txlzdlxdm) {
        this.txlzdlxdm = txlzdlxdm;
    }

    public String getLxdhlx() {
        return lxdhlx;
    }

    public void setLxdhlx(String lxdhlx) {
        this.lxdhlx = lxdhlx;
    }

    public String getGxrlxfs() {
        return gxrlxfs;
    }

    public void setGxrlxfs(String gxrlxfs) {
        this.gxrlxfs = gxrlxfs;
    }

    public String getXxscPdbz() {
        return xxscPdbz;
    }

    public void setXxscPdbz(String xxscPdbz) {
        this.xxscPdbz = xxscPdbz;
    }

    public Date getXxscClsj() {
        return xxscClsj;
    }

    public void setXxscClsj(Date xxscClsj) {
        this.xxscClsj = xxscClsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", txlzdlxdm=").append(txlzdlxdm);
        sb.append(", lxdhlx=").append(lxdhlx);
        sb.append(", gxrlxfs=").append(gxrlxfs);
        sb.append(", xxscPdbz=").append(xxscPdbz);
        sb.append(", xxscClsj=").append(xxscClsj);
        sb.append("]");
        return sb.toString();
    }
}