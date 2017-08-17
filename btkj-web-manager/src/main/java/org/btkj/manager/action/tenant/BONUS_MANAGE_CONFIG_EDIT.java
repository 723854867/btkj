package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.param.BonusManageConfigEditParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class BONUS_MANAGE_CONFIG_EDIT extends EmployeeAction<BonusManageConfigEditParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	public BONUS_MANAGE_CONFIG_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, BonusManageConfigEditParam param) {
		if (param.getCrudType() == CrudType.CREATE && param.getTeamDepth() > tenant.getTeamDepth())
			return Consts.RESULT.FORBID;
		return vehicleManageService.bonusManageConfigEdit(tenant.getTid(), param);
	}
}
