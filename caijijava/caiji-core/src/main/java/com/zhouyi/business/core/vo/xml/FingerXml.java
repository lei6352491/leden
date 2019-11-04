package com.zhouyi.business.core.vo.xml;

import com.zhouyi.business.core.model.LedenCollectFinger;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: FingerXml
 * @Description: 指纹xml对象信息
 * @date 2019/7/6 10:11
 * @Version 1.0
 **/
public class FingerXml extends LedenCollectFinger {

    private String ryjcxxcjbh;

    private String zwzwdm;

    private String zzhwqsqkdm;

    private String zwZdyxx;

    private String zwTxspfxcd;

    private String zwTxczfxcd;

    private String zwTxfbl;

    private String zwTxysffms;

    private String zwTxzl;

    private byte[] zwTxsj;

    private String createUserId;

    private Date createDatetime;


    @Override
    public String getCreateUserId() {
        return createUserId;
    }

    @Override
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public Date getCreateDatetime() {
        return createDatetime;
    }

    @Override
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getZwzwdm() {
        return zwzwdm;
    }

    public void setZwzwdm(String zwzwdm) {
        this.zwzwdm = zwzwdm;
    }

    public String getZzhwqsqkdm() {
        return zzhwqsqkdm;
    }

    public void setZzhwqsqkdm(String zzhwqsqkdm) {
        this.zzhwqsqkdm = zzhwqsqkdm;
    }

    public String getZwZdyxx() {
        return zwZdyxx;
    }

    public void setZwZdyxx(String zwZdyxx) {
        this.zwZdyxx = zwZdyxx;
    }

    public String getZwTxspfxcd() {
        return zwTxspfxcd;
    }

    public void setZwTxspfxcd(String zwTxspfxcd) {
        this.zwTxspfxcd = zwTxspfxcd;
    }

    public String getZwTxczfxcd() {
        return zwTxczfxcd;
    }

    public void setZwTxczfxcd(String zwTxczfxcd) {
        this.zwTxczfxcd = zwTxczfxcd;
    }

    public String getZwTxfbl() {
        return zwTxfbl;
    }

    public void setZwTxfbl(String zwTxfbl) {
        this.zwTxfbl = zwTxfbl;
    }

    public String getZwTxysffms() {
        return zwTxysffms;
    }

    public void setZwTxysffms(String zwTxysffms) {
        this.zwTxysffms = zwTxysffms;
    }

    public String getZwTxzl() {
        return zwTxzl;
    }

    public void setZwTxzl(String zwTxzl) {
        this.zwTxzl = zwTxzl;
    }

    public byte[] getZwTxsj() {
        return zwTxsj;
    }

    public void setZwTxsj(byte[] zwTxsj) {
        this.zwTxsj = zwTxsj;
    }

    public FingerXml() {
    }

    public FingerXml(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }
}
