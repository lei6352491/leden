package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 李秸康
 * @ClassNmae: SysUnitDto
 * @Description: TODO
 * @date 2019/6/21 18:01
 * @Version 1.0
 **/
@ApiModel(value = "部门分页条件对象模型")
public class SysUnitDto extends PageDto implements Serializable {

    @ApiModelProperty(value = "排序的字段")
    private String field; //排序的字段

    @ApiModelProperty(value = "上级部门编码")
    private String parentId;



    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
