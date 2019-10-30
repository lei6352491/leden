package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.SysDictList;

import java.io.Serializable;

public class SysDictListVo extends SysDictList implements Serializable {

    private Integer page;

    private Integer startNo;

    private Integer size;

    private Integer endNo;

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
        return endNo;
    }

    public void setEndNo(Integer endNo) {
        this.endNo = endNo;
    }

    public String getOverName() {
        return overName;
    }

    public void setOverName(String overName) {
        this.overName = overName;
    }


    @Override
    public String toString() {
        return "SysDictListVo{" +
                "page=" + page +
                ", startNo=" + startNo +
                ", size=" + size +
                ", endNo=" + endNo +
                ", overName='" + overName + '\'' +
                '}';
    }
}
