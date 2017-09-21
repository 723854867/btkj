package org.btkj.pojo.param.statistics;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.enums.TimeType;

public class StatisticActsParam extends EmployeeParam {

	private static final long serialVersionUID = 8373881041011356405L;

	private int tid;
	private boolean asc;
	private Integer day;
	private Integer year;
	private Integer week;
	private Integer month;
	private Integer season;
	private Integer endTime;
	private Integer beginTime;
	@NotNull
	private TimeType timeType;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public boolean isAsc() {
		return asc;
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Integer beginTime) {
		this.beginTime = beginTime;
	}

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}
	
	public String[] conditions() {
		Set<String> conditions = new HashSet<String>();
		conditions.add("`tid`=" + tid);
		if (null != year)
			conditions.add("`year`=" + year);
		if (null != month)
			conditions.add("`month`=" + month);
		if (null != day)
			conditions.add("`day`=" + day);
		if (null != week)
			conditions.add("`week`=" + week);
		if (null != season)
			conditions.add("`season`=" + season);
		if (null != beginTime)
			conditions.add("`created`>=" + beginTime);
		if (null != endTime)
			conditions.add("`created`<=" + endTime);
		return conditions.toArray(new String[]{});
	}
}
