package org.btkj.manager.action.tenant.poundage;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.vehicle.CoefficientRangeEditParam;
import org.btkj.vehicle.api.BonusService;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class COEFFICIENT_RANGE_EDIT extends EmployeeAction<CoefficientRangeEditParam> {
	
	@Resource
	private BonusService bonusService;
	
	public COEFFICIENT_RANGE_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<?> execute(App app, User user, Tenant tenant, Employee employee, CoefficientRangeEditParam param) {
		param.setTid(tenant.getTid());
		return bonusService.coefficientRangeEdit(param);
	}
}
