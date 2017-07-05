package org.btkj.master.action.vehicle.baotu;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.api.TenantService;
import org.btkj.vehicle.api.VehicleConfigService;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class ROUTES extends AdministratorAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private VehicleConfigService vehicleConfigService;

	@Override
	protected Result<List<Route>> execute(Request request, Administrator operator) {
		int tid = request.getParam(Params.TID);
		Tenant tenant = tenantService.getTenantById(tid);
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		return Result.result(vehicleConfigService.routes(tenant));
	}
}
