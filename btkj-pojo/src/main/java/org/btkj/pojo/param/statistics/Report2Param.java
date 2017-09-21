package org.btkj.pojo.param.statistics;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.enums.StatisticUsedType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.enums.TimeType;

public class Report2Param extends EmployeeParam {

	private static final long serialVersionUID = 663082610856371422L;

	private int tid;
	private Integer day;
	private Integer year;
	private Integer month;
	@NotNull
	private TimeType timeType;
	private StatisticUsedType usedType;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
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

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}

	public StatisticUsedType getUsedType() {
		return usedType;
	}

	public void setUsedType(StatisticUsedType usedType) {
		this.usedType = usedType;
	}
	
	public String[] conditions() {
		Set<String> set = new HashSet<String>();
		set.add("`tid`=" + tid);
		if (null != year)
			set.add("`year`=" + year);
		if (null != month)
			set.add("`month`=" + month);
		if (null != day)
			set.add("`day`=" + day);
		if (null != usedType)
			set.add("`used_type`=" + usedType.mark());
		return set.toArray(new String[]{});
	}
}
