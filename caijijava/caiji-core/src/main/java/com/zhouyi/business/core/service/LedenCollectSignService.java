package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectSign;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardSign;
import com.zhouyi.business.core.vo.LedenCollectSignVo;

import java.util.List;

public interface LedenCollectSignService extends BaseService<LedenCollectSign, LedenCollectSignVo> {


    /**
     * 根据人员编号查询特征点
     * @param personCode
     * @return
     */
    List<StandardSign> listSignsByPersonCode(String personCode);
}
