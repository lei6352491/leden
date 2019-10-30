package com.zhouyi.business.core.vo.headvo;

import com.zhouyi.business.core.vo.FPTHeaderVo;

import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: PackageHeadVo
 * @Description: 解析指纹头部对象
 * @date 2019/7/6 10:06
 * @Version 1.0
 **/
public class PackageHeadVo extends FPTHeaderVo {

    private String version;
    private Date createtime;
    private String originsystem;
    private String fsdwGajgjgdm;
    private String fsdwGajgmc;
    private String fsdwXtlx;
    private String fsrXm;
    private String fsrGmsfhm;
    private String fsrLxdh;
    private String compressType;
    private String nfiqScore;


    public String getCompressType() {
        return compressType;
    }

    public void setCompressType(String compressType) {
        this.compressType = compressType;
    }

    public String getNfiqScore() {
        return nfiqScore;
    }

    public void setNfiqScore(String nfiqScore) {
        this.nfiqScore = nfiqScore;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOriginsystem() {
        return originsystem;
    }

    public void setOriginsystem(String originsystem) {
        this.originsystem = originsystem;
    }

    public String getFsdwGajgjgdm() {
        return fsdwGajgjgdm;
    }

    public void setFsdwGajgjgdm(String fsdwGajgjgdm) {
        this.fsdwGajgjgdm = fsdwGajgjgdm;
    }

    public String getFsdwGajgmc() {
        return fsdwGajgmc;
    }

    public void setFsdwGajgmc(String fsdwGajgmc) {
        this.fsdwGajgmc = fsdwGajgmc;
    }

    public String getFsdwXtlx() {
        return fsdwXtlx;
    }

    public void setFsdwXtlx(String fsdwXtlx) {
        this.fsdwXtlx = fsdwXtlx;
    }

    public String getFsrXm() {
        return fsrXm;
    }

    public void setFsrXm(String fsrXm) {
        this.fsrXm = fsrXm;
    }

    public String getFsrGmsfhm() {
        return fsrGmsfhm;
    }

    public void setFsrGmsfhm(String fsrGmsfhm) {
        this.fsrGmsfhm = fsrGmsfhm;
    }

    public String getFsrLxdh() {
        return fsrLxdh;
    }

    public void setFsrLxdh(String fsrLxdh) {
        this.fsrLxdh = fsrLxdh;
    }
}
