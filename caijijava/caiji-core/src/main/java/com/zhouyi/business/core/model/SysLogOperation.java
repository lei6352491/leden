package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "操作日志数据模型")
public class SysLogOperation implements Serializable {
    @ApiModelProperty(value = "主键id,新增不用传")
    private String pkId;
    @ApiModelProperty(value = "用户编码")
    private String userId;
    @ApiModelProperty(value = "用户IP地址")
    private String addreIp;
    @ApiModelProperty(value = "操作菜单编码")
    private String operateMenuCode;
    @ApiModelProperty(value = "操作时间")
    private String operateTime;
    @ApiModelProperty(value = "记录创建时间")
    private Date createDatetime;
    @ApiModelProperty(value = "操作者姓名")
    private String userName;
    @ApiModelProperty(value = "用户单位名")
    private String unitName;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddreIp() {
        return addreIp;
    }

    public void setAddreIp(String addreIp) {
        this.addreIp = addreIp;
    }

    public String getOperateMenuCode() {
        return operateMenuCode;
    }

    public void setOperateMenuCode(String operateMenuCode) {
        this.operateMenuCode = operateMenuCode;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public SysLogOperation() {
    }

    public SysLogOperation(String pkId, String userId, String addreIp) {
        this.pkId = pkId;
        this.userId = userId;
        this.addreIp = addreIp;
        this.createDatetime=new Date();
    }

    @Override
    public String toString() {
        return "SysLogOperation{" +
                "pkId='" + pkId + '\'' +
                ", userId='" + userId + '\'' +
                ", addreIp='" + addreIp + '\'' +
                ", operateMenuCode='" + operateMenuCode + '\'' +
                ", operateTime='" + operateTime + '\'' +
                ", createDatetime=" + createDatetime +
                ", userName='" + userName + '\'' +
                ", unitName='" + unitName + '\'' +
                '}';
    }
}