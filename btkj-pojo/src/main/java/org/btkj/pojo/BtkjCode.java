package org.btkj.pojo;

import org.rapid.util.common.consts.code.ICode;

/**
 * 所有 btkj 的公共 code 都在改类中定义。0~500 是公共预留 code，1000以上是参数错误码
 * 
 * @author ahab
 */
public enum BtkjCode implements ICode {
	
	/**
	 * action 不存在
	 */
	ACTION_NOT_EXIST(500, "action not exist"),
	
	/**
	 * app 已经下架了
	 */
	APP_NOT_EXIST(501, "app not exist"),
	
	/**
	 * 已经存在申请了
	 * 
	 */
	APPLY_EXIST(502, "apply exist"),
	
	/**
	 * 申请不存在
	 */
	APPLY_NOT_EXIST(503, "apply not exist"),
	
	/**
	 * 已经是雇员了
	 */
	ALREADY_IS_EMPLOYEE(504, "already is employee"),
	
	/**
	 * 手机号已经被占用了
	 */
	MOBILE_EXIST(505, "mobile exist"),
	
	/**
	 * 用户代理商个数最大值
	 */
	USER_TENANT_NUM_MAXIMUM(506, "tenant number maximum"),
	
	/**
	 * app 大力上个数最大值
	 */
	APP_TENANT_NUM_MAXIMUM(507, "tenant number maximum"),
	
	/**
	 * 代理公司不存在
	 */
	TENANT_NOT_EXIST(508, "tenant not exist"),
	
	/**
	 * 雇员不存在
	 */
	EMPLOYEE_NOT_EXIST(509, "employee not exist"),
	
	/**
	 * 用户资料不全
	 */
	USER_DATA_INCOMPLETE(510, "user data is incomplete");
	
	private int code;
	private String desc;
	
	private BtkjCode(int id, String desc) {
		this.code = id;
		this.desc = desc;
	}

	@Override
	public int id() {
		return code;
	}

	@Override
	public String key() {
		throw new UnsupportedOperationException("Code has no key attribute!");
	}

	@Override
	public String value() {
		return desc;
	}
}
