package com.zhouyi.business.core.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author 杜承旭
 * @ClassNmae: CollectNodeVo
 * @Description: TODO
 * @date 2019/7/31 10:14
 * @Version 1.0
 **/
@Data
@ToString
public class CollectNodeVo {

    private String collectTypeCode;
    private String collectUnitCode;

    private Integer page;
    private Integer startNo;
    private Integer size;
    private Integer endNo;

}
