package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectPTerminal;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectPTerminalVo;

public interface LedenCollectPTerminalService extends BaseService<LedenCollectPTerminal, LedenCollectPTerminalVo> {
    Response selectMiniDataByPersonCode(String id);

    Response selectDataByPersonCode(String id);
}
