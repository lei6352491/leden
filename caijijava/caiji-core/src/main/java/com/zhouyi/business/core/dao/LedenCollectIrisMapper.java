package com.zhouyi.business.core.dao;


import com.zhouyi.business.core.model.LedenCollectIris;
import com.zhouyi.business.core.model.xinzhen.IrisCommons;
import com.zhouyi.business.core.model.xinzhen.IrisComparsion;
import com.zhouyi.business.core.model.xinzhen.IrisReceive;
import com.zhouyi.business.core.vo.LedenCollectIrisVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LedenCollectIrisMapper
        extends BuffBaseMapper<LedenCollectIris, LedenCollectIrisVo> {

    List<LedenCollectIris> selectDataByPersonCode(String id);

    int deleteDataByPersonCode(String id);


    IrisReceive irisCollectSearch(@Param(value = "rybh") String rybh);

    IrisCommons selectCommons(@Param(value="rybh") String rybh);

    IrisComparsion irisComparsionSearch(@Param(value = "rybh") String rybh);


}