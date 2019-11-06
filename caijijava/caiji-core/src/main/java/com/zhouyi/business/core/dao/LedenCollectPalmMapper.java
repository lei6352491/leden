package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPalm;
import com.zhouyi.business.core.vo.LedenCollectPalmVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LedenCollectPalmMapper extends
        BuffBaseMapper<LedenCollectPalm, LedenCollectPalmVo>{

    List<LedenCollectPalm> selectPalmFingerByPersonCode(String id);

    /**
     * 根据指纹压缩算法和人员编号删除掌纹数据
     * @param personId
     * @param compressionAlgorithm
     * @return
     */
    int deletePalmByPersonId(@Param("personCode") String personId, @Param("compressionAlgorithm") String compressionAlgorithm);

}