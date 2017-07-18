package org.btkj.manager.action.tenant.bonus;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.BonusManageConfigType;
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
	protected Result<?> execute(Request request, Employee employee) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			int depth = request.getParam(Params.DEPTH);
			if (depth <= 1 || depth > employee.getTeamDepth())
				return Consts.RESULT.FORBID;
			BonusManageConfigType type = BonusManageConfigType.match(request.getParam(Params.TYPE));
			if (null == type)
				throw ConstConvertFailureException.errorConstException(Params.TYPE);
			int rate = request.getParam(Params.NUM);
			return vehicleManageService.bonusManageConfigAdd(employee.getTid(), type, depth, rate);
		case UPDATE:
			String id = request.getParam(Params.KEY);
			rate = request.getParam(Params.NUM);
			return vehicleManageService.bonusManageConfigUpdate(id, employee.getTid(), rate);
		case DELETE:
			id = request.getParam(Params.KEY);
			return vehicleManageService.bonusManageConfigDelete(id, employee.getTid());
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
