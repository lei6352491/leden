package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.LedenCollectPerson;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: LedenConllectPersonVo2
 * @Description: TODO
 * @date 2019/7/23 15:51
 * @Version 1.0
 **/
public class LedenConllectPersonVo2 extends LedenCollectPerson implements Serializable {

    private String sign;

    private Integer page;

    private Integer startNo;

    private Integer size;

    private Integer endNo;

    private String overName;

    private Integer total;

    private String fileSuffix;

    private Date dateStart;

    private Date dateEnd;

    private String dataType;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }


}

