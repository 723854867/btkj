package org.btkj.vehicle.rule;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.config.pojo.entity.Area;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.bo.PolicySchema;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.consts.code.ICode;
import org.rapid.util.lang.DateUtil;
import org.springframework.stereotype.Component;

@Component("rule")
public class Rule {

	@Resource
	private ConfigService configService;
	
	/**
	 * 检查投保规则
	 * 
	 * @param order
	 * @return
	 */
	public ICode orderCheck(EmployeeForm ef, PolicySchema schema) {
		// 检查起保时间
		if (null != schema.getCommercialStart()) {
			ICode code = _checkRenewalPeriod(ef, schema.getCommercialStart());
			if (code != Code.OK)
				return code;
		}
		if (null != schema.getCompulsiveStart()) {
			ICode code = _checkRenewalPeriod(ef, schema.getCompulsiveStart());
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
	private ICode _checkRenewalPeriod(EmployeeForm ef, String startTime) {
		Area area = configService.area(ef.getTenant().getRegion());
		int renewalInterval = area.getRenewalPeriod() * 24 * 3600;
		int timestamp = (int) (DateUtil.getTime(startTime, DateUtil.YYYY_MM_DD_HH_MM_SS) / 1000);
		int maxTime = DateUtil.currentTime() + renewalInterval;
		if (timestamp > maxTime || timestamp < DateUtil.currentTime())
			return BtkjCode.NOT_IN_RENEWAL_PERIOD;
		return Code.OK;
	}
}
