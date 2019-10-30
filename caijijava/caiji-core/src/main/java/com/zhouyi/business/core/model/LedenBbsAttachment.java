package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "附件模型对象")
public class LedenBbsAttachment implements Serializable {
    @ApiModelProperty(value = "主键id")
    private String pkId;

    @ApiModelProperty(value = "公告主键标识")
    private String bbsId;

    @ApiModelProperty(value = "附件名称")
    private String filename;

    @ApiModelProperty(value = "文件路径")
    private String fileurl;

    @ApiModelProperty(value = "文件大小")
    private String filesize;

    @ApiModelProperty(value = "文件类型")
    private String filetype;

    @ApiModelProperty(value = "删除标识")
    private String deletag;

    private static final long serialVersionUID = 1L;

    public LedenBbsAttachment(String bbsId,
                              String filename,
                              String fileurl,
                              String filesize,
                              String filetype,
                              String deletag) {
        this.bbsId = bbsId;
        this.filename = filename;
        this.fileurl = fileurl;
        this.filesize = filesize;
        this.filetype = filetype;
        this.deletag = deletag;
    }


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getBbsId() {
        return bbsId;
    }

    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getDeletag() {
        return deletag;
    }

    public void setDeletag(String deletag) {
        this.deletag = deletag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", bbsId=").append(bbsId);
        sb.append(", filename=").append(filename);
        sb.append(", fileurl=").append(fileurl);
        sb.append(", filesize=").append(filesize);
        sb.append(", filetype=").append(filetype);
        sb.append(", deletag=").append(deletag);
        sb.append("]");
        return sb.toString();
    }

    public LedenBbsAttachment() {
    }
}