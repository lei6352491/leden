package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: BbsInsertDto
 * @Description: TODO 新增数据模型
 * @date 2019/7/25 8:25
 * @Version 1.0
 **/
@Data
public class BbsInsertDto {

    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "内容")
    private String contentss;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "部门编码")
    private String unitCode;
    @ApiModelProperty(value = "删除标志")
    private String deletag;
    @ApiModelProperty(value = "备注")
    private String annex;
    @ApiModelProperty(value = "创建用户编码")
    private String createUserId;
    @ApiModelProperty(value = "创建时间",hidden = true)
     private Date createDatetime=new Date();
}
