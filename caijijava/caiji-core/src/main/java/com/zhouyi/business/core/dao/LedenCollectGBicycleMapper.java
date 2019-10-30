package com.zhouyi.business.core.dao;


import com.zhouyi.business.core.model.LedenCollectGBicycle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenCollectGBicycleMapper extends BaseMapper<LedenCollectGBicycle,String> {
    /**
     * 通过物品编号删除自行车
     * @param wpbh
     * @return
     */
    int deleteBicycleByWpbh(String wpbh);
}