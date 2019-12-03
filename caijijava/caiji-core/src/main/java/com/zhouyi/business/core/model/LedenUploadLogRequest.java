package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: LedenUploadLogRequest
 * @Description: TODO
 * @date 2019/12/3 15:51
 * @Version 1.0
 **/

@Data
@ToString
public class LedenUploadLogRequest {
    private String ryjcxxcjbh;
    private String jzrybh;
    private String cjdwdm;
    private String uploadStatus;
    private Date startDate;
    private Date endDate;

    private Integer page;
    private Integer size;
}
