package org.btkj.master.action.vehicle.baotu;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.api.TenantService;
import org.btkj.vehicle.api.VehicleConfigService;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class ROUTE_ADD extends AdministratorAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private TenantService tenantService;
	@Resource
	private VehicleConfigService vehicleConfigService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		int tid = request.getParam(Params.TID);
		Tenant tenant = tenantService.getTenantById(tid);
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		Lane lane = Lane.match(request.getParam(Params.ID));
		if (null == lane)
			return BtkjConsts.RESULT.LANE_NOT_EXIST;
		Insurer insurer = configService.getInsurerById(request.getParam(Params.TID));
		if (null == insurer)
			return BtkjConsts.RESULT.INSURER_NOT_EXIST;
		vehicleConfigService.addRoute(tid, insurer.getId(), lane);
		return Consts.RESULT.OK;
	}
}
