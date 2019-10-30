package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenEquipmentPlugIn;
import com.zhouyi.business.core.vo.LedenEquipmentPlugInVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenEquipmentPlugInMapper
        extends BuffBaseMapper<LedenEquipmentPlugIn, LedenEquipmentPlugInVo>{

    List<LedenEquipmentPlugIn> selectDataByNodeCode(String nodeCode);

}