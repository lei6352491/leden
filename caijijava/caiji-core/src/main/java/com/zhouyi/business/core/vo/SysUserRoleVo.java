package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.SysUserRole;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: SysUserRoleVo
 * @Description: TODO
 * @date 2019/7/15 16:06
 * @Version 1.0
 **/
public class SysUserRoleVo extends SysUserRole implements Serializable {

    private Integer page;

    private Integer startNo;

    private Integer size;

    private Integer endNo;

    private String overName;

    private Date dateStart;

    private Date dateEnd;

    public SysUserRoleVo(String pkId, String userId, String roleId, String deleteFlag, String createUserId, Date createDatetime) {
        super(pkId, userId, roleId, deleteFlag, createUserId, createDatetime);
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
}
