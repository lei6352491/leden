package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(value = "系统用户数据模型")
public class SysUser implements Serializable {
    @ApiModelProperty(value = "用户编码")
    private String userCode;
    @ApiModelProperty(value = "用户账户")
    private String userAccount;
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户性别码",example = "0/1")
    private String userSexCode;
    @ApiModelProperty(value = "用户身份证号码",example = "430602.....")
    private String userIdcardno;
    @ApiModelProperty(value = "用户类型",notes = "0:民警/1：协警")
    private String userType;
    @ApiModelProperty(value = "用户警号",notes = "民警必填，协警不必填")
    private String userPoliceno;
    @ApiModelProperty(value = "用户手机号",example = "175......")
    private String userPhone;
    @ApiModelProperty(value = "用户邮箱",example = "xxxx@xx.com")
    private String userEmail;
    @ApiModelProperty(value = "用户部门编码",required = true)
    private String userUnitCode;
    @ApiModelProperty(value = "用户状态",notes = "0在线/1不在线")
    private String userStatus;

    @ApiModelProperty(value = "删除标志",required = true,example = "0")
    private String deleteFlag;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "记录创建人id")
    private String createUserId;
    @ApiModelProperty(value = "记录创建时间")
    private Date createDatetime;
    @ApiModelProperty(value = "更新人id")
    private String updateUserId;
    @ApiModelProperty(value = "更新时间")
    private Date updateDatetime;
    @ApiModelProperty(value = "用户密码盐值",hidden = true)
    private String salt;
    @ApiModelProperty(value = "用户部门名称")
    private String userUnitName;
    @ApiModelProperty(value = "用户的角色列表集合")
    private List<SysUserRoleDto> roles;
    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;
    @ApiModelProperty(value = "修改人姓名")
    private String updateUserName;
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    private Date expDate;

    private LedenEquipment ledenEquipment;

    public LedenEquipment getLedenEquipment() {
        return ledenEquipment;
    }

    public void setLedenEquipment(LedenEquipment ledenEquipment) {
        this.ledenEquipment = ledenEquipment;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public List<SysUserRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<SysUserRoleDto> roles) {
        this.roles = roles;
    }

    public String getUserUnitName() {
        return userUnitName;
    }

    public void setUserUnitName(String userUnitName) {
        this.userUnitName = userUnitName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSexCode() {
        return userSexCode;
    }

    public void setUserSexCode(String userSexCode) {
        this.userSexCode = userSexCode;
    }

    public String getUserIdcardno() {
        return userIdcardno;
    }

    public void setUserIdcardno(String userIdcardno) {
        this.userIdcardno = userIdcardno;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPoliceno() {
        return userPoliceno;
    }

    public void setUserPoliceno(String userPoliceno) {
        this.userPoliceno = userPoliceno;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserUnitCode() {
        return userUnitCode;
    }

    public void setUserUnitCode(String userUnitCode) {
        this.userUnitCode = userUnitCode;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userCode=").append(userCode);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userName=").append(userName);
        sb.append(", userSexCode=").append(userSexCode);
        sb.append(", userIdcardno=").append(userIdcardno);
        sb.append(", userType=").append(userType);
        sb.append(", userPoliceno=").append(userPoliceno);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userUnitCode=").append(userUnitCode);
        sb.append(", userStatus=").append(userStatus);
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