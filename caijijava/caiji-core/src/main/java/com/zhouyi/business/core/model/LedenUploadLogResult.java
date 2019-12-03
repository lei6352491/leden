package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: LedenUploadLogResult
 * @Description: TODO
 * @date 2019/12/3 15:39
 * @Version 1.0
 **/

@Data
@ToString
public class LedenUploadLogResult extends LedenCollectPerson implements Serializable {
    private String pkId;

    private String equipmentId;

    //    @Description("节点标示")
    private String nodeSign;

    //    @Description("是否授权")
    private String isEmpower;

    //    @Description("上传id")
    private String uploadIp;

    private String uploadStatus;

    private String uploadResultInfo;

    private Date uploadDatetime;

    private Date createDatetime;

    private Integer isGetCode;

    private Date resolveDatetime;
}
