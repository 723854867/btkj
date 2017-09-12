package org.btkj.pojo.param.statistics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.PolicyNature;
import org.btkj.pojo.enums.VehiclePolicyType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.enums.SortType;
import org.rapid.util.common.enums.TimeType;

public class StatisticPoliciesParam extends EmployeeParam {

	private static final long serialVersionUID = 663082610856371422L;

	private Set<Integer> apps;
	private Set<Integer> users;
	private Set<Integer> tenants;
	private Set<Integer> insurers;
	private Set<Integer> employees;

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

	public Set<Integer> getApps() {
		return apps;
	}

	public void setApps(Set<Integer> apps) {
		this.apps = apps;
	}

	public Set<Integer> getUsers() {
		return users;
	}

	public void setUsers(Set<Integer> users) {
		this.users = users;
	}

	public Set<Integer> getTenants() {
		return tenants;
	}

	public void setTenants(Set<Integer> tenants) {
		this.tenants = tenants;
	}

	public Set<Integer> getInsurers() {
		return insurers;
	}

	public void setInsurers(Set<Integer> insurers) {
		this.insurers = insurers;
	}

	public Set<Integer> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Integer> employees) {
		this.employees = employees;
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
	
	public Integer getGroupMod() {
		return groupMod;
	}
	
	public void setGroupMod(Integer groupMod) {
		this.groupMod = groupMod;
	}
	
	public void tenantFilter(int tid) { 
		this.apps = null;
		this.users = null;
		this.tenants = null;
		this.tenants = new HashSet<Integer>();
		this.tenants.add(tid);
	}
	
	public Map<String, Object> params() {
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != transfer)
			params.put(BtkjConsts.FIELD.TRANSFER, this.transfer);
		if (null != nature)
			params.put(BtkjConsts.FIELD.NATURE, this.nature.mark());
		if (null != type)
			params.put(BtkjConsts.FIELD.TYPE, this.type.mark());
		if (null != insuranceType)
			params.put(BtkjConsts.FIELD.INSURANCE_TYPE, this.insuranceType.mark());
		if (null != year)
			params.put(BtkjConsts.FIELD.YEAR, this.year);
		if (null != month)
			params.put(BtkjConsts.FIELD.MONTH, this.month);
		if (null != day)
			params.put(BtkjConsts.FIELD.DAY, this.day);
		if (null != season)
			params.put(BtkjConsts.FIELD.SEASON, this.season);
		if (null != week)
			params.put(BtkjConsts.FIELD.WEEK, this.week);
		return params;
	}
}
