package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.LedenCollectPerson;
import com.zhouyi.business.core.vo.combine.LedenCollectPersonCombine;

import java.io.Serializable;
import java.util.Date;

public class LedenConllectPersonVo extends LedenCollectPersonCombine implements Serializable {

    private Integer page;

    private Integer startNo;

    private Integer size;

    private Integer endNo;

    private String overName;

    private Integer total;

    private Date dateStart;

    private Date dateEnd;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "LedenConllectPersonVo{" +
                "page=" + page +
                ", startNo=" + startNo +
                ", size=" + size +
                ", endNo=" + endNo +
                ", overName='" + overName + '\'' +
                ", total=" + total +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}'+head.EQUIPMENT_CODE+"\t"+head.USER_CODE;
    }
}
