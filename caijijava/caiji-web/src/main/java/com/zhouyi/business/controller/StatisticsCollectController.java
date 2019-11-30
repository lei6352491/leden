package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.WeekCollectData;
import com.zhouyi.business.core.model.YearCollectData;
import com.zhouyi.business.core.model.months.MonthStatistical;
import com.zhouyi.business.core.service.LedenCollectPersonService;
import com.zhouyi.business.core.service.StatisticsCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @Autowired
    private LedenCollectPersonService ledenCollectPersonService;

    /**
     * 查看该周每天采集量
     * */
    @RequestMapping(value = "/week/{unitCode}")
    public WeekCollectData selectWeekCollectData(@PathVariable String unitCode){
        return statisticsCollectService.selectWeekCollectData(unitCode);
    }

    /**
     * 查看该年每月采集量
     * */
    @RequestMapping(value = "/year/{unitCode}")
    public YearCollectData selectYearCollectData(@PathVariable String unitCode){
        return statisticsCollectService.selectYearCollectData(unitCode);
    }


    @RequestMapping(value = "/month/{unitCode}")
    public List<MonthStatistical> selectMonthCollectData(@PathVariable String unitCode){
       return  ledenCollectPersonService.getMonthStatistical(unitCode);
    }





}
