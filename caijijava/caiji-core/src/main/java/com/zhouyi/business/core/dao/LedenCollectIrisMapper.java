package com.zhouyi.business.core.dao;


import com.zhouyi.business.core.model.LedenCollectIris;
import com.zhouyi.business.core.model.xinzhen.IrisCommons;
import com.zhouyi.business.core.model.xinzhen.IrisComparsion;
import com.zhouyi.business.core.model.xinzhen.IrisReceive;
import com.zhouyi.business.core.vo.LedenCollectIrisVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectIrisMapper
        extends BuffBaseMapper<LedenCollectIris, LedenCollectIrisVo> {

    List<LedenCollectIris> selectDataByPersonCode(String id);

    int deleteDataByPersonCode(String id);


    IrisReceive irisCollectSearch(String rybh);

    IrisCommons selectCommons(String rybh);

    IrisComparsion irisComparsionSearch(String rybh);


}