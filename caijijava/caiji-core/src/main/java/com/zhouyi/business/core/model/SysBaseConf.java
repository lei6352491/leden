package com.zhouyi.business.core.model;

import java.io.Serializable;

public class SysBaseConf implements Serializable {
    private String pkId;

    private String systemName;

    private String systemVersion;

    private String logoLocation;

    private String personNoPr;

    private String copyrightBelongs;

    private static final long serialVersionUID = 1L;

    public String getCopyrightBelongs() {
        return copyrightBelongs;
    }

    public void setCopyrightBelongs(String copyrightBelongs) {
        this.copyrightBelongs = copyrightBelongs;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getLogoLocation() {
        return logoLocation;
    }

    public void setLogoLocation(String logoLocation) {
        this.logoLocation = logoLocation;
    }

    public String getPersonNoPr() {
        return personNoPr;
    }

    public void setPersonNoPr(String personNoPr) {
        this.personNoPr = personNoPr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", systemName=").append(systemName);
        sb.append(", systemVersion=").append(systemVersion);
        sb.append(", logoLocation=").append(logoLocation);
        sb.append(", personNoPr=").append(personNoPr);
        sb.append("]");
        return sb.toString();
    }
}