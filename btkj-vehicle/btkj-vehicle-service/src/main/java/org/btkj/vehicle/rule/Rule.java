package org.btkj.vehicle.rule;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.btkj.vehicle.pojo.entity.City;
import org.btkj.vehicle.redis.CityMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.consts.code.ICode;
import org.rapid.util.lang.DateUtils;
import org.springframework.stereotype.Component;

@Component("rule")
public class Rule {

	@Resource
	private CityMapper cityMapper;
	
	/**
	 * 检查投保规则
	 * 
	 * @param order
	 * @return
	 */
	public ICode orderCheck(int cityCode, PolicySchema schema) {
		// 检查起保时间
		if (null != schema.getCommercialStart()) {
			ICode code = _checkRenewalPeriod(cityCode, schema.getCommercialStart());
			if (code != Code.OK)
				return code;
		}
		if (null != schema.getCompulsiveStart()) {
			ICode code = _checkRenewalPeriod(cityCode, schema.getCompulsiveStart());
			if (code != Code.OK)
				return code;
		}
		return Code.OK;
	}
	
	/**
	 * 检查续保期限
	 * 
	 * @return
	 */
	private ICode _checkRenewalPeriod(int cityCode, String startTime) {
		City city = cityMapper.getByKey(cityCode);
		if (null == city)
			return BtkjCode.CITY_UNSUPPORT;
		int renewalInterval = city.getRenewalPeriod() * 24 * 3600;
		int timestamp = (int) (DateUtils.getTime(startTime, DateUtils.YYYY_MM_DD_HH_MM_SS) / 1000);
		int maxTime = DateUtils.currentTime() + renewalInterval;
		if (timestamp > maxTime || timestamp < DateUtils.currentTime())
			return BtkjCode.NOT_IN_RENEWAL_PERIOD;
		return Code.OK;
	}
}
