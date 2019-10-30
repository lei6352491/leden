package com.zhouyi.business.dto;

import java.io.Serializable;

/**
 * @author 李秸康
 * @ClassNmae: LedenBbsAttachmentDto
 * @Description: TODO
 * @date 2019/6/25 14:06
 * @Version 1.0
 **/
public class LedenBbsAttachmentDto extends BaseDto implements Serializable {

    private String pkId;

    private String bbsId;

    private String filename;

    private String fileurl;

    private String filesize;

    private String filetype;

    private String deletag;


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
}
