package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LedenUploadPacket implements Serializable {
    private String pkId;

    private String uploadLogId;

    private String nodeSign;

    private String fileServer;

    private String fileLocation;

    private String fileSuffix;

    private BigDecimal fileSize;

    private String fileMd5;

    private String resolveStatus;

    private String resolveResultInfo;

    private Date resolveDatetime;

    //
    private Date uploadDate;

    private String personCode;

    private static final long serialVersionUID = 1L;

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getUploadLogId() {
        return uploadLogId;
    }

    public void setUploadLogId(String uploadLogId) {
        this.uploadLogId = uploadLogId;
    }

    public String getNodeSign() {
        return nodeSign;
    }

    public void setNodeSign(String nodeSign) {
        this.nodeSign = nodeSign;
    }

    public String getFileServer() {
        return fileServer;
    }

    public void setFileServer(String fileServer) {
        this.fileServer = fileServer;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getResolveStatus() {
        return resolveStatus;
    }

    public void setResolveStatus(String resolveStatus) {
        this.resolveStatus = resolveStatus;
    }

    public String getResolveResultInfo() {
        return resolveResultInfo;
    }

    public void setResolveResultInfo(String resolveResultInfo) {
        this.resolveResultInfo = resolveResultInfo;
    }

    public Date getResolveDatetime() {
        return resolveDatetime;
    }

    public void setResolveDatetime(Date resolveDatetime) {
        this.resolveDatetime = resolveDatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", uploadLogId=").append(uploadLogId);
        sb.append(", nodeSign=").append(nodeSign);
        sb.append(", fileServer=").append(fileServer);
        sb.append(", fileLocation=").append(fileLocation);
        sb.append(", fileSuffix=").append(fileSuffix);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", fileMd5=").append(fileMd5);
        sb.append(", resolveStatus=").append(resolveStatus);
        sb.append(", resolveResultInfo=").append(resolveResultInfo);
        sb.append(", resolveDatetime=").append(resolveDatetime);
        sb.append("]");
        return sb.toString();
    }
}