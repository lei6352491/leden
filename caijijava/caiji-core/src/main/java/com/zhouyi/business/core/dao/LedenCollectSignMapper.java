package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectSign;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardSign;
import com.zhouyi.business.core.vo.LedenCollectSignVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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


    /**
     * 根据条件查询特殊特征
     * @param conditions
     * @return
     */
    List<StandardSign> listSignsByConditions(Map<String,Object> conditions);
}