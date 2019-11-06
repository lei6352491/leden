package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectFourfinger;
import com.zhouyi.business.core.vo.LedenCollectFourfingerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LedenCollectFourfingerMapper extends
        BuffBaseMapper<LedenCollectFourfinger, LedenCollectFourfingerVo>{

        List<LedenCollectFourfinger> selectFourFingerByPersonCode(String id);

        int deleteFourFingerByPersonId(@Param("personCode") String personId, @Param("compressionAlgorithm") String compressionAlgorithm);
}