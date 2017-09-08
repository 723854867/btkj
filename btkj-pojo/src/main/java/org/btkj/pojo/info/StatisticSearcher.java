package org.btkj.pojo.info;

import org.rapid.util.common.enums.TimeType;

public class StatisticSearcher extends Page {

	private static final long serialVersionUID = -8984092571554101926L;

	private int begin;
	private int end;
	private TimeType timeType;
	
	public int getBegin() {
		return begin;
	}
	
	public void setBegin(int begin) {
		this.begin = begin;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public TimeType getTimeType() {
		return timeType;
	}
	
	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}
}
