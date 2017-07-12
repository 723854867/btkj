package org.btkj.manager.action.tenant.bonus;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.BonusScaleConfigType;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

public class VEHICLE_BONUS_MANAGE_SET extends TenantAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<Void> execute(Request request, EmployeeForm ef) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			int depth = request.getParam(Params.DEPTH);
			BonusScaleConfigType type = BonusScaleConfigType.match(request.getParam(Params.TYPE));
			if (null == type)
				throw ConstConvertFailureException.errorConstException(Params.TYPE);
			int rate = request.getParam(Params.NUM);
			break;
		case UPDATE:
			int id = request.getParam(Params.ID);
			rate = request.getParam(Params.NUM);
			break;
		case DELETE:
			id = request.getParam(Params.ID);
			break;
		default:
			return Consts.RESULT.FORBID;
		}
		return null;
	}
}
