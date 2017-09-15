package org.btkj.common.pojo.param;

import org.btkj.pojo.param.EmployeeParam;

public class ScoreRewardParam extends EmployeeParam {

	private static final long serialVersionUID = -8580567257773322709L;

	private int type;
	private int endTime;
	private int beginTime;
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public int getBeginTime() {
		return beginTime;
	}
	
	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}
}
