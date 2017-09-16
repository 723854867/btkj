package org.btkj.pojo.param.statistics;

import java.util.HashSet;
import java.util.Set;

import org.btkj.pojo.enums.BizType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.enums.TimeType;

public class StatisticScoreParam extends EmployeeParam {

	private static final long serialVersionUID = -4375489238965738784L;
	
	private Integer appId;
	private Integer uid;
	private Integer tid;
	private Integer tarId;
	private Boolean income;
	private BizType type;
	private Integer detailType;
	private String bizId;
	private boolean asc;
	
	private TimeType timeType;
	private Integer endTime;
	private Integer beginTime;
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer week;
	private Integer season;
	
	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getTarId() {
		return tarId;
	}

	public void setTarId(Integer tarId) {
		this.tarId = tarId;
	}

	public Boolean getIncome() {
		return income;
	}

	public void setIncome(Boolean income) {
		this.income = income;
	}

	public BizType getType() {
		return type;
	}

	public void setType(BizType type) {
		this.type = type;
	}

	public Integer getDetailType() {
		return detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public String[] conditions() {
		Set<String> conditions = new HashSet<String>();
		if (null != income)
			conditions.add("`income`=" + (income ? 1 : 0));
		if (null != type)
			conditions.add("`type`=" + type.mark());
		if (null != detailType)
			conditions.add("`detail_type`=" + detailType);
		if (null != bizId)
			conditions.add("`biz_id`=" + bizId);
		if (null != beginTime)
			conditions.add("`created`>=" + beginTime);
		if (null != endTime)
			conditions.add("`created`<=" + endTime);
		return conditions.toArray(new String[]{});
	}
}
