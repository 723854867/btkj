package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.param.QuoteNoticeParam;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class QUOTE_NOTICE extends EmployeeAction<QuoteNoticeParam> {
	
	@Resource
	private CourierService courierService;
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, QuoteNoticeParam param) {
		Result<VehicleOrder> result = vehicleService.orderInfo(tenant, employee, param.getOrderId());
		if (!result.isSuccess())
			return result;
		VehicleOrder order = result.attach();
		if (order.getEmployeeId() != employee.getId())
			return Consts.RESULT.FORBID;
		if (order.getState().mark() < VehicleOrderState.QUOTE_SUCCESS.mark())			// 只有报价成功才可以转发
			return BtkjConsts.RESULT.ORDER_STATE_ERROR;
		
		param.setCommercialRate(Math.max(0, param.getCommercialRate()));
		param.setCommercialRate(Math.min(100, param.getCommercialRate()));
		param.setCompulsoryRate(Math.max(0, param.getCompulsoryRate()));
		param.setCompulsoryRate(Math.min(100, param.getCompulsoryRate()));
		if (null == param.getAgentMobile())
			param.setAgentMobile(user.getMobile());
		if (null == param.getAgentName())
			param.setAgentName(user.getName());
		courierService.quotaNotice(result.attach(), param);
		return Consts.RESULT.OK;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
