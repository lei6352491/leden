package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.LedenCollectPersonMapper;
import com.zhouyi.business.core.model.WeekCollectData;
import com.zhouyi.business.core.model.YearCollectData;
import com.zhouyi.business.core.utils.UnitUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 杜承旭
 * @ClassNmae: StatisticsCollectServiceImpl
 * @Description: TODO
 * @date 2019/11/21 15:35
 * @Version 1.0
 **/
@Service
public class StatisticsCollectServiceImpl implements StatisticsCollectService{

    @Autowired
    private LedenCollectPersonMapper ledenCollectPersonMapper;

    /**
     * 获取该星期的每天的采集量
     * */
    @Override
    public WeekCollectData selectWeekCollectData(String unitCode) {
        //获取模糊匹配的单位编码
        String newUnitCode= UnitUtil.getUnitHead(unitCode,2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        //校正区域星期中的天数
        weekDay = weekDay - 1;
        if(weekDay == 0)
            weekDay = 7;
        //获取该星期中每天凌晨的时间对象和现在时间对象
        List<Date> dateList = weekOfDay(weekDay);
        //查询本周每天的采集人数
        return selectDailyCollectNumber(dateList,newUnitCode);
    }

    /**
     * 获取该年的每月采集量
     * */
    @Override
    public YearCollectData selectYearCollectData(String unitCode) {
        String newUnitCode=UnitUtil.getUnitHead(unitCode,2);
        //获取当前月份
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int month = calendar.get(Calendar.MONTH);
        //获取当前年份
        int year = calendar.get(Calendar.YEAR);
        //校正区域月份
        month = month + 1;
        //获取该年中每月1号凌晨的时间对象和现在时间对象
        List<Date> list = yearOfMonth(year, month);
        //查询今年每月的采集量
        return selectMonthlyCollectNumber(list,newUnitCode);
    }

    /**
     * 查询今年每月的采集量
     * */
    private YearCollectData selectMonthlyCollectNumber(List<Date> list,String unitCode){
        ArrayList<Integer> collectNumberList = new ArrayList<>();
        for (int i = 1;i < list.size();i++){
            Date startDate = list.get(i - 1);
            Date endDate = list.get(i);
            int collectNumber = ledenCollectPersonMapper.selectCollectNumber(startDate, endDate,unitCode);
            collectNumberList.add(collectNumber);
        }
        //填充数据集合
        int size = collectNumberList.size();
        for (int i = 12;i > size;i--){
            collectNumberList.add(null);
        }
        //封装数据
        return new YearCollectData
                (collectNumberList.get(0),collectNumberList.get(1),collectNumberList.get(2),collectNumberList.get(3),
                        collectNumberList.get(4),collectNumberList.get(5),collectNumberList.get(6),
                        collectNumberList.get(7),collectNumberList.get(8),collectNumberList.get(9),
                        collectNumberList.get(10),collectNumberList.get(11));
    }

    /**
     * 获取该年中每月1号凌晨的时间对象和现在时间对象
     * */
    private List<Date> yearOfMonth(Integer year,Integer month){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //获取该年年初时间对象
            Date earlyYearDate = simpleDateFormat.parse(year + "-01-01");
            //创建时间容器
            List<Date> list = new ArrayList<>();
            //把每月初的时间对象添加到容器中
            list.add(earlyYearDate);
            for (int i = 1;i < month;i++){
                Date date = DateUtils.addMonths(earlyYearDate, i);
                list.add(date);
            }
            list.add(new Date());
            return list;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询本周每天的采集人数
     * */
    private WeekCollectData selectDailyCollectNumber(List<Date> list,String unitCode){
        ArrayList<Integer> collectNumberList = new ArrayList<>();
        for (int i = 1;i < list.size();i++){
            //取出每天的时间间隔对象
            Date beforeDawnDate = list.get(i - 1);
            Date theNextMorning  = list.get(i);
            //获取这天的采集人数
            int collectNumber = ledenCollectPersonMapper.selectCollectNumber(beforeDawnDate, theNextMorning,unitCode);
            collectNumberList.add(collectNumber);
        }
        //填充数据集合
        int size = collectNumberList.size();
        for (int i = 7;i > size;i--){
            collectNumberList.add(null);
        }
        //封装到返回对象中
        WeekCollectData weekCollectData = new WeekCollectData();
        weekCollectData.setMonday(collectNumberList.get(0));
        weekCollectData.setTuesday(collectNumberList.get(1));
        weekCollectData.setWednesday(collectNumberList.get(2));
        weekCollectData.setThursday(collectNumberList.get(3));
        weekCollectData.setFriday(collectNumberList.get(4));
        weekCollectData.setSaturday(collectNumberList.get(5));
        weekCollectData.setSunday(collectNumberList.get(6));

        return weekCollectData;
    }

    /**
     * 获取该星期中每天凌晨的时间对象和现在时间对象
     * */
    private List<Date> weekOfDay(Integer weekDay){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();
        String dateString = simpleDateFormat.format(newDate);
        try {
            //获取今天的凌晨时间
            Date todayDate = simpleDateFormat.parse(dateString);
            //创建时间对象容器
            List<Date> list = new ArrayList<>();
            //获取本周的星期一凌晨时间
            Date mondayMorningDate  = DateUtils.addDays(todayDate, -(weekDay - 1));
            list.add(mondayMorningDate);
            //本周每天的凌晨时间
            for (int i = 1;i < weekDay;i++){
                Date theSecondDay = DateUtils.addDays(mondayMorningDate, i);
                list.add(theSecondDay);
            }
            list.add(newDate);
            return list;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
