package org.btkj.manager.action.tenant.bonus;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.vehicle.pojo.BonusScaleConfigType;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class VEHICLE_BONUS_MANAGE_SET extends TenantAction {

	@Override
	protected Result<Void> execute(Request request, EmployeeForm ef) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			int depth = request.getParam(Params.DEPTH);
			int type = request.getParam(Params.TYPE);
			break;
		case UPDATE:
			break;
		case DELETE:
			break;
		default:
			return Consts.RESULT.FORBID;
		}
		return null;
	}
}
