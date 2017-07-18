package org.btkj.manager.action.tenant.bonus;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class VEHICLE_BONUS_SCALE_SET extends TenantAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<Void> execute(Request request, EmployeeForm ef) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			return vehicleManageService.bonusScaleConfigAdd(ef.getTenant().getTid(), request.getParam(Params.NUM), request.getParam(Params.COMPARISON), request.getParam(Params.ARRAY));
		case UPDATE:
			return vehicleManageService.bonusScaleConfigUpdate(request.getParam(Params.ID), ef.getTenant().getTid(), request.getParam(Params.NUM), request.getParam(Params.COMPARISON), request.getParam(Params.ARRAY));
		case DELETE:
			return vehicleManageService.bonusScaleConfigDelete(request.getParam(Params.ID), ef.getTenant().getTid());
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
