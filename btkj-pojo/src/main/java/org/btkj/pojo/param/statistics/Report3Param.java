package org.btkj.pojo.param.statistics;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.enums.StatisticUsedType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.enums.TimeType;

public class Report3Param extends EmployeeParam {

	private static final long serialVersionUID = 8373881041011356405L;

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

	public String[] conditions(boolean act) {
		Set<String> conditions = new HashSet<String>();
		conditions.add("`tid`=" + tid);
		if (act)
			conditions.add("`type` IN(1, 2)");
		if (null != year)
			conditions.add("`year`=" + year);
		if (null != month)
			conditions.add("`month`=" + month);
		if (null != day)
			conditions.add("`day`=" + day);
		if (null != usedType)
			conditions.add("`used_type`=" + usedType.mark());
		return conditions.toArray(new String[] {});
	}
}
