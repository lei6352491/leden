package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(description = "共享信息数据对象")
@Data
@ToString
public class LedenShareApp implements Serializable {
    @ApiModelProperty(value = "主键id")
    private String appId;

    @ApiModelProperty(value = "应用key")
    private String appKey;

    @ApiModelProperty(value = "系统标识")
    private String appSign;

    @ApiModelProperty(value = "系统名称")
    private String appName;

    @ApiModelProperty(value = "应用描述")
    private String appDesc;

    @ApiModelProperty(value = "系统路径")
    private String appUrl;

    @ApiModelProperty(value = "IP地址")
    private String appIp;

    @ApiModelProperty(value = "删除标识",notes = "0未删除/1删除")
    private String deletag;

    @ApiModelProperty(value = "备注")
    private String annex;

    @ApiModelProperty(value = "创建人id")
    private String createUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createDatetime;

    @ApiModelProperty(value = "更新人id")
    private String updateUserId;

    @ApiModelProperty(value = "更新时间")
    private Date updateDatetime;


   @ApiModelProperty(value = "节点授权信息")
   private List<NodeEmpowersDto> nodeEmpowersDto;




    private static final long serialVersionUID = 1L;


    public void setCreateDatetime() {
        this.createDatetime=new Date();
    }

    public void setUpdateDatetime() {
        this.updateDatetime=new Date();
    }
}