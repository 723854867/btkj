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
	APP_OFF_SHELFS(501, "The app is now off the shelfs"),
	
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
	ALREADY_IS_EMPLOYEE(504, "already is employee");
	
	
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
