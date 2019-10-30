package com.zhouyi.business.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentParetsDto
 * @Description: TODO
 * @date 2019/6/25 10:33
 * @Version 1.0
 **/
public class LedenEquipmentParetsDto extends BaseDto implements Serializable {

    private String pkId;

    private String nodeCode;

    private String paretsName;

    private String paretsPlug;

    private String paretsPlugVersion;

    private String deleteFlag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getParetsName() {
        return paretsName;
    }

    public void setParetsName(String paretsName) {
        this.paretsName = paretsName;
    }

    public String getParetsPlug() {
        return paretsPlug;
    }

    public void setParetsPlug(String paretsPlug) {
        this.paretsPlug = paretsPlug;
    }

    public String getParetsPlugVersion() {
        return paretsPlugVersion;
    }

    public void setParetsPlugVersion(String paretsPlugVersion) {
        this.paretsPlugVersion = paretsPlugVersion;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
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
}
