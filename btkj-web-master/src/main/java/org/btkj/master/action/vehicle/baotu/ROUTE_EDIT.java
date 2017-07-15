package org.btkj.master.action.vehicle.baotu;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.api.TenantService;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class ROUTE_EDIT extends AdministratorAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			Tenant tenant = tenantService.getTenantById(request.getParam(Params.TID));
			if (null == tenant)
				return BtkjConsts.RESULT.TENANT_NOT_EXIST;
			Insurer insurer = configService.getInsurerById(request.getParam(Params.ID));
			if (null == insurer)
				return BtkjConsts.RESULT.INSURER_NOT_EXIST;
			return vehicleManageService.routeAdd(tenant.getTid(), insurer.getId(), request.getParam(Params.LANE));
		case UPDATE:
			return vehicleManageService.routeUpdate(request.getParam(Params.KEY), request.getParam(Params.LANE));
		case DELETE:
			vehicleManageService.routeDelete(request.getParam(Params.KEY));
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
}