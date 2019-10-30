package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李秸康
 * @ClassNmae: SysDataLogConditionsDto
 * @Description: TODO
 * @date 2019/8/19 16:12
 * @Version 1.0
 **/
@ApiModel(value = "数据日志条件")
@Data
public class SysDataLogConditionsDto extends PageDto{
    @ApiModelProperty(value = "对象姓名")
    private String personName;
    @ApiModelProperty(value = "采集人姓名")
    private String userName;
    @ApiModelProperty(value = "IP地址")
    private String addreIp;
}
