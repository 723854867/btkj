package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.pojo.po.Administrator;
import org.btkj.pojo.po.VehicleDept;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_DEPTS extends LoggedAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<VehicleDept>> execute(Request request, Administrator operator) {
		return Result.result(vehicleManageService.depts(request.getParam(Params.ID)));
	}
}
