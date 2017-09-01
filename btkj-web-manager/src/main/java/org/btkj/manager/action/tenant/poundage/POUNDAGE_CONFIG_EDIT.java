package org.btkj.manager.action.tenant.poundage;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam.NodeConfigInfo;
import org.rapid.util.common.message.Result;

public class POUNDAGE_CONFIG_EDIT extends EmployeeAction<PoundageConfigEditParam> {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PoundageConfigEditParam param) {
		param.setTid(tenant.getTid());
		NodeConfigInfo config = param.getConfig();
		if (null != config) {
			config.setCmRate(Math.min(config.getCmRate(), BtkjConsts.LIMITS.MAX_COMMISION_RATE));
			config.setCpRate(Math.min(config.getCpRate(), BtkjConsts.LIMITS.MAX_COMMISION_RATE));
			config.setCmRate(Math.max(config.getCmRate(), BtkjConsts.LIMITS.MIN_COMMISION_RATE));
			config.setCpRate(Math.max(config.getCpRate(), BtkjConsts.LIMITS.MIN_COMMISION_RATE));
			config.setCmRetainRate(Math.min(config.getCmRetainRate(), BtkjConsts.LIMITS.MAX_COMMISION_RETAIN_RATE));
			config.setCpRetainRate(Math.min(config.getCpRetainRate(), BtkjConsts.LIMITS.MAX_COMMISION_RETAIN_RATE));
			config.setCmRetainRate(Math.max(config.getCmRetainRate(), BtkjConsts.LIMITS.MIN_COMMISION_RETAIN_RATE));
			config.setCpRetainRate(Math.max(config.getCpRetainRate(), BtkjConsts.LIMITS.MIN_COMMISION_RETAIN_RATE));
		}
		return bonusService.poundageConfigEdit(param);
	}
}
