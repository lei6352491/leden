package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectSgtzzc;
import com.zhouyi.business.core.vo.LedenCollectSgtzzcVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LedenCollectSgtzzcMapper extends
        BuffBaseMapper<LedenCollectSgtzzc, LedenCollectSgtzzcVo>{


    /**
     * 根据人员编号删除足记信息
     * @param personId
     * @return
     */
    int deleteSgtzzcByPersonId(String personId);


    /**
     * 根据条件查询身高体重足长等
     * @param conditions
     * @return
     */
    LedenCollectSgtzzc getSgtzzcByConditions(Map<String,Object> conditions);


}