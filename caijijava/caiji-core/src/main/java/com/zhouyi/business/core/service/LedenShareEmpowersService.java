package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenShareApp;
import com.zhouyi.business.core.model.LedenShareEmpowers;
import com.zhouyi.business.core.vo.EmpowerNodeVo;
import com.zhouyi.business.core.vo.LedenShareEmpowersVo;

import java.util.List;
import java.util.Map;

/**
 * @author 杜承旭
 * @ClassNmae: LedenShareEmpowersService
 * @Description: TODO
 * @date 2019/7/17 13:56
 * @Version 1.0
 **/
public interface LedenShareEmpowersService extends BaseService<LedenShareEmpowers, LedenShareEmpowersVo>{

    /**
     * 根据条件获取共享数据信息
     * @param conditions
     * @return
     */
    List<LedenShareApp> listShareEmpowersByConditions(Map<String,Object> conditions);


    /**
     * 修改app权限信息
      * @param ledenShareEmpowers
     * @return
     */
    boolean changAppEmpowers(List<LedenShareEmpowers> ledenShareEmpowers);

}
