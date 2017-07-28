package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.po.VehicleBrand;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_BRANDS extends LoggedAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<VehicleBrand>> execute(Request request, Administrator operator) {
		return Result.result(vehicleManageService.brands());
	}
}
