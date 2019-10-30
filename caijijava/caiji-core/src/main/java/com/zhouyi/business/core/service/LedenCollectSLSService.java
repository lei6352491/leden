package com.zhouyi.business.core.service;

import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.vo.LedenCollectSLSVo;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectSLSService
 * @Description: 体貌/特征/足长。。 接口
 * @date 2019/7/3 16:09
 * @Version 1.0
 **/
public interface LedenCollectSLSService {

    /**
     * 录入人员的体貌/特征/足长等信息
     * @param path
     * @return
     */
    boolean insertPersonInfo(String path) throws AuthenticationException;
}
