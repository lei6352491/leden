package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectSign;
import com.zhouyi.business.core.vo.LedenCollectSignVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectSignMapper extends
        BuffBaseMapper<LedenCollectSign, LedenCollectSignVo>{


    /**
     * 插入多个特征信息
     * @param list
     * @return
     */
    int insertSigns(List<LedenCollectSign> list);


    /**
     * 根据人员编号删除特征点信息
     * @param personId
     * @return
     */
    int deleteSignByPersonId(String personId);
}