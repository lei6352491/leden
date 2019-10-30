package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: CollectIncomplete
 * @Description: TODO
 * @date 2019/7/20 16:05
 * @Version 1.0
 **/
@Data
@ToString
public class CollectIncomplete {

    private String cjdwdm;
    private String collectCategoryId;
    private String ryjcxxcjbh;
    private String xm;
    private String gmsfhm;
    private String cjrxm;
    private String collectStatus;
    private String schedule;

    private String collectCategoryName;
    private String name;
    private Date csrq;
    private String ajlbdm;
    private String cjdwmc;
    private Date collectDate;

}
