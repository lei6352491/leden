package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.SysBaseConf;
import com.zhouyi.business.core.vo.SysBaseConfVo;

import java.util.List;

public interface SysBaseConfService {

    SysBaseConf findSysBaseConfById(String id);

    List<SysBaseConf> findSysBaseConfByModel(SysBaseConfVo sysBaseConfVo);

    void saveSysBaseConf(SysBaseConf sysBaseConf);

    void updateSysBaseConf(SysBaseConf sysBaseConf);

    void deleteSysBaseConfById(String id);

    int findTotal(SysBaseConfVo sysBaseConfVo);
}
