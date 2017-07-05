package org.btkj.master.action.vehicle.baotu;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Administrator;
import org.btkj.vehicle.api.VehicleConfigService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class CITY_DELETE extends AdministratorAction {
	
	@Resource
	private VehicleConfigService vehicleConfigService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		vehicleConfigService.deleteCity(request.getParam(Params.REGION));
		return Consts.RESULT.OK;
	}
}