package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: BbsConditionsDto
 * @Description: TODO 公告添加模型
 * @date 2019/7/19 17:20
 * @Version 1.0
 **/
@Data
@ApiModel(value = "公告查询条件")
public class BbsConditionsDto extends PageDto implements Serializable {
   @ApiModelProperty(value = "公告标题")
    private String title;
    @ApiModelProperty(value="是否草稿？1：0")
    private int draft;
    @ApiModelProperty(value = "是否发布？1:0")
    private int publish;
    @ApiModelProperty(value = "开始时间")
    private Date beginDate;
    @ApiModelProperty(value = "结束时间")
    private Date endDate;
    @ApiModelProperty(value = "公告排序字段")
    private String sort2;
}
