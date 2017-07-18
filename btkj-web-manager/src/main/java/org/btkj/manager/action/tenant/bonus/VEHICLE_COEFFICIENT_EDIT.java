package org.btkj.manager.action.tenant.bonus;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class VEHICLE_COEFFICIENT_EDIT extends TenantAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(Request request, Employee employee) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		CoefficientType type = request.getParam(Params.COEFFICIENT_TYPE);
		if (!type.isCustom())
			return Consts.RESULT.FORBID;
		int tid = employee.getTid();
		switch (crudType) {
		case DELETE:
			return vehicleManageService.coefficientDelete(tid, type, request.getParam(Params.ID));
		case UPDATE:
			return vehicleManageService.coefficientUpdate(tid, type, request.getParam(Params.ID), request.getParam(Params.COMPARISON), request.getParam(Params.ARRAY), request.getParam(Params.NAME));
		case CREATE:
			return vehicleManageService.coefficientAdd(tid, type, request.getParam(Params.COMPARISON), request.getParam(Params.ARRAY), request.getParam(Params.NAME));
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
