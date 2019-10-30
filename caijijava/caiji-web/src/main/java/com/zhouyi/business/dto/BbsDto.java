package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: BbsDto
 * @Description: TODO
 * @date 2019/6/24 14:46
 * @Version 1.0
 **/
@ApiModel(value = "公告数据模型")
public class BbsDto extends PageDto implements Serializable {
    @ApiModelProperty(value = "主键Id,新增可不传")
    private String pkId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "内容")
    private String contentss;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "部门编码")
    private String unitCode;
    @ApiModelProperty(value = "删除标志")
    private String deletag;
    @ApiModelProperty(value = "备注")
    private String annex;
    @ApiModelProperty(value = "创建用户编码")
    private String createUserId;
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createDatetime;
    @ApiModelProperty(value = "更新用户编码")
    private String updateUserId;
    @ApiModelProperty(value = "更新时间",hidden = true)
    private Date updateDatetime;



    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentss() {
        return contentss;
    }

    public void setContentss(String contentss) {
        this.contentss = contentss;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getDeletag() {
        return deletag;
    }

    public void setDeletag(String deletag) {
        this.deletag = deletag;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime() {
        this.createDatetime = new Date();
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime() {
        this.updateDatetime = new Date();
    }
}
