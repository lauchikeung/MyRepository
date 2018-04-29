package com.company.common.utils;

/**
 * 数值辅助类
 * @author Lauchikeung
 *
 */
public class NumericUtils {
	
	/**
	 * 通用型方法 暂时放这里
	 * @param value	待比较的值
	 * @param levelValue	等级比较的值 由大到小存放
	 * @param levels 等级
	 * @return 筛选出来的数值
	 */
	public static int assessmentEvent(double value,double[] levelValue,int[] levels){
		for (int i = 0; i < levels.length; i++) {
			if(i==0){
				if(value >= levelValue[i]){
					return levels[i];
				}
			}else if(i==levels.length-1){
				if(value < levelValue[i-2]){
					return levels[i];
				}
			}else if(i>0){
				if(value < levelValue[i-1] && value >= levelValue[i]){
					return levels[i];
				}
			}	
		}
		return 0;
	}

	/**
	 * 取最小值
	 * @param array 待比较的数
	 * @return 最小值
	 */
	public static double getMin(double[] array){
		double min = array[0];
		for (int i = 0; i < array.length; i++) {
			if(array[i] < min){
				min = array[i];
			}
		}
		return min;
	}
	
	/**
	 * 取最大值
	 * @param array 待比较的数
	 * @return 最大值
	 */
	public static double getMax(double[] array){
		double max = array[0];
		for (int i = 0; i < array.length; i++) {
			if(array[i] > max){
				max = array[i];
			}
		}
		return max;
	}
}
