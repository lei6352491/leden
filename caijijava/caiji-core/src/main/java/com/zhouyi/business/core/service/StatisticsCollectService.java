package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.WeekCollectData;
import com.zhouyi.business.core.model.YearCollectData;

/**
 * @author 杜承旭
 * @ClassNmae: StatisticsCollectService
 * @Description: TODO
 * @date 2019/11/21 15:34
 * @Version 1.0
 **/
public interface StatisticsCollectService {

    WeekCollectData selectWeekCollectData();

    YearCollectData selectYearCollectData();

}
