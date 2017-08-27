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
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam.Type;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class POUNDAGE_CONFIG_EDIT extends EmployeeAction<PoundageConfigEditParam> {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PoundageConfigEditParam param) {
		param.setTid(tenant.getTid());
		if (param.getType() == Type.EDIT) {
			PoundageConfigEditParam.Node node = param.getNode();
			if (null == node)
				return Consts.RESULT.FORBID;
			node.setCommercialRate(Math.min(node.getCommercialRate(), BtkjConsts.LIMITS.MAX_COMMISION_RATE));
			node.setCompulsoryRate(Math.min(node.getCompulsoryRate(), BtkjConsts.LIMITS.MAX_COMMISION_RATE));
			node.setCommercialRate(Math.max(node.getCommercialRate(), BtkjConsts.LIMITS.MIN_COMMISION_RATE));
			node.setCompulsoryRate(Math.max(node.getCompulsoryRate(), BtkjConsts.LIMITS.MIN_COMMISION_RATE));
			node.setCommercialRetainRate(Math.min(node.getCommercialRetainRate(), BtkjConsts.LIMITS.MAX_COMMISION_RETAIN_RATE));
			node.setCompulsoryRetainRate(Math.min(node.getCompulsoryRetainRate(), BtkjConsts.LIMITS.MAX_COMMISION_RETAIN_RATE));
			node.setCommercialRetainRate(Math.max(node.getCommercialRetainRate(), BtkjConsts.LIMITS.MIN_COMMISION_RETAIN_RATE));
			node.setCompulsoryRetainRate(Math.max(node.getCompulsoryRetainRate(), BtkjConsts.LIMITS.MIN_COMMISION_RETAIN_RATE));
		}
		return null;
	}
}
