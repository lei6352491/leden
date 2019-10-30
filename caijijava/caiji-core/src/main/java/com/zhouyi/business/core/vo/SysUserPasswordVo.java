package com.zhouyi.business.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 李秸康
 * @ClassNmae: SysUserPasswordVo
 * @Description: TODO 用户修改密码VO
 * @date 2019/7/22 13:35
 * @Version 1.0
 **/
@ApiModel(value = "修改密码数据模型")
public class SysUserPasswordVo {

    @ApiModelProperty(value = "当前登陆用户编码",required = true)
    private String userCode;
    @ApiModelProperty(value = "用户的新密码",required = true)
    private String userPassword;


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
