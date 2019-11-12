package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.LedenCollectSgtzzcMapper;
import com.zhouyi.business.core.model.LedenCollectSgtzzc;
import com.zhouyi.business.core.vo.LedenCollectSgtzzcVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LedenCollectSgtzzcServiceImpl extends
        BaseServiceImpl<LedenCollectSgtzzc, LedenCollectSgtzzcVo>
        implements LedenCollectSgtzzcService{

    @Autowired
    private LedenCollectSgtzzcMapper ledenCollectSgtzzcMapper;

    @Override
    public LedenCollectSgtzzc getSgtzzcByPersonCode(String personCode) {
        return ledenCollectSgtzzcMapper.getSgtzzcByConditions(new HashMap<String,Object>(1){{put("personCode",personCode);}});
    }
}
