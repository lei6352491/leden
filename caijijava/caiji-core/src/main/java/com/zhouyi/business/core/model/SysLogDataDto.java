package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李秸康
 * @ClassNmae: SysLogDataDto
 * @Description: TODO
 * @date 2019/8/19 9:06
 * @Version 1.0
 **/
@ApiModel
@Data
public class SysLogDataDto{
   @ApiModelProperty(value = "对象姓名")
   private String personName;
   @ApiModelProperty(value = "采集人姓名")
   private String userName;
}
