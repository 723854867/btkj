package org.btkj.pojo.comparable;

public enum Comparable {

	/**
	 * 小于
	 */
	LESS,
	
	/**
	 * 小于等于
	 */
	LESS_EQUAL,
	
	/**
	 * 大于
	 */
	LARGE,
	
	/**
	 * 大于等于
	 */
	LARGE_EQUAL,
	
	/**
	 * 小于xx并且大于xx
	 */
	BETWEEN,
	
	/**
	 * 小于xx并且大于等于xx
	 * 
	 */
	BETWEEN_LEFT_EQUAL,
	
	/**
	 * 小于等于xx并且大于xx
	 */
	BETWEEN_RIGHT_EQUAL;
}
