package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: CollectNodeResult
 * @Description: TODO
 * @date 2019/7/31 10:19
 * @Version 1.0
 **/
@Data
@ToString
public class CollectNodeResult {
    private String pkId;
    private String collectCateGoryName;
    private String nodeCode;
    private String nodeName;
    private String deleteFlag;
    private String collectStatus;
    private String nodeOrd;
    private Date collectDate;
    private Integer isSkip;

}
