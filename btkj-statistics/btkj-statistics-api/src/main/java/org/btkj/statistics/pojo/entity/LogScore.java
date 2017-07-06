package org.btkj.statistics.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class LogScore implements UniqueModel<Long> {

	private static final long serialVersionUID = 5025465074564999575L;

	private long id;
	private int employeeId;
	private int uid;
	private int tid;
	private int appId;
	private int type;
	private int bizId;
	private double quota;
	private int year;
	private int month;
	private int day;
	private int week;
	private int season;
	private int created;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getBizId() {
		return bizId;
	}
	
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	
	public double getQuota() {
		return quota;
	}
	
	public void setQuota(double quota) {
		this.quota = quota;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public Long key() {
		return this.id;
	}
}
