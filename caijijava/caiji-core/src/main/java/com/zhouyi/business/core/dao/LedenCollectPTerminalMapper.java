package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPTerminal;
import com.zhouyi.business.core.vo.LedenCollectPTerminalVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectPTerminalMapper extends BuffBaseMapper<LedenCollectPTerminal, LedenCollectPTerminalVo>{

    List<LedenCollectPTerminal> selectMiniDataByPersonCode(String id);


    /**
     * 根据人员编号删除终端信息
     * @param personId
     * @return
     */
    int deleteTerminalByPersonId(String personId);



}