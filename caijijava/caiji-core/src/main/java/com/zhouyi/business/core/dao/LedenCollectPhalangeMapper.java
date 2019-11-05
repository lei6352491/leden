package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPhalange;
import com.zhouyi.business.core.vo.LedenCollectPhalangeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LedenCollectPhalangeMapper extends
        BuffBaseMapper<LedenCollectPhalange, LedenCollectPhalangeVo>{


    /**
     * 根据人员编号和压缩算法删除指节纹
     * @param personId
     * @param compressionAlgorithm
     * @return
     */
    int deletePhalangeByPersonId(@Param("personCode") String personId,@Param("compressionAlgorithm") String compressionAlgorithm);
}