package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.SysBaseConf;

import java.io.Serializable;

public class SysBaseConfVo extends SysBaseConf implements Serializable {

    private Integer page;

    private Integer startNo;

    private Integer size;

    private Integer EndNo;

    private String overName;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStartNo() {
        return startNo;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getEndNo() {
        return EndNo;
    }

    public void setEndNo(Integer endNo) {
        EndNo = endNo;
    }

    public String getOverName() {
        return overName;
    }

    public void setOverName(String overName) {
        this.overName = overName;
    }

    @Override
    public String toString() {
        return "SysBaseConfVo{" +
                "page=" + page +
                ", startNo=" + startNo +
                ", size=" + size +
                ", EndNo=" + EndNo +
                ", overName='" + overName + '\'' +
                '}';
    }
}
