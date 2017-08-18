package org.btkj.common.action.tenant;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 根据车架号获取车辆信息 
 * 
 * @author ahab
 */
public class VEHICLE_INFOS extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<List<VehicleInfo>> execute(Request request, Employee employee) {
		return Result.result(vehicleService.vehicleInfos(employee.getTenant(), request.getParam(Params.VIN)));
	}
}
