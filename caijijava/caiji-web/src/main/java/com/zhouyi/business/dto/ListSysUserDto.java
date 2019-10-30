package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李秸康
 * @ClassNmae: ListSysUserDto
 * @Description: TODO 用户列表dto
 * @date 2019/7/22 15:42
 * @Version 1.0
 **/
@Data
@ApiModel(value = "用户列表条件模型")
public class ListSysUserDto extends PageDto{
    @ApiModelProperty(value = "用户账户名")
    private String userAccount;
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    @ApiModelProperty(value = "警号")
    private String userPoliceno;
    @ApiModelProperty(value = "是否遍历下级部门",notes = "1标识显示下级，0标识不显示下级")
    private Integer cycle=1;
    @ApiModelProperty(value = "当前用户部门编号",required = true)
    private String unitCode;
}
