package com.company.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;;

/**
 * String 辅助类
 * @author Lauchikeung
 *
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return true 空，false 非空
	 */
	public static boolean isEmpty(String str){
		if("".equals(str)||null == str){
			return true;
		}
		return false;
	}
	
	/**
	 * 使用正则表达式验证
	 * @param regex 正则表达式
	 * @param input 内容
	 * @return 匹配成功为true
	 */
	public static boolean validByRegex(String regex, String input){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();//find()
	}
	/*
	 * 表达式
	 * 是否数字
	 * ^0[x|X][\\da-eA-E]+$
	 * ^[-+]{0,1}\\d*\\.{0,1}\\d+$
	 * 是否中文
	 * ^[\u4e00-\u9fa5]+$
	 * 是否整数
	 * ^[-+]{0,1}\\d*$
	 */
	/**
	 * 输入值是否数字
	 * @param value
	 * @return true 是数字
	 */
	public static boolean isNumberic(String value){
		return validByRegex("^[-+]{0,1}\\d*\\.{0,1}\\d+$",value);
	}
	
	/**
	 * 输入值是否整数
	 * @param value
	 * @return true 是整数
	 */
	public static boolean isInteger(String value){
		return validByRegex("^[-+]{0,1}\\d*$", value);
	}
}
