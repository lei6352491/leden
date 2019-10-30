package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 李秸康
 * @ClassNmae: SysLogOperationDto
 * @Description: TODO
 * @date 2019/6/24 10:25
 * @Version 1.0
 **/
@ApiModel(value = "系统操作日志查询条件模型")
@Data
public class SysLogOperationDto extends PageDto implements Serializable {

    @ApiModelProperty(value = "IP地址")
    private String ADDRE_IP;
    @ApiModelProperty(value = "用户名")
    private String USER_NAME;
    @ApiModelProperty(value = "菜单编码")
    private String OPERATE_MENU_CODE;
    @ApiModelProperty(value = "菜单名称")
    private String MENU_NAME;


}
