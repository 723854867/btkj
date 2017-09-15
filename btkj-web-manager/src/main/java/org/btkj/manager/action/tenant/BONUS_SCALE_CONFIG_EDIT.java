package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
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
	protected Result<?> execute(App app, User user, Tenant tenant, Employee employee, BonusScaleConfigEditParam param) {
		return vehicleManageService.bonusScaleConfigEdit(tenant.getTid(), param);
	}
}
