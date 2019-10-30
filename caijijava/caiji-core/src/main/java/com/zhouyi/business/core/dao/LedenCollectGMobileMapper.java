package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectGMobile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenCollectGMobileMapper extends BaseMapper<LedenCollectGMobile,String>{

    /**
     * 根据人员编号删除手机信息
     * @param personId
     * @return
     */
    int deleteGMobileByPersonId(String personId);
}