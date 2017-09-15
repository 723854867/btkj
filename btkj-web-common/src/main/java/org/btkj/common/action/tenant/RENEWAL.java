package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.RenewalParam;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.vehicle.Renewal;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

/**
 * 获取续保信息接口
 * 
 * @author ahab
 */
public class RENEWAL extends EmployeeAction<RenewalParam> {
	
	@Resource
	private VehicleService vehicleService;
	
	@Override
	protected Result<?> execute(App app, User user, Tenant tenant, Employee employee, RenewalParam param) {
		Result<Renewal> result = null;
		switch (param.getType()) {
		case 1:									// 根据车架号和发动机号获取
			if (null == param.getVin() || null == param.getEngine())
				return Consts.RESULT.FORBID;
			result = vehicleService.renewal(user, tenant, employee, param.getVin(), param.getEngine(), param.getName());
			break;
		default:								// 默认根据车牌获取
			if (null == param.getLicense())
				return Consts.RESULT.FORBID;
			result = vehicleService.renewal(user, tenant, employee, param.getLicense(), param.getName());
		}
		return result;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
