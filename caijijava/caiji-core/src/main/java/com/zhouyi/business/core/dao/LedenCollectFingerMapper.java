package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectFinger;
import com.zhouyi.business.core.vo.LedenCollectFingerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LedenCollectFingerMapper extends
        BuffBaseMapper<LedenCollectFinger, LedenCollectFingerVo>{


    /**
     * 插入多条指纹数据
     * @param list
     * @return
     */
    int insertFingers(List<? extends LedenCollectFinger> list);

    /**
     * 根据人员编号查询指纹信息
     * */
    List<LedenCollectFinger> selectFingerByPersonCode(String id);

    /**
     * 根据人员编号和压缩算法
     * @param personCode
     * @param compressionAlgorithm
     */
    void deleteByPersonCode (@Param("personCode") String personCode,@Param("compressionAlgorithem") String compressionAlgorithm);
}