package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectGCard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenCollectGCardMapper extends BaseMapper<LedenCollectGCard,String>{

    /**
     * 根据物品编号删除卡信息
     * @param wpbh
     * @return
     */
    int deleteCardByWpbh(String wpbh);

}