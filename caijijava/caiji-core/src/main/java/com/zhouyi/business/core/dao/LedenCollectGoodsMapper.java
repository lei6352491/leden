package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectGoods;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenCollectGoodsMapper extends BaseMapper<LedenCollectGoods,String>{


    /**
     * 插入物品信息列表
     * @param list
     * @return
     */
    int insertGoods(List<LedenCollectGoods> list);


    /**
     * 根据人员编号删除物品信息
     * @param personId
     * @return
     */
    int deleteGoodsByPersonId(String personId);


    /**
     * 根据条件筛选物品信息
     * @param conditions
     * @return
     */
    List<StandardGoods> listGoodsByConditions(Map<String,Object> conditions);
}