package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.pojo.entity.VehicleDept;
import org.btkj.pojo.param.IdParam;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;

public class VEHICLE_DEPTS extends AdminAction<IdParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<VehicleDept>> execute(Admin admin, IdParam param) {
		return Result.result(vehicleManageService.depts(param.getId()));
	}
}
