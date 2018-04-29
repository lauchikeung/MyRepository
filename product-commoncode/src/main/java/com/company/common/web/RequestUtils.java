package com.company.common.web;

import javax.servlet.http.HttpServletRequest;

/**
 * Request 辅助类
 * @author Lauchikeung
 *
 */
public class RequestUtils {

	
	public static String getParameter(HttpServletRequest request,String value){
		return request.getParameter(value);
	}
}
