package com.zhouyi.business.core.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtil {

	public static List<List<String>> getWeekListDate(Date now) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<List<String>> totalList=new ArrayList<List<String>>();
		
		Calendar cal = Calendar.getInstance();  
		try{
	        cal.setTime(now);  
	  
	        int d = 0;  
	        if(cal.get(Calendar.DAY_OF_WEEK)==1){  
	            d = -6;  
	        }else{  
	            d = 2-cal.get(Calendar.DAY_OF_WEEK);  
	        }  
	        cal.add(Calendar.DAY_OF_WEEK, d);  
	        int days=7;
	        for(int w=0;w<4;w++){
	        	List<String> list=new ArrayList<String>();
	        	if(w==0){
	        		list.add(sdf.format(cal.getTime()));
	        		days=6;
	        	}else{
	        		days=7;
	        	}
		        for(int i=0;i<days;i++){
		        	cal.add(Calendar.DAY_OF_MONTH, 1);
		        	list.add(sdf.format(cal.getTime()));
		        }
		        totalList.add(list);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
        return totalList;
	}  
	
	/**
	 * 获取明天日期
	 * @return
	 */
	public static String getTomorrowDate(){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String day=sdf.format(cal.getTime());
		return day;
	}
	
	/**
	 * 获取时间差
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getTimeDiff(String startTime,String endTime){
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
		try{
			Long d=sdf.parse(endTime).getTime()-sdf.parse(startTime).getTime();
			int hours = (int) (d/(1000 * 60 * 60));  
			return hours;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String getTimeExpireByMinute(int minute){
		Date now=new Date();
		Date timeExpire = new Date(now.getTime() + (30 * 60 * 1000));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(timeExpire);
	}
//	public static void main(String[] args){
//		int d=getTimeDiff("10:00","11:00");
//		System.out.println(d);
//	}
}
