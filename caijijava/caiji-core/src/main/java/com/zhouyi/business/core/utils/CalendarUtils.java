package com.zhouyi.business.core.utils;

import org.jsoup.helper.DataUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: CalendarUtils
 * @Description: TODO 时间工具类
 * @date 2019/9/4 9:54
 * @Version 1.0
 **/
public class CalendarUtils {
    /**
     * 默认日期格式
     */
    public static String DEFAULT_FORMAT = "yyyy-MM-dd";
    /**

    /**
     * 格式化日期
     * @param date 日期对象
     * @return String 日期字符串
     */
    public static String formatDate(Date date){
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }
    /**
     * 获取当年的第一天
     * @param year
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }
    /**
     * 获取当年的最后一天
     * @param year
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }


    /**
     * 返回两个季度的Calendar
     * @return
     */
    public static Calendar[] quarterDate(Integer quarter){
        Calendar calendars[] =new Calendar[2];
       switch (quarter){
           case 1:
               calendars[0]=getFisrtDayOfMonth(getSysYear(),1);
               calendars[1]=getFisrtDayOfMonth(getSysYear(),3);
               break;
           case 2:
               calendars[0]=getFisrtDayOfMonth(getSysYear(),4);
               calendars[1]=getFisrtDayOfMonth(getSysYear(),6);
               break;
           case 3:
               calendars[0]=getFisrtDayOfMonth(getSysYear(),7);
               calendars[1]=getFisrtDayOfMonth(getSysYear(),9);
               break;
           case 4:
               calendars[0]=getFisrtDayOfMonth(getSysYear(),10);
               calendars[1]=getFisrtDayOfMonth(getSysYear(),12);
               break;
       }
       return  calendars;

    }


    public static Calendar getLastDayOfMonth(int year,int month)
    {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal;
    }


    public static Calendar getFisrtDayOfMonth(int year,int month)
    {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal;
    }


    public static Date[] weekDate(Integer year,Integer week){
        Date[] dates=new Date[2];
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 1);
        int dayOfWeek = 7- calendar.get(Calendar.DAY_OF_WEEK) + 1;//算出第一周还剩几天 +1是因为1号是1天
        week = week -2;//周数减去第一周再减去要得到的周
        calendar.add(Calendar.DAY_OF_YEAR, week*7+dayOfWeek);
        dates[0]=calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        dates[1]=calendar.getTime();
        return dates;
    }

    public static Integer getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return Integer.parseInt(year);
    }
    public static Calendar generateCalendar(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
       /**
     * 将日期转为Calendar
     * @param date
     * @return
     */
    public static  Calendar[] transferToCalendar(Date[] date){
        Calendar[] calendars=new Calendar[date.length];
        for (int i=0;i<date.length;i++){
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date[i]);
            calendars[i]=calendar;
        }
        return calendars;

    }


    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date s = sdf.parse("2019-05");
        Calendar ca = Calendar.getInstance();
        ca.setTime(s);
        ca.setFirstDayOfWeek(Calendar.MONDAY);
        System.out.println(ca.getActualMaximum(Calendar.WEEK_OF_MONTH));
    }
}
