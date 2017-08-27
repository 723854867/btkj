package org.btkj.manager.action.tenant.poundage;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.pojo.param.PoundageCoefficientRangeEditParam;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class POUNDAGE_COEFFICIENT_RANGE_EDIT extends EmployeeAction<PoundageCoefficientRangeEditParam> {
	
	@Resource
	private BonusService bonusService;
	
	public POUNDAGE_COEFFICIENT_RANGE_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PoundageCoefficientRangeEditParam param) {
		param.setTid(tenant.getTid());
		return bonusService.poundageCoefficientRangeEdit(param);
	}
}
