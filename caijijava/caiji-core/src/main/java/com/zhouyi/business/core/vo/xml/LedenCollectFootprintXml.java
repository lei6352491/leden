package com.zhouyi.business.core.vo.xml;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectFootprintXml
 * @Description: 足迹xml信息
 * @date 2019/7/4 14:17
 * @Version 1.0
 **/
public class LedenCollectFootprintXml {

    public String ryjcxxcjbh;

    public String zjbwdm;

    public String zjlxdm;

    public byte[] zjsj;

    public String xdhwdm;

    public String xydm;

    public String xxzldf;

    public String annex;

    public String createUserId;

    public Date createDatetime;

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getZjbwdm() {
        return zjbwdm;
    }

    public void setZjbwdm(String zjbwdm) {
        this.zjbwdm = zjbwdm;
    }

    public String getZjlxdm() {
        return zjlxdm;
    }

    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    public byte[] getZjsj() {
        return zjsj;
    }

    public void setZjsj(byte[] zjsj) {
        this.zjsj = zjsj;
    }

    public String getXdhwdm() {
        return xdhwdm;
    }

    public void setXdhwdm(String xdhwdm) {
        this.xdhwdm = xdhwdm;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getXxzldf() {
        return xxzldf;
    }

    public void setXxzldf(String xxzldf) {
        this.xxzldf = xxzldf;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }
}
