package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectPTerminalMapper;
import com.zhouyi.business.core.model.LedenCollectPTerminal;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectPTerminalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LedenCollectPTerminalServiceImpl
        extends BaseServiceImpl<LedenCollectPTerminal, LedenCollectPTerminalVo>
        implements LedenCollectPTerminalService{

    @Autowired
    private LedenCollectPTerminalMapper ledenCollectPTerminalMapper;

    @Override
    public Response selectMiniDataByPersonCode(String id) {
        List<LedenCollectPTerminal> list = ledenCollectPTerminalMapper.selectMiniDataByPersonCode(id);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }

    @Override
    public Response selectDataByPersonCode(String id) {
        LedenCollectPTerminal ledenCollectPTerminal = ledenCollectPTerminalMapper.selectByPrimaryKey(id);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenCollectPTerminal);
    }
}
