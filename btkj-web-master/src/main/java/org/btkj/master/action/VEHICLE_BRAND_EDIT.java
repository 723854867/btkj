package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.param.VehicleBrandEditParam;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class VEHICLE_BRAND_EDIT extends AdminAction<VehicleBrandEditParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	public VEHICLE_BRAND_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE);
	}

	@Override
	protected Result<?> execute(Admin admin, VehicleBrandEditParam param) {
		return vehicleManageService.brandEdit(param);
	}
}
