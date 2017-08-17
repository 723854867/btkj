package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.NilParam;
import org.btkj.pojo.po.VehicleBrand;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;

public class VEHICLE_BRANDS extends AdminAction<NilParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<VehicleBrand>> execute(Administrator admin, NilParam param) {
		return Result.result(vehicleManageService.brands());
	}
}
