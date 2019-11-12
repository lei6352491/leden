package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.SysDictList;
import com.zhouyi.business.core.vo.SysDictListVo;

import java.util.List;


public interface SysDictListService {

    List<SysDictList> findSysDictListById(String id);

    List<SysDictList> findSysDiceListByModel(SysDictListVo sysDictListVo);

    void saveSysDictList(SysDictList sysDictList);

    void updateSysDictList(SysDictList sysDictList);

    void deleteSysDictList(String id);

    List<SysDictList> testList(SysDictListVo sysDictListVo);

    int findTotal(SysDictListVo sysDictListVo);

    /**
     * 根据code查询name
     * @param code
     * @return
     */
    String getDictNameByCode(String code);
}
