package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.param.VehicleModelEditParam;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class VEHICLE_MODEL_EDIT extends AdminAction<VehicleModelEditParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	public VEHICLE_MODEL_EDIT() {
		super(CrudType.CREATE, CrudType.DELETE);
	}

	@Override
	protected Result<?> execute(Admin admin, VehicleModelEditParam param) {
		return vehicleManageService.modelEdit(param);
	}
}
