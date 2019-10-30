package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李秸康
 * @ClassNmae: SysUnitListDto
 * @Description: TODO 单位列表条件模型
 * @date 2019/8/7 16:10
 * @Version 1.0
 **/
@ApiModel(value = "部门列表分页条件")
@Data
public class SysUnitListDto extends PageDto {

    @ApiModelProperty(value = "上级部门编码")
    private String upperUnitCode;

    @ApiModelProperty(value = "部门编码")
    private String unitCode;

    @ApiModelProperty(value = "部门名称")
    private String unitName;

    @ApiModelProperty(value = "部门分类")
    private String unitCategory;

    @ApiModelProperty(value = "是否遍历下级",example = "1")
    private String cycle;
}
