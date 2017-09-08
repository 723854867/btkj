package org.btkj.pojo.param.statistics;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.PolicyNature;
import org.btkj.pojo.enums.StatisticUsedType;
import org.btkj.pojo.enums.VehiclePolicyType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.enums.SortType;
import org.rapid.util.common.enums.TimeType;

public class StatisticPoliciesParam extends EmployeeParam {

	private static final long serialVersionUID = 663082610856371422L;

	private Integer tid;
	private Integer uid;
	private Integer appId;
	private Boolean transfer;
	@NotNull
	private TimeType timeType;
	private Integer insurerId;
	private Integer salesmanId;
	private PolicyNature nature;
	private VehiclePolicyType type;
	private InsuranceType insuranceType;
	private StatisticUsedType statisticUsedType;
	private SortType sortType = SortType.ASC;
	private int beginTime;
	private int endTime;

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Boolean isTransfer() {
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

	public Integer getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Integer insurerId) {
		this.insurerId = insurerId;
	}
	
	public Integer getSalesmanId() {
		return salesmanId;
	}
	
	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
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

	public StatisticUsedType getStatisticUsedType() {
		return statisticUsedType;
	}

	public void setStatisticUsedType(StatisticUsedType statisticUsedType) {
		this.statisticUsedType = statisticUsedType;
	}
	
	public SortType getSortType() {
		return sortType;
	}
	
	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}
	
	public int getBeginTime() {
		return beginTime;
	}
	
	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public Map<String, Object> params() { 
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != tid)
			params.put(BtkjConsts.FIELD.TID, this.tid);
		if (null != appId)
			params.put(BtkjConsts.FIELD.APP_ID, this.appId);
		if (null != uid)
			params.put(BtkjConsts.FIELD.UID, this.uid);
		if (null != salesmanId)
			params.put(BtkjConsts.FIELD.EMPLOYEE_ID, this.salesmanId);
		if (null != insurerId)
			params.put(BtkjConsts.FIELD.INSURER_ID, this.insurerId);
		if (null != transfer)
			params.put(BtkjConsts.FIELD.TRANSFER, this.transfer);
		if (null != nature)
			params.put(BtkjConsts.FIELD.NATURE, this.nature.mark());
		if (null != type)
			params.put(BtkjConsts.FIELD.TYPE, this.type.mark());
		if (null != insuranceType)
			params.put(BtkjConsts.FIELD.INSURANCE_TYPE, this.insuranceType.mark());
		if (null != statisticUsedType)
			params.put(BtkjConsts.FIELD.STATISTIC_USED_TYPE, this.statisticUsedType.mark());
		return params;
	}
}
