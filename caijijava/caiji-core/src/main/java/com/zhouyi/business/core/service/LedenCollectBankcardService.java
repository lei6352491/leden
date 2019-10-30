package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectBankcard;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectBankcardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface LedenCollectBankcardService
        extends BaseService<LedenCollectBankcard, LedenCollectBankcardVo> {

    Response<Object> saveMapToRepository(List list, String userUnitCode);

    Response<List<LedenCollectBankcard>> selectBankcardByPersonCode(String id);

}
