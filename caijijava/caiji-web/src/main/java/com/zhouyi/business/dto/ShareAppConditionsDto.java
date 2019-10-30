package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李秸康
 * @ClassNmae: ShareAppConditionsDto
 * @Description: TODO
 * @date 2019/8/5 8:51
 * @Version 1.0
 **/
@ApiModel("共享信息条件对象")
@Data
public class ShareAppConditionsDto extends PageDto{
    @ApiModelProperty(value = "系统标识")
    private String appSign;
    @ApiModelProperty(value = "系统名称")
    private String appName;
    @ApiModelProperty(value = "系统地址")
    private String appUrl;

}
