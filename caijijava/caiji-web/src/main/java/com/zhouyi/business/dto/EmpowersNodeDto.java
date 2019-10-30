package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: EmpowersNodeDto
 * @Description: TODO 共享节点对象模型
 * @date 2019/8/7 11:38
 * @Version 1.0
 **/
@ApiModel(value = "共享授权对象")
@Data
public class EmpowersNodeDto {

    @ApiModelProperty(value = "应用id")
    private String appId;
    @ApiModelProperty(value = "分享类型")
    private String shareType;
    @ApiModelProperty(value = "节点信息")
    private String nodeSign;
    @ApiModelProperty(value = "删除标志")
    private String deletag="0";
    @ApiModelProperty(value = "备注")
    private String annex;
    @ApiModelProperty(value = "创建人id",hidden = true)
    private String createUserId;
    @ApiModelProperty(value = "记录创建时间",hidden = true)
    private Date createDatetime;
    @ApiModelProperty(value = "记录更新人ID")
    private String updateUserId;
    @ApiModelProperty(value = "记录更新时间",hidden = true)
    private Date updateDatetime;



    public void setUpdateDatetime() {
        this.updateDatetime = new Date();
    }
}
