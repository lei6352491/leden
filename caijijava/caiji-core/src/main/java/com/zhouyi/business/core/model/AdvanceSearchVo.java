package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: AdvanceSearchVo
 * @Description: TODO
 * @date 2019/7/26 15:06
 * @Version 1.0
 **/
@Data
public class AdvanceSearchVo implements Serializable {

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

    public Date getCjsj() {
        if(this.cjsj!=null)
           return new Date(this.cjsj.getTime());
        else
            return null;
    }


    public void setCjsj(Date cjsj) {
        if(cjsj!=null)
            this.cjsj=(Date)cjsj.clone();
        else
            this.cjsj=null;
    }

    @ApiModelProperty(value = "采集开始时间")
    private Date beginDate;

    public Date getBeginDate() {
        if(this.beginDate!=null)
            return new Date(this.beginDate.getTime());
        else
            return null;
    }

    public void setBeginDate(Date beginDate) {
        if(beginDate!=null)
            this.beginDate=(Date)beginDate.clone();
        else
            this.beginDate=null;
    }

    @ApiModelProperty(value = "采集结束时间")
    private Date endDate;

    public Date getEndDate() {
        if(this.endDate!=null)
            return new Date(this.endDate.getTime());
        else
            return null;
    }

    public void setEndDate(Date endDate) {
        if(endDate!=null)
            this.endDate=(Date)endDate.clone();
        else
            this.endDate=null;
    }

    @ApiModelProperty(value = "最小年龄")
    private String minAge;
    @ApiModelProperty(value = "最大年龄")
    private String maxAge;

    private Date startDate;

    public Date getStartDate() {
        if(this.startDate!=null)
            return new Date(this.startDate.getTime());
        else
            return null;
    }

    public void setStartDate(Date startDate) {
        if(startDate!=null)
            this.startDate=(Date)startDate.clone();
        else
            this.startDate=null;
    }

    private Date stopDate;


    public Date getStopDate() {
        if(this.stopDate!=null)
            return new Date(this.stopDate.getTime());
        else
            return null;
    }

    public void setStopDate(Date stopDate) {
        if(stopDate!=null)
            this.stopDate=(Date)stopDate.clone();
        else
            this.stopDate=null;
    }

    private String sign;

    @ApiModelProperty(value = "页码")
    private int pNo;
    @ApiModelProperty(value = "页面大小")
    private int pSize;
}
