package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: SysUserDto
 * @Description: 用户登陆Dto
 * @date 2019/6/22 10:02
 * @Version 1.0
 **/
@ApiModel(value = "用户条件对象模型")
public class SysUserDto extends PageDto implements Serializable {
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
    @ApiModelProperty(value = "记录创建时间",hidden = true)
    private Date createDatetime;
    @ApiModelProperty(value = "更新人id")
    private String updateUserId;
    @ApiModelProperty(value = "更新人时间",hidden = true)
    private Date updateDatetime;
    @ApiModelProperty(value = "角色列表")
    private String[] roleIds;

    private String equipmentIp;

    private String equipmentMac;

    public String getEquipmentIp() {
        return equipmentIp;
    }

    public void setEquipmentIp(String equipmentIp) {
        this.equipmentIp = equipmentIp;
    }

    public String getEquipmentMac() {
        return equipmentMac;
    }

    public void setEquipmentMac(String equipmentMac) {
        this.equipmentMac = equipmentMac;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
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

    /**
     * 修改创建和修改的日期未系统当前日期，不可操作
     */
    public void setCreateDatetime() {
        this.createDatetime = new Date();
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

    public void setUpdateDatetime() {
        this.updateDatetime = new Date();
    }


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
