package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenShareApp;
import com.zhouyi.business.core.model.ShareAppDetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenShareAppMapper extends BaseMapper<LedenShareApp,String>{
    /**
     * 根据id获取注册信息
     * @param id
     * @return
     */
    ShareAppDetailDto getLedenShareAppById(String id);
}