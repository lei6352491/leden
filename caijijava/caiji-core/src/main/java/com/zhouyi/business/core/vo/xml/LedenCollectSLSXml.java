package com.zhouyi.business.core.vo.xml;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectSLSXml
 * @Description: 体貌/特殊/身高体重足长数据类
 * @date 2019/7/2 17:38
 * @Version 1.0
 **/
public class LedenCollectSLSXml {
    public String ryjcxxcjbh;
    public String tmtzMs;
    public String tbtzMs;
    public String qtfmMs;
    public String sg;
    public String tz;
    public String zc;
    public String signlistnum;
    public LinkedList<LedenCollectSignXml> dataSon;



    public String getTbtzMs() {
        return tbtzMs;
    }

    public void setTbtzMs(String tbtzMs) {
        this.tbtzMs = tbtzMs;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getTmtzMs() {
        return tmtzMs;
    }

    public void setTmtzMs(String tmtzMs) {
        this.tmtzMs = tmtzMs;
    }

    public String getQtfmMs() {
        return qtfmMs;
    }

    public void setQtfmMs(String qtfmMs) {
        this.qtfmMs = qtfmMs;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getSignlistnum() {
        return signlistnum;
    }

    public void setSignlistnum(String signlistnum) {
        this.signlistnum = signlistnum;
    }


    public LinkedList<LedenCollectSignXml> getDataSon() {
        return dataSon;
    }

    public void setDataSon(LinkedList<LedenCollectSignXml> dataSon) {
        this.dataSon = dataSon;
    }
}
