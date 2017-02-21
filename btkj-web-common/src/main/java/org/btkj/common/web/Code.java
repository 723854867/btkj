package org.btkj.common.web;

import org.rapid.util.common.consts.code.ICode;

public enum Code implements ICode {
	
	ACTION_NOT_EXIST(101, "action not exist");
	
	private int code;
	private String desc;
	
	private Code(int id, String desc) {
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
