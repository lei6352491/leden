package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 李秸康
 * @ClassNmae: UserInfoReciverDto
 * @Description: 服务器接收数据对象
 * @date 2019/7/8 8:42
 * @Version 1.0
 **/
@ApiModel(value = "用户查询对象模型")
public class UserInfoReciverDto {

    @ApiModelProperty(value = "设备id,必传，目前我也不知道干啥得用的")
    private String equipmentCode;
    @ApiModelProperty(value = "用户账号",required = true,example = "lijiekang")
    private String userAccount;
    @ApiModelProperty(value = "用户密码",required = true,example = "19980920")
    private String userPassword;

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
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
}
