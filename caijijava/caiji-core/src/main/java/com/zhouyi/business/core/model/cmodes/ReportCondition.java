package com.zhouyi.business.core.model.cmodes;

import com.mysql.jdbc.StringUtils;
import com.zhouyi.business.core.utils.CalendarUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jsoup.helper.StringUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 李秸康
 * @ClassNmae: ReportCondition
 * @Description: TODO 报表入参条件对象
 * @date 2019/9/2 9:29
 * @Version 1.0
 **/
@ApiModel(value = "报表条件入参对象")
@Data
public class ReportCondition {
    @ApiModelProperty(value = "部门编码")
    private String unitCode;
    @ApiModelProperty(value = "年报年份")
    private String year;
    @ApiModelProperty(value = "季度编号(1-4)")
    private String quarter;
    @ApiModelProperty(value = "周数")
    private String week;
    @ApiModelProperty(value = "月报")
    private String month;
    @ApiModelProperty(value = "自定义时间")
    private Custom custom;


    @ApiModel(value = "自定义")
    @Data
    public class Custom {
        private Date startTime;
        private Date endTime;
    }

    @ApiModelProperty(hidden = true)
    private Date startDate;
    @ApiModelProperty(hidden = true)
    private Date endDate;
    @ApiModelProperty(hidden = true)
    private Calendar first;
    @ApiModelProperty(hidden = true)
    private Calendar second;
    @ApiModelProperty(hidden = true)
    private boolean upper = false;

    public void setYear(String year) {
        this.year = year;
        if (!StringUtils.isNullOrEmpty(year)) {
            setStartDate(CalendarUtils.getYearFirst(Integer.parseInt(year)));
            setEndDate(CalendarUtils.getYearLast(Integer.parseInt(year)));
            setFirst(CalendarUtils.generateCalendar(getStartDate()));
            setSecond(CalendarUtils.generateCalendar(getEndDate()));
        }
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
        if (!StringUtils.isNullOrEmpty(quarter)) {
            Calendar[] calendars = CalendarUtils.quarterDate(Integer.parseInt(quarter));
            setValues(calendars);
        }

    }

    public void setWeek(String week) {
        this.week = week;
        if (!StringUtils.isNullOrEmpty(week)) {
            Date[] dates = CalendarUtils.weekDate(CalendarUtils.getSysYear(), Integer.parseInt(week));
            Calendar[] weekCalendar = CalendarUtils.transferToCalendar(dates);
            setValues(weekCalendar);
        }

    }

    public void setMonth(String month) {
        this.month = month;
        if (!StringUtils.isNullOrEmpty(month)) {
            int newYear = CalendarUtils.getSysYear();
            int newMonth = Integer.parseInt(month);
            Calendar fisrtDayOfMonth = CalendarUtils.getFisrtDayOfMonth(newYear, newMonth);
            Calendar lastDayOfMonth = CalendarUtils.getLastDayOfMonth(newYear, newMonth);
            setValues(new Calendar[]{fisrtDayOfMonth, lastDayOfMonth});
        }

    }

    public void setCustom(Custom custom) {
        this.custom = custom;
        if (custom!=null&&custom.getStartTime() != null) {
            Calendar[] calendars = CalendarUtils.transferToCalendar(new Date[]{custom.getStartTime(), custom.getEndTime()});
            setValues(calendars);
        }

    }

    private void setValues(Calendar[] calendars) {
        setFirst(calendars[0]);
        setSecond(calendars[1]);
        setStartDate(calendars[0].getTime());
        setEndDate(calendars[1].getTime());
    }

}

