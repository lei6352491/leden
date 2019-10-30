package com.zhouyi.business.model;

import javax.xml.crypto.Data;
import java.io.Serializable;

public class SearchParam implements Serializable {

    //全文检索参数
    private String searchParam;

    //性别过滤
    private String xbdm;

    //出生时间范围过滤
    private Data csrqStart;
    private Data csrqEnd;

    //采集时间范围
    private Data cjsjStart;
    private Data cjsjEnd;

    //分页参数
    private Integer page;
    private Integer size;

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }

    public String getXbdm() {
        return xbdm;
    }

    public void setXbdm(String xbdm) {
        this.xbdm = xbdm;
    }

    public Data getCsrqStart() {
        return csrqStart;
    }

    public void setCsrqStart(Data csrqStart) {
        this.csrqStart = csrqStart;
    }

    public Data getCsrqEnd() {
        return csrqEnd;
    }

    public void setCsrqEnd(Data csrqEnd) {
        this.csrqEnd = csrqEnd;
    }

    public Data getCjsjStart() {
        return cjsjStart;
    }

    public void setCjsjStart(Data cjsjStart) {
        this.cjsjStart = cjsjStart;
    }

    public Data getCjsjEnd() {
        return cjsjEnd;
    }

    public void setCjsjEnd(Data cjsjEnd) {
        this.cjsjEnd = cjsjEnd;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "SearchParam{" +
                "searchParam='" + searchParam + '\'' +
                ", xbdm='" + xbdm + '\'' +
                ", csrqStart=" + csrqStart +
                ", csrqEnd=" + csrqEnd +
                ", cjsjStart=" + cjsjStart +
                ", cjsjEnd=" + cjsjEnd +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
