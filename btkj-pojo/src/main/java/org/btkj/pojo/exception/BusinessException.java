package org.btkj.pojo.exception;

import org.rapid.util.common.consts.code.ICode;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 2542020019232961715L;

	private ICode code;
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(ICode code) {
		this.code = code;
	}
	
	public BusinessException(ICode code, String message) {
		super(message);
		this.code = code;
	}
	
	public BusinessException(ICode code, Throwable cause) {
		super(cause);
		this.code = code;
	}
	
	public BusinessException(ICode code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}
	
	public ICode getCode() {
		return code;
	}
}
