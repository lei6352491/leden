package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(value = "单位实体模型")
public class SysUnit implements Serializable {
    @ApiModelProperty(value = "单位编码",required = true)
    private String unitCode;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    private String unitFullName;

    private String unitLeader;

    private String unitPhone;

    private BigDecimal unitLongitude;

    private BigDecimal unitLatitude;

    private String upperUnitCode;

    private String unitLevel;

    private String unitCategory;

    private String deleteFlag;

    private String remark;
    @ApiModelProperty(value = "创建人：当前登陆用户编码",notes = "新增必传")
    private String createUserId;

    private Date createDatetime;
    @ApiModelProperty(value = "修改人：当前登陆用户编码",notes = "修改必传")
    private String updateUserId;

    private Date updateDatetime;
    @ApiModelProperty(value = "子部门集合",hidden = true)
    private List<SysUnit> childUnit;

    private boolean boo=false;

    private Integer isLeaf;

    @ApiModelProperty(value = "创建用户名",hidden = true)
    private String createUserName;
    @ApiModelProperty(value = "修改用户名",hidden = true)
    private String modifyUserName;

    @ApiModelProperty(value = "部门分类名称")
    private String categoryName;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isBoo() {
        return boo;
    }

    public void setBoo(boolean boo) {
        this.boo = boo;
    }

    public List<SysUnit> getChildUnit() {
        return childUnit;
    }

    public void setChildUnit(List<SysUnit> childUnit) {
        this.childUnit = childUnit;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitFullName() {
        return unitFullName;
    }

    public void setUnitFullName(String unitFullName) {
        this.unitFullName = unitFullName;
    }

    public String getUnitLeader() {
        return unitLeader;
    }

    public void setUnitLeader(String unitLeader) {
        this.unitLeader = unitLeader;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public BigDecimal getUnitLongitude() {
        return unitLongitude;
    }

    public void setUnitLongitude(BigDecimal unitLongitude) {
        this.unitLongitude = unitLongitude;
    }

    public BigDecimal getUnitLatitude() {
        return unitLatitude;
    }

    public void setUnitLatitude(BigDecimal unitLatitude) {
        this.unitLatitude = unitLatitude;
    }

    public String getUpperUnitCode() {
        return upperUnitCode;
    }

    public void setUpperUnitCode(String upperUnitCode) {
        this.upperUnitCode = upperUnitCode;
    }

    public String getUnitLevel() {
        return unitLevel;
    }

    public void setUnitLevel(String unitLevel) {
        this.unitLevel = unitLevel;
    }

    public String getUnitCategory() {
        return unitCategory;
    }

    public void setUnitCategory(String unitCategory) {
        this.unitCategory = unitCategory;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", unitCode=").append(unitCode);
        sb.append(", unitName=").append(unitName);
        sb.append(", unitFullName=").append(unitFullName);
        sb.append(", unitLeader=").append(unitLeader);
        sb.append(", unitPhone=").append(unitPhone);
        sb.append(", unitLongitude=").append(unitLongitude);
        sb.append(", unitLatitude=").append(unitLatitude);
        sb.append(", upperUnitCode=").append(upperUnitCode);
        sb.append(", unitLevel=").append(unitLevel);
        sb.append(", unitCategory=").append(unitCategory);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", remark=").append(remark);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append("]");
        return sb.toString();
    }
}