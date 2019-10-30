package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杜承旭
 * @ClassNmae: PortraitResult
 * @Description: TODO
 * @date 2019/8/30 10:43
 * @Version 1.0
 **/
@Data
@ToString
public class PortraitResult implements Serializable {

    private String pkId;

    private String ryjcxxcjbh;

    private String rxzplxdm;

    private String rxdzwjgs;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private Map<String,String> txzps = new HashMap<>();
}
