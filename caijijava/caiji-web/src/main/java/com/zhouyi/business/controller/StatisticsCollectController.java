package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.WeekCollectData;
import com.zhouyi.business.core.model.YearCollectData;
import com.zhouyi.business.core.service.StatisticsCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杜承旭
 * @ClassNmae: StatisticsCollectController
 * @Description: TODO
 * @date 2019/11/21 15:03
 * @Version 1.0
 **/

@RestController
@RequestMapping("/statistics")
public class StatisticsCollectController {

    @Autowired
    private StatisticsCollectService statisticsCollectService;

    /**
     * 查看该周每天采集量
     * */
    @RequestMapping(value = "/week")
    public WeekCollectData selectWeekCollectData(){
        return statisticsCollectService.selectWeekCollectData();
    }

    /**
     * 查看该年每月采集量
     * */
    @RequestMapping(value = "/year")
    public YearCollectData selectYearCollectData(){
        return statisticsCollectService.selectYearCollectData();
    }
}
