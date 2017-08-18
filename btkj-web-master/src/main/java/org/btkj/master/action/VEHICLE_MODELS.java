package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.pojo.param.IdParam;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;

public class VEHICLE_MODELS extends AdminAction<IdParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<VehicleModel>> execute(Administrator admin, IdParam param) {
		return Result.result(vehicleManageService.models(param.getId()));
	}
}
