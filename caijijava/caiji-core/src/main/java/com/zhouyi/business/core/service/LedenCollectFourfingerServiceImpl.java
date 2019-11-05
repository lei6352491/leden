package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectFourfingerMapper;
import com.zhouyi.business.core.model.LedenCollectFourfinger;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectFourfingerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LedenCollectFourfingerServiceImpl
        extends BaseServiceImpl<LedenCollectFourfinger, LedenCollectFourfingerVo>
        implements LedenCollectFourfingerService {

    @Autowired
    private LedenCollectFourfingerMapper ledenCollectFourfingerMapper;


    @Override
    public Response findFourFingerByPersonCode(String personCode) {
        List<LedenCollectFourfinger> ledenCollectFourfingers = ledenCollectFourfingerMapper.selectFourFingerByPersonCode(personCode);
        if (ledenCollectFourfingers.size() <= 0)
            return ResponseUtil.returnError(ReturnCode.ERROR_05);
        ledenCollectFourfingers.stream().forEach(s -> {
            if (s.getSlzTxsj() != null) {
                s.setCzwzp(new String(s.getSlzTxsj()));
                s.setSlzTxsj(null);
            }
        });
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenCollectFourfingers);
    }
}
