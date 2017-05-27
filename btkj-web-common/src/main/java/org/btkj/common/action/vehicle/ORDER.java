package org.btkj.common.action.vehicle;

import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.CommercialInsurance;
import org.btkj.pojo.model.insur.vehicle.CompulsiveInsurance;
import org.btkj.pojo.model.insur.vehicle.InsurUnit;
import org.btkj.pojo.model.insur.vehicle.InsuranceSchema;
import org.btkj.pojo.model.insur.vehicle.Vehicle;
import org.btkj.pojo.submit.VehicleOrderSubmit;
import org.btkj.vehicle.api.RuleService;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.Validator;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.CollectionUtils;
import org.rapid.util.lang.StringUtils;

/**
 * 车险下单：包括报价、投保、报价并投保三种方式
 * 
 * @author ahab
 */
public class ORDER extends TenantAction {
	
	@Resource
	private RuleService ruleService;
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(Request request, Client client, EmployeeForm employeeForm) {
		VehicleOrderSubmit submit = request.getParam(Params.VEHICLE_ORDER_SUBMIT);
		if (!_check(employeeForm.getTenant(), submit) || !ruleService.check(employeeForm, submit))
			throw ConstConvertFailureException.errorConstException(Params.VEHICLE_ORDER_SUBMIT);
		return vehicleService.order(employeeForm, submit);
	}
	
	/**
	 * 只检查参数的基本合法性比如是否为null，是否为空等等，不会检验参数是否符合保险规则
	 * 
	 * @param submit
	 */
	private boolean _check(Tenant tenant, VehicleOrderSubmit submit) {
		Set<Integer> quote = submit.getQuote();
		Set<Integer> insure = submit.getInsure();
		if (!CollectionUtils.isSubSet(quote, insure))
			return false;
		int quoteNum = null == quote ? 0 : quote.size();
		int insureNum = null == insure ? 0 : insure.size();
		if (0 == quoteNum 
				|| GlobalConfigContainer.getGlobalConfig().getMaxQuoteNum() < quoteNum
				|| GlobalConfigContainer.getGlobalConfig().getMaxInsurNum() < insureNum)
			return false;
		
		Renewal renewal = submit.getRenewal();
		if (null == renewal || null == renewal.getOwner() || null == renewal.getInsurer() 
				|| null == renewal.getInsured() || null == renewal.getVehicle() || null == renewal.getSchema())
			return false;
		return _checkInsurUnit(renewal.getOwner())
				&& _checkInsurUnit(renewal.getInsurer())
				&& _checkInsurUnit(renewal.getInsured())
				&& _checkVehicle(renewal.getVehicle())
				&& _checkSchema(renewal.getSchema());
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
	
	private boolean _checkVehicle(Vehicle vehicle) {
		if (!StringUtils.hasText(
				vehicle.getEngine(), vehicle.getLicense(), 
				vehicle.getModel(), vehicle.getVin(), 
				vehicle.getEnrollDate()))
			return false;
		return Validator.isVehicleLisense(vehicle.getLicense());
	}
	
	private boolean _checkSchema(InsuranceSchema schema) {
		CompulsiveInsurance cpi = schema.getCompulsive();
		CommercialInsurance cmi = schema.getCommercial();
		if (null == cpi && null == cmi)
			return false;
		if (null != cpi && null == cpi.getStart())  		// 交强险必须指定起保日期 
			return false;
		if (null != cmi) {
			// 必须要有起保日期
			if (null == cmi.getStart())
				return false;
			// 确保至少保了一种基本险
			if (null == cmi.getDamage() && null == cmi.getThird() && null == cmi.getDriver()
					&& null == cmi.getPassenger() && null == cmi.getRobbery())
				return false;
		}
		return true;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
