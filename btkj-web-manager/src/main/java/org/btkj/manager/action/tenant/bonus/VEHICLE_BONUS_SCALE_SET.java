package org.btkj.manager.action.tenant.bonus;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

public class VEHICLE_BONUS_SCALE_SET extends TenantAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<Void> execute(Request request, EmployeeForm ef) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			BonusManageConfigType type = BonusManageConfigType.match(request.getParam(Params.TYPE));
			if (null == type)
				throw ConstConvertFailureException.errorConstException(Params.TYPE);
			int rate = request.getParam(Params.NUM);
			int min = request.getParam(Params.MIN);
			int max = request.getParam(Params.MAX);
			return vehicleManageService.bonusScaleConfigAdd(ef.getTenant().getTid(), type, rate, min, max);
		case UPDATE:
			int id = request.getParam(Params.ID);
			rate = request.getParam(Params.NUM);
			min = request.getParam(Params.MIN);
			max = request.getParam(Params.MAX);
			return vehicleManageService.bonusScaleConfigUpdate(id, ef.getTenant().getTid(), rate, min, max);
		case DELETE:
			id = request.getParam(Params.ID);
			return vehicleManageService.bonusScaleConfigDelete(id, ef.getTenant().getTid());
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
