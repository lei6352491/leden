package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenBbs;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.BbsVo;

import java.util.List;
import java.util.Map;

/**
 * 公告业务
 */
public interface LedenBbsService {

    /**
     * 分页查询公告业务
     * @param conditions
     * @return
     */
    PageData<LedenBbs> searchBbsByConditions(Map<String, Object> conditions);

    /**
     * 修改公告业务
     * @param bbsVo
     * @return
     */
    Boolean modifyLendenBbs(BbsVo bbsVo);

    /**
     * 新增公告
     * @param bbsVo
     * @return
     */
    Boolean addLendenBbs(BbsVo bbsVo);

    /**
     * 删除公告
     * @param pkId
     * @return
     */
    Boolean removeLendenBbs(String pkId);

    /**
     * 查询最新的一条公告
     * */
    Response selectBbsByDate();

}
