package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


/**
  * @author 李秸康
  * @Description 修改人像属性rxDzwjgs--rxdzwjgs
  * @Modifydate 2019/7/4
  * @param
  * @return
 **/
public class LedenCollectPortrait implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String rxzplxdm;

    private String rxdzwjgs;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] rxtxsj;

    private String rxtp;

    private static final long serialVersionUID = 1L;

    public String getRxtp() {
        return rxtp;
    }

    public void setRxtp(String rxtp) {
        this.rxtp = rxtp;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getRxzplxdm() {
        return rxzplxdm;
    }

    public void setRxzplxdm(String rxzplxdm) {
        this.rxzplxdm = rxzplxdm;
    }

    public String getRxdzwjgs() {
        return rxdzwjgs;
    }

    public void setRxdzwjgs(String rxdzwjgs) {
        this.rxdzwjgs = rxdzwjgs;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public byte[] getRxtxsj() {
        return rxtxsj;
    }

    public void setRxtxsj(byte[] rxtxsj) {
        this.rxtxsj = rxtxsj;
    }

    @Override
    public String toString() {
        return "LedenCollectPortrait{" +
                "pkId='" + pkId + '\'' +
                ", ryjcxxcjbh='" + ryjcxxcjbh + '\'' +
                ", rxzplxdm='" + rxzplxdm + '\'' +
                ", rxdzwjgs='" + rxdzwjgs + '\'' +
                ", deletag='" + deletag + '\'' +
                ", annex='" + annex + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createDatetime=" + createDatetime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateDatetime=" + updateDatetime +
                ", rxtxsj=" + Arrays.toString(rxtxsj) +
                '}';
    }
}