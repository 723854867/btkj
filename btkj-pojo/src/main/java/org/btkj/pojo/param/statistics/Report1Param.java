package org.btkj.pojo.param.statistics;

import java.util.HashSet;
import java.util.Set;

import org.btkj.pojo.enums.StatisticUsedType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.lang.CollectionUtil;

public class Report1Param extends EmployeeParam {

	private static final long serialVersionUID = 1278223345364874188L;

	private int tid;
	private Set<Integer> insurers;
	private StatisticUsedType usedType;
	private Integer endTime;
	private Integer beginTime;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public Set<Integer> getInsurers() {
		return insurers;
	}

	public void setInsurers(Set<Integer> insurers) {
		this.insurers = insurers;
	}

	public StatisticUsedType getUsedType() {
		return usedType;
	}

	public void setUsedType(StatisticUsedType usedType) {
		this.usedType = usedType;
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
	
	public String[] conditions() {
		Set<String> set = new HashSet<String>();
		set.add("`tid`=" + tid);
		if (null != usedType)
			set.add("`used_type`=" + usedType.mark());
		if (null != endTime)
			set.add("`created`<=" + endTime);
		if (null != beginTime)
			set.add("`created`>=" + beginTime);
		if (!CollectionUtil.isEmpty(insurers)) {
			StringBuilder builder = new StringBuilder("`insurer_id IN(`");
			for (int insurerId : insurers)
				builder.append(insurerId).append(",");
			builder.deleteCharAt(builder.length() - 1);
			builder.append(")");
			set.add(builder.toString());
		}
		return set.toArray(new String[]{});
	}
}
