package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: AdvanceSearchDto
 * @Description: TODO 高级查询条件模型
 * @date 2019/7/25 9:57
 * @Version 1.0
 **/
@ApiModel(value = "高级查询对象模型")
@Data
public class AdvanceSearchDto extends PageDto{

    @ApiModelProperty(value = "采集单位代码")
    private String cjdwdm;
    @ApiModelProperty(value = "采集单位名称")
    private String cjdwmc;
    @ApiModelProperty(value = "所属采集类别")
    private String idLedenCollectCategory;
    @ApiModelProperty(value = "人员编号")
    private String cjrybh;
    @ApiModelProperty(value = "姓名")
    private String xm;
    @ApiModelProperty(value = "外文名称")
    private String wwxm;
    @ApiModelProperty(value = "身份证号")
    private String gmsfhm;
    @ApiModelProperty(value = "民族代码")
    private String mzdm;
    @ApiModelProperty(value = "籍贯省市县代码")
    private String jgssxdm;
    @ApiModelProperty(value = "户籍地行政区划")
    private String hjdssxdm;
    @ApiModelProperty(value = "出生地行政区划")
    private String csdssxdm;
    @ApiModelProperty(value = "案件类别代码")
    private String ajlbdm;
    @ApiModelProperty(value = "DNA编号")
    private String rydnabh;
    @ApiModelProperty(value = "吸毒检测结果")
    private String xdjcjg;
    @ApiModelProperty(value = "采集人姓名")
    private String cjrxm;
    @ApiModelProperty(value = "采集状态码")
    private String status;
    @ApiModelProperty(value = "是否遍历下级？0：1")
    private String round;

    private Date cjsj;
    @ApiModelProperty(value = "采集开始时间")
    private Date beginDate;
    @ApiModelProperty(value = "采集结束时间")
    private Date endDate;
    @ApiModelProperty(value = "最小年龄")
    private String minAge;
    @ApiModelProperty(value = "最大年龄")
    private String maxAge;
}
