package org.btkj.common.action.vehicle;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.courier.model.QuotaNoticeSubmit;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class QUOTA_NOTICE extends TenantAction {
	
	@Resource
	private CourierService courierService;
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(Request request, Client client, EmployeeForm ef) {
		QuotaNoticeSubmit submit = request.getParam(Params.QUOTA_NOTICE_SUBMIT);
		Result<VehicleOrder> result = vehicleService.orderInfo(ef, request.getParam(Params.ORDER_ID));
		if (!result.isSuccess())
			return result;
		VehicleOrder order = result.attach();
		if (order.getEmployeeId() != ef.getEmployee().getId())
			return Consts.RESULT.FORBID;
		if (order.getState().mark() < VehicleOrderState.QUOTE_SUCCESS.mark())			// 只有报价成功才可以转发
			return BtkjConsts.RESULT.ORDER_STATE_ERROR;
		courierService.quotaNotice(result.attach(), submit);
		return Consts.RESULT.OK;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
