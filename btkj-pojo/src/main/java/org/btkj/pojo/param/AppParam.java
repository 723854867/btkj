package org.btkj.pojo.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class AppParam extends Param {

	private static final long serialVersionUID = 5644425975990988063L;

	@Min(1)
	@Max(1000)
	private int appId;
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
}
