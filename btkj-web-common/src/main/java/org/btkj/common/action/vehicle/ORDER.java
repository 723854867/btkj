package org.btkj.common.action.vehicle;

import javax.annotation.Resource;

import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.InsurUnit;
import org.btkj.pojo.submit.VehicleOrderSubmit;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.Validator;
import org.rapid.util.common.message.Result;

/**
 * 车险下单：包括报价、投保、报价并投保三种方式
 * 
 * @author ahab
 */
public class ORDER extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(Request request, Client client, EmployeeForm employeeForm) {
		VehicleOrderSubmit submit = request.getParam(Params.VEHICLE_ORDER_SUBMIT);
		_check(employeeForm.getTenant(), submit);
		return vehicleService.order(employeeForm, submit);
	}
	
	/**
	 * 对投保参数做一个基本的检测
	 * 
	 * @param submit
	 */
	private boolean _check(Tenant tenant, VehicleOrderSubmit submit) {
		int quoteMod = submit.getQuoteMod();
		int insureMod = submit.getInsureMod();
		int quoteBitCount = Integer.bitCount(quoteMod);
		int insurBitCount = Integer.bitCount(insureMod);
		if (0 == quoteMod || (quoteMod & insureMod) != insureMod
				|| (tenant.getInsurerMod() & quoteMod) != quoteMod
				|| (GlobalConfigContainer.getGlobalConfig().getMaxQuoteNum() < quoteBitCount)
				|| (GlobalConfigContainer.getGlobalConfig().getMaxInsurNum() < insurBitCount))
			return false;
		
		Renewal renewal = submit.getRenewal();
		if (null == renewal || null == renewal.getOwner() || null == renewal.getInsurer() 
				|| null == renewal.getInsured() || null == renewal.getVehicle() || null == renewal.getSchema())
			return false;
		_checkInsurUnit(renewal.getOwner());
		_checkInsurUnit(renewal.getInsurer());
		_checkInsurUnit(renewal.getInsured());
		return true;
	}
	
	private boolean _checkInsurUnit(InsurUnit unit) {
		if (null == unit.getIdType() || null == unit.getIdNo() || null == unit.getName())
			return false;
		if (null != unit.getMobile() && !Validator.isMobile(unit.getMobile()))
			return false;
		if (!unit.getIdType().check(unit.getIdNo()))
			return false;
		return true;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
