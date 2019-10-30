package com.zhouyi.business.core.dao;


import com.zhouyi.business.core.model.LedenCollectBankcard;
import com.zhouyi.business.core.vo.LedenCollectBankcardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectBankcardMapper extends BuffBaseMapper<LedenCollectBankcard, LedenCollectBankcardVo> {

    List<LedenCollectBankcard> selectBankcardByPersonCode(String id);
}