package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.WeekCollectData;
import com.zhouyi.business.core.model.YearCollectData;
import com.zhouyi.business.core.model.months.MonthStatistical;
import com.zhouyi.business.core.service.LedenCollectPersonService;
import com.zhouyi.business.core.service.StatisticsCollectService;
import com.zhouyi.business.core.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    public MonthEntity selectMonthCollectData(@PathVariable String unitCode){
        //查询今年有多少天
        int count= DateUtil.getCurrentMonthDay();
        List<MonthStatistical> monthStatistical = ledenCollectPersonService.getMonthStatistical(unitCode);
        MonthEntity monthEntity=new MonthEntity(monthStatistical,count);
       return  monthEntity;
    }


    /**
     * @Author: first
     * @Date: 上午11:52 2019/12/3
     * @Description: 封装月数据实体
    **/
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class MonthEntity{
       private List<MonthStatistical> list;
       private int count;
    }





}
