package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectGVehicle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenCollectGVehicleMapper extends BaseMapper<LedenCollectGVehicle,String>{

    /**
     * 根据人员编号删除交通工具信息
     * @param personId
     * @return
     */
    int deleteVehicleByPersonId(String personId);
}