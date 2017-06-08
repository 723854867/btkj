package org.btkj.courier.pojo;

import java.io.Serializable;

public class JianJieResp implements Serializable {

	private static final long serialVersionUID = -3730464990743456645L;

	private boolean SuccessStatus;
	private String ErrorMessage;
	private String Result;

	public boolean isSuccessStatus() {
		return SuccessStatus;
	}

	public void setSuccessStatus(boolean successStatus) {
		SuccessStatus = successStatus;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}
}
