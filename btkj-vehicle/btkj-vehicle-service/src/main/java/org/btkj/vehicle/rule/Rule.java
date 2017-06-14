package org.btkj.vehicle.rule;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.btkj.vehicle.mybatis.entity.City;
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
	private ICode _checkRenewalPeriod(int cityCode, String time) {
		City city = cityMapper.getByKey(cityCode);
		if (null == city)
			return BtkjCode.CITY_UNSUPPORT;
		int timestamp = Integer.valueOf(time);
		int maxTime = DateUtils.currentTime() + city.getRenewalPeriod() * 24 * 3600;
		if (timestamp > maxTime || timestamp < DateUtils.currentTime())
			return BtkjCode.NOT_IN_RENEWAL_PERIOD;
		return Code.OK;
	}
}
