package com.zhouyi.business.dto;

import com.zhouyi.business.core.model.LedenShareApp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 李秸康
 * @ClassNmae: ShareAppUpdateDto
 * @Description: TODO 共享修改信息模型
 * @date 2019/8/12 16:19
 * @Version 1.0
 **/
@ApiModel(value = "共享修改信息模型")
public class ShareAppUpdateDto extends LedenShareApp {


    @ApiModelProperty(value = "主键id",required = true)
    private String appId;

    @ApiModelProperty(value = "修改人Id",required = true)
    private String updateUserId;


    @ApiModelProperty(hidden = true)
    private String createUserId;

    @ApiModelProperty(hidden = true)
    private String createDatetime;
}
