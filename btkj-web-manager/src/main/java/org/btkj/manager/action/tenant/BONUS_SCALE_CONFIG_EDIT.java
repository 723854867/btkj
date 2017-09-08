package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.param.vehicle.BonusScaleConfigEditParam;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class BONUS_SCALE_CONFIG_EDIT extends EmployeeAction<BonusScaleConfigEditParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	public BONUS_SCALE_CONFIG_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, BonusScaleConfigEditParam param) {
		return vehicleManageService.bonusScaleConfigEdit(tenant.getTid(), param);
	}
}
