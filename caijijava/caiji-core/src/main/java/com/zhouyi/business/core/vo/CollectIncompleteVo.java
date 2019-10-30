package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.CollectIncomplete;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 杜承旭
 * @ClassNmae: CollectIncompleteVo
 * @Description: TODO
 * @date 2019/7/20 16:57
 * @Version 1.0
 **/
public class CollectIncompleteVo extends CollectIncomplete implements Serializable {
    private Integer page;

    private Integer startNo;

    private Integer size;

    private Integer endNo;

    private String overName;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CollectIncompleteVo that = (CollectIncompleteVo) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(startNo, that.startNo) &&
                Objects.equals(size, that.size) &&
                Objects.equals(endNo, that.endNo) &&
                Objects.equals(overName, that.overName) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), page, startNo, size, endNo, overName, dateStart, dateEnd);
    }
}
