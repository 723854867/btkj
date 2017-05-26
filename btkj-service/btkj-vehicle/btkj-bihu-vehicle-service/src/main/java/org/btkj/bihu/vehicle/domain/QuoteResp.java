package org.btkj.bihu.vehicle.domain;

import java.io.Serializable;

import org.btkj.bihu.vehicle.RespHandler;

public class QuoteResp implements Serializable {
	
	private static final long serialVersionUID = 8596847564565549751L;

	public static final RespHandler<QuoteResp> JSON_HANDLER			= RespHandler.build(QuoteResp.class);

	private int BusinessStatus;
	private String StatusMessage;

	public int getBusinessStatus() {
		return BusinessStatus;
	}

	public void setBusinessStatus(int businessStatus) {
		BusinessStatus = businessStatus;
	}

	public String getStatusMessage() {
		return StatusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		StatusMessage = statusMessage;
	}
}
