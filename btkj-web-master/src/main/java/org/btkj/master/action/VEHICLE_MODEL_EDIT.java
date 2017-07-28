package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class VEHICLE_MODEL_EDIT extends LoggedAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			return vehicleManageService.modelAdd(request.getParam(Params.ID), request.getParam(Params.NAME));
		case UPDATE:
			return vehicleManageService.modelUpdate(request.getParam(Params.ID), request.getParam(Params.NAME));
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
