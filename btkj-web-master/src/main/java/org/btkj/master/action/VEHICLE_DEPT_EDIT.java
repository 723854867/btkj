package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.param.VehicleDeptEditParam;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class VEHICLE_DEPT_EDIT extends AdminAction<VehicleDeptEditParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	public VEHICLE_DEPT_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE);
	}

	@Override
	protected Result<?> execute(Admin admin, VehicleDeptEditParam param) {
		return vehicleManageService.deptEdit(param);
	}
}
