package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: ReportDto
 * @Description: TODO 报表数据模型
 * @date 2019/9/2 10:10
 * @Version 1.0
 **/
@ApiModel(value = "报表数据模型")
@Data
@NoArgsConstructor
public class ReportDto {
    @ApiModelProperty(value = "部门编码")
    private String unitCode;
    @ApiModelProperty(value = "上级部门编码")
    private String upperUnitCode;
    @ApiModelProperty(value = "部门名称")
    private String unitName;
    @ApiModelProperty(value = "采集总数")
    private  Integer collectSum;
    @ApiModelProperty(value = "采集节点集合")
    private List<CollectInfo> collectInfos;




    public ReportDto(String unitName, Integer collectSum) {
        this.unitName = unitName;
        this.collectSum = collectSum;
    }

    public CollectInfo createNewCollectInfo(Integer count,Integer count2){
        String s = Double.toString(new BigDecimal((float) count/count2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        CollectInfo collectInfo=new CollectInfo(count,s);
        return collectInfo;
    }



}
