package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectFullpalm;
import com.zhouyi.business.core.model.LedenCollectPalm;
import com.zhouyi.business.core.vo.LedenCollectFullpalmVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenCollectFullpalmMapper extends
        BuffBaseMapper<LedenCollectFullpalm, LedenCollectFullpalmVo>{


    /**
     * 根据压缩算法和人员编号删除
     * @param personId
     * @param compressionAlgorithm
     * @return
     */
    int deleteFullPalmByPersonId(@Param("personCode") String personId,@Param("compressionAlgorithm") String compressionAlgorithm);


    /**
     * 根据条件筛选掌纹信息
     * @param conditions
     * @return
     */
    List<LedenCollectPalm> listPalmByConditions(Map<String,Object> conditions);
}