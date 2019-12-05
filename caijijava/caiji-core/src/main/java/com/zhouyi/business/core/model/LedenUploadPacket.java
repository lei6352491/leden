package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: first
 * @Date: 下午1:50 2019/11/5
 * @Description:
**/
@Data
@ToString
public class LedenUploadPacket implements Serializable {
    private String pkId;

    private String equipmentId;

    private String ryjcxxcjbh;

    private String nodeSign;

    private String fileServer;

    private String fileLocation;

    private String dataType;

    private Date createDatetime;

    private String fileSuffix;

    /**
     * 解析状态(0为解析 1解析成功 2解析失败)
     */
    private String resolveStatus;

    private String resolveResultInfo;

    private Date resolveDatetime;

    private String uploadLogId;

    private String fileSize;

    private String fileMd5;

    private String cjdwdm;

    private static final long serialVersionUID = 1L;
}