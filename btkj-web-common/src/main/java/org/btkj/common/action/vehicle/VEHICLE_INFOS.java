package org.btkj.common.action.vehicle;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.vo.VehicleInfo;
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
		return Result.result(vehicleService.vehicleInfos(request.getParam(Params.VIN)));
	}
}
