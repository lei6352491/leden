package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenMatchCase implements Serializable {
    private String asjbh;

    private String ysxtAsjbh;

    private String xckybh;

    private String xczzhwkbh;

    private String ajlbdm;

    private String ssjzrmby;

    private String asjfsddXzqhdm;

    private String asjfsddDzmc;

    private String jyaq;

    private String sfmaPdbz;

    private String zwbdxtlxms;

    private String tqsjGajgjgdm;

    private String qusjGajgmc;

    private String tqryXm;

    private String tqryGmsfhm;

    private String tqryLxdh;

    private Date tqsj;

    private String deletag;

    private String annex;

    private static final long serialVersionUID = 1L;

    public String getAsjbh() {
        return asjbh;
    }

    public void setAsjbh(String asjbh) {
        this.asjbh = asjbh;
    }

    public String getYsxtAsjbh() {
        return ysxtAsjbh;
    }

    public void setYsxtAsjbh(String ysxtAsjbh) {
        this.ysxtAsjbh = ysxtAsjbh;
    }

    public String getXckybh() {
        return xckybh;
    }

    public void setXckybh(String xckybh) {
        this.xckybh = xckybh;
    }

    public String getXczzhwkbh() {
        return xczzhwkbh;
    }

    public void setXczzhwkbh(String xczzhwkbh) {
        this.xczzhwkbh = xczzhwkbh;
    }

    public String getAjlbdm() {
        return ajlbdm;
    }

    public void setAjlbdm(String ajlbdm) {
        this.ajlbdm = ajlbdm;
    }

    public String getSsjzrmby() {
        return ssjzrmby;
    }

    public void setSsjzrmby(String ssjzrmby) {
        this.ssjzrmby = ssjzrmby;
    }

    public String getAsjfsddXzqhdm() {
        return asjfsddXzqhdm;
    }

    public void setAsjfsddXzqhdm(String asjfsddXzqhdm) {
        this.asjfsddXzqhdm = asjfsddXzqhdm;
    }

    public String getAsjfsddDzmc() {
        return asjfsddDzmc;
    }

    public void setAsjfsddDzmc(String asjfsddDzmc) {
        this.asjfsddDzmc = asjfsddDzmc;
    }

    public String getJyaq() {
        return jyaq;
    }

    public void setJyaq(String jyaq) {
        this.jyaq = jyaq;
    }

    public String getSfmaPdbz() {
        return sfmaPdbz;
    }

    public void setSfmaPdbz(String sfmaPdbz) {
        this.sfmaPdbz = sfmaPdbz;
    }

    public String getZwbdxtlxms() {
        return zwbdxtlxms;
    }

    public void setZwbdxtlxms(String zwbdxtlxms) {
        this.zwbdxtlxms = zwbdxtlxms;
    }

    public String getTqsjGajgjgdm() {
        return tqsjGajgjgdm;
    }

    public void setTqsjGajgjgdm(String tqsjGajgjgdm) {
        this.tqsjGajgjgdm = tqsjGajgjgdm;
    }

    public String getQusjGajgmc() {
        return qusjGajgmc;
    }

    public void setQusjGajgmc(String qusjGajgmc) {
        this.qusjGajgmc = qusjGajgmc;
    }

    public String getTqryXm() {
        return tqryXm;
    }

    public void setTqryXm(String tqryXm) {
        this.tqryXm = tqryXm;
    }

    public String getTqryGmsfhm() {
        return tqryGmsfhm;
    }

    public void setTqryGmsfhm(String tqryGmsfhm) {
        this.tqryGmsfhm = tqryGmsfhm;
    }

    public String getTqryLxdh() {
        return tqryLxdh;
    }

    public void setTqryLxdh(String tqryLxdh) {
        this.tqryLxdh = tqryLxdh;
    }

    public Date getTqsj() {
        return tqsj;
    }

    public void setTqsj(Date tqsj) {
        this.tqsj = tqsj;
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
        sb.append(", asjbh=").append(asjbh);
        sb.append(", ysxtAsjbh=").append(ysxtAsjbh);
        sb.append(", xckybh=").append(xckybh);
        sb.append(", xczzhwkbh=").append(xczzhwkbh);
        sb.append(", ajlbdm=").append(ajlbdm);
        sb.append(", ssjzrmby=").append(ssjzrmby);
        sb.append(", asjfsddXzqhdm=").append(asjfsddXzqhdm);
        sb.append(", asjfsddDzmc=").append(asjfsddDzmc);
        sb.append(", jyaq=").append(jyaq);
        sb.append(", sfmaPdbz=").append(sfmaPdbz);
        sb.append(", zwbdxtlxms=").append(zwbdxtlxms);
        sb.append(", tqsjGajgjgdm=").append(tqsjGajgjgdm);
        sb.append(", qusjGajgmc=").append(qusjGajgmc);
        sb.append(", tqryXm=").append(tqryXm);
        sb.append(", tqryGmsfhm=").append(tqryGmsfhm);
        sb.append(", tqryLxdh=").append(tqryLxdh);
        sb.append(", tqsj=").append(tqsj);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append("]");
        return sb.toString();
    }
}