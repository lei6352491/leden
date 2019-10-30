package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: CollectInfo
 * @Description: TODO
 * @date 2019/9/3 17:28
 * @Version 1.0
 **/

@Data
@ApiModel(value = "节点信息模型")
@NoArgsConstructor
public class CollectInfo {
    @ApiModelProperty(value = "节点编码")
    private String nodeCode;
    @ApiModelProperty(value = "节点名称")
    private String nodeName;
    @ApiModelProperty(value = "采集数量")
    private Integer collectNum;
    @ApiModelProperty(value = "采集率")
    private String collectRate;
    @ApiModelProperty(value = "标识")
    private String nodeSign;

    public CollectInfo(Integer collectNum, String collectRate) {
        this.collectNum = collectNum;
        this.collectRate = collectRate;
    }

    public CollectInfo(Integer collectNum) {
        this.collectNum = collectNum;
    }
}
