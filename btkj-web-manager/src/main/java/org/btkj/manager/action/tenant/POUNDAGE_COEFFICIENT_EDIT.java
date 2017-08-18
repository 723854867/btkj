package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.param.PoundageCoefficientEditParam;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class POUNDAGE_COEFFICIENT_EDIT extends EmployeeAction<PoundageCoefficientEditParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	public POUNDAGE_COEFFICIENT_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PoundageCoefficientEditParam param) {
		param.setTid(tenant.getTid());
		return vehicleManageService.poundageCoefficientEdit(param);
	}
}
