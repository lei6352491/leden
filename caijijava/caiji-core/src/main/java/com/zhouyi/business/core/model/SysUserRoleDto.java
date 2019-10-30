package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: SysUserRoleDto
 * @Description: TODO 封装用户角色模型
 * @date 2019/8/8 16:09
 * @Version 1.0
 **/
@Data
@ApiModel("角色模型")
public class SysUserRoleDto {
    @ApiModelProperty(value = "角色关联主键")
    private String pkId;
    @ApiModelProperty(value = "角色Id")
    private String roleId;
   @ApiModelProperty(value = "删除标志")
    private String deleteFlag;
   @ApiModelProperty(value = "创建人Id")
    private String createUserId;
   @ApiModelProperty(value = "创建人姓名")
    private String createUserName;
   @ApiModelProperty(value = "创建时间")
    private Date createDatetime;
   @ApiModelProperty(value = "修改人id")
    private String updateUserId;
   @ApiModelProperty(value = "修改人姓名")
    private String updateUserName;
   @ApiModelProperty(value = "修改时间")
    private Date updateDatetime;
   @ApiModelProperty(value = "角色详情")
    private SysRole sysRole;
}
