package org.btkj.pojo.vo;

import org.rapid.util.common.enums.TIME_TYPE;

public class StatisticSearcher extends Page {

	private static final long serialVersionUID = -8984092571554101926L;

	private int begin;
	private int end;
	private TIME_TYPE timeType;
	
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
	
	public TIME_TYPE getTimeType() {
		return timeType;
	}
	
	public void setTimeType(TIME_TYPE timeType) {
		this.timeType = timeType;
	}
}
