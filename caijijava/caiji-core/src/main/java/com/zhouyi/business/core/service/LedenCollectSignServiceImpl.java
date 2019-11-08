package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.LedenCollectSignMapper;
import com.zhouyi.business.core.model.LedenCollectSign;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardSign;
import com.zhouyi.business.core.vo.LedenCollectSignVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LedenCollectSignServiceImpl extends
        BaseServiceImpl<LedenCollectSign, LedenCollectSignVo> implements LedenCollectSignService {

    @Autowired
    private LedenCollectSignMapper ledenCollectSignMapper;

    @Override
    public List<StandardSign> listSignsByPersonCode(String personCode) {
        return ledenCollectSignMapper.listSignsByConditions(new HashMap<String,Object>(1){{put("personCode",personCode);}});
    }
}
