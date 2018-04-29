package com.company.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author Lauchikeung
 *
 */
public class DateUtils {
	
	/**
	 * 两日期相减得相差（年月日...）
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param calendarType 年月日..
	 * @return 相差年月日...
	 */
	public static int getDateByTwoDayMinus(Date startDate,Date endDate,int calendarType){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int startDay = calendar.get(calendarType);
		calendar.setTime(endDate);
		int endDay = calendar.get(calendarType);
		return endDay - startDay;
	}
	
	
	/**
	 * 给日期添加（年月日...）
	 * @param date 日期
	 * @param calendarType 年月日...
	 * @param num 添加或相减年月日 可用负数
	 * @return 日期加 年月日...
	 */
	public static Date getDateByNum(Date date,int calendarType,Integer num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendarType, num);
		return calendar.getTime();
	}
	
	/**
	 * 得出年月日
	 * @param date 日期
	 * @param calendarType 年月日...
	 * @param num 添加或相减年月日 可用负数
	 * @return 得出年月日 年月0代表1
	 */
	public static int getDate(Date date,int calendarType,Integer num){
		Calendar calendar = Calendar.getInstance();		
		if(date!=null)
			calendar.setTime(date);
		calendar.add(calendarType, num);
		return calendar.get(calendarType);
			
	}

}
