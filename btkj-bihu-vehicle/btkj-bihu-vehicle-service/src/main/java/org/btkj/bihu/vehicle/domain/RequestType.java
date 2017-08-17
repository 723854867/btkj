package org.btkj.bihu.vehicle.domain;

public enum RequestType {

	/**
	 * 获取续保
	 */
	RENEWL,
	
	/**
	 * 获取车型信息
	 * 
	 */
	VEHICLE_INFO,
	
	/**
	 * 报价/核保
	 */
	QUOTE,
	
	/**
	 * 获取报价接口
	 */
	QUOTE_RESULT,
	
	/**
	 * 获取核保接口
	 */
	INSURE_RESULT;
}
