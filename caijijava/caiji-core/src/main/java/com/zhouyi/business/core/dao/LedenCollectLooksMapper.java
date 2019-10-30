package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectLooks;
import com.zhouyi.business.core.vo.LedenCollectLooksVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenCollectLooksMapper extends
        BuffBaseMapper<LedenCollectLooks, LedenCollectLooksVo>{
    /**
     * 根据人员编号删除外貌信息
     * @param personId
     * @return
     */
    int deleteLooksById(String personId);

}