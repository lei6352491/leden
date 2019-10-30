package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: EquipmentListDto
 * @Description: TODO
 * @date 2019/7/29 9:13
 * @Version 1.0
 **/
@ApiModel(value = "接入入参Dto")
@Data
public class EquipmentListDto extends PageDto {

    @ApiModelProperty(value = "设备类型")
    private String  chamberType;
    @ApiModelProperty(value = "注册时间（开始）")
    private Date beginDate;
    @ApiModelProperty(value = "注册时间（结束）")
    private Date endDate;
}
