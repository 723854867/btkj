package org.btkj.pojo.param.statistics;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.PolicyNature;
import org.btkj.pojo.enums.VehiclePolicyType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.enums.SortType;
import org.rapid.util.common.enums.TimeType;

public class StatisticPoliciesParam extends EmployeeParam {

	private static final long serialVersionUID = 663082610856371422L;

	private Integer appId;
	private Integer uid;
	private Integer tid;
	private Integer tarId;
	private Integer insurerId;

	private Boolean transfer;
	@NotNull
	private TimeType timeType;
	private PolicyNature nature;
	private VehiclePolicyType type;
	private Integer statisticUsedMod;
	private InsuranceType insuranceType;
	private SortType sortType = SortType.ASC;
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer week;
	private Integer season;
	private Integer beginTime;
	private Integer endTime;
	private Integer groupMod;
	private boolean asc;
	
	public StatisticPoliciesParam() {}
	
	public StatisticPoliciesParam(StatisticActsParam param) {
		this.tid = param.getTid();
		this.asc = param.isAsc();
		this.year = param.getYear();
		this.month = param.getMonth();
		this.day = param.getDay();
		this.week = param.getWeek();
		this.season = param.getSeason();
		this.endTime = param.getEndTime();
		this.beginTime = param.getBeginTime();
		this.timeType = param.getTimeType();
	}
	
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

	public Integer getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Integer insurerId) {
		this.insurerId = insurerId;
	}

	public Boolean getTransfer() {
		return transfer;
	}

	public void setTransfer(Boolean transfer) {
		this.transfer = transfer;
	}

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}

	public PolicyNature getNature() {
		return nature;
	}

	public void setNature(PolicyNature nature) {
		this.nature = nature;
	}

	public VehiclePolicyType getType() {
		return type;
	}

	public void setType(VehiclePolicyType type) {
		this.type = type;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	public Integer getStatisticUsedMod() {
		return statisticUsedMod;
	}
	
	public void setStatisticUsedMod(Integer statisticUsedMod) {
		this.statisticUsedMod = statisticUsedMod;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
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

	public Integer getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Integer beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	
	public boolean isAsc() {
		return asc;
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	public Integer getGroupMod() {
		return groupMod;
	}
	
	public void setGroupMod(Integer groupMod) {
		this.groupMod = groupMod;
	}
	
	public String[] groups() {
		Set<String> conditions = new HashSet<String>();
		if (null != groupMod) {
			for (GroupType type : GroupType.values()) {
				if ((type.mark & groupMod) == type.mark)
					conditions.add(type.name().toLowerCase());
			}
		}
		return conditions.toArray(new String[]{});
	}
	
	public String[] conditions() {
		Set<String> conditions = new HashSet<String>();
		if (null != tid)
			conditions.add("`tid`=" + tid);
		if (null != uid)
			conditions.add("`uid`=" + uid);
		if (null != appId)
			conditions.add("`app_id`=" + appId);
		if (null != tarId)
			conditions.add("`employee_id`=" + tarId);
		if (null != insurerId)
			conditions.add("`insurer_id`=" + insurerId);
		if (null != type)
			conditions.add("`type`=" + type.mark());
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
		if (null != transfer)
			conditions.add("`transfer`=" + (transfer ? 1 : 0));
		if (null != nature)
			conditions.add("`nature`=" + nature.mark());
		if (null != insuranceType)
			conditions.add("`nature`=" + insuranceType.mark());
		if (null != statisticUsedMod) 
			conditions.add("`statistic_used_type&`" + statisticUsedMod + "=`statistic_used_type`");
		return conditions.toArray(new String[]{});
	}
	
	public enum GroupType {
		EMPLOYEE_ID(1),
		UID(2);
		private int mark;
		private GroupType(int mark) {
			this.mark = mark;
		}
		public int mark() {
			return mark;
		}
	}
}
