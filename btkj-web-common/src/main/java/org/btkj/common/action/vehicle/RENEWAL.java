package org.btkj.common.action.vehicle;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 获取续保信息接口
 * 
 * @author ahab
 */
public class RENEWAL extends TenantAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;
	
	@Override
	protected Result<Renewal> execute(Request request, Client client, EmployeeForm employeeForm) {
		String name = request.getParam(Params.NAME);
		int type = request.getOptionalParam(Params.TYPE);
		Result<Renewal> result = null;
		switch (type) {
		case 1:									// 根据车架号和发动机号获取
			String vin = request.getParam(Params.VIN);
			String engine = request.getParam(Params.ENGINE);
			result = vehicleService.renewal(employeeForm, vin, engine, name);
		default:								// 默认根据车牌获取
			String license = request.getParam(Params.LICENSE);
			result = vehicleService.renewal(employeeForm, license, name);
		}
		if (result.isSuccess() && 0 != result.attach().getInsurerId()) {
			Renewal renewal = result.attach();
			Insurer insurer = configService.getInsurerById(renewal.getInsurerId());
			if (null != insurer) {
				renewal.setInsurerName(insurer.getName());
				renewal.setInsurerIcon(insurer.getIcon());
			}
		}
		return result;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
