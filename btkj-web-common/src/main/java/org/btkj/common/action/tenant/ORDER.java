package org.btkj.common.action.tenant;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.btkj.pojo.bo.InsurUnit;
import org.btkj.pojo.bo.Insurance;
import org.btkj.pojo.bo.PolicySchema;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.UnitType;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.vo.VehiclePolicyTips;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.validator.Validator;

/**
 * 车险下单：包括报价、投保、报价并投保三种方式
 * 
 * @author ahab
 */
public class ORDER extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(Request request, Employee employee) {
		int quoteGroup = request.getParam(Params.QUOTE_GROUP);
		int insureGroup = request.getOptionalParam(Params.INSURE_GROUP);
		String vehicleId = request.getParam(Params.VEHICLE_ID);
		if (quoteGroup <= 0 || insureGroup < 0 
				|| Integer.bitCount(quoteGroup) > GlobalConfigContainer.getGlobalConfig().getMaxQuoteNum()
				|| Integer.bitCount(insureGroup) > GlobalConfigContainer.getGlobalConfig().getMaxInsureNum())
			return Consts.RESULT.FORBID;
		VehiclePolicyTips tips = request.getParam(Params.VEHICLE_POLICY_TIPS);
		if (!_check(employee.getTenant(), tips))
			throw ConstConvertFailureException.errorConstException(Params.VEHICLE_POLICY_TIPS);
		return vehicleService.order(quoteGroup, insureGroup, employee, tips, vehicleId);
	}
	
	/**
	 * 只检查参数的基本合法性比如是否为null，是否为空等等，不会检验参数是否符合保险规则
	 * 
	 * @param submit
	 */
	private boolean _check(TenantPO tenant, VehiclePolicyTips tips) {
		if (null == tips.getOwner() || null == tips.getInsurer() || null == tips.getInsured() || null == tips.getSchema())
			return false;
		VehicleUsedType usedType = tips.getVehicleUsedType();
		if (null == usedType)
			return false;
		return _checkInsurUnit(tips.getOwner(), usedType, true)
				&& _checkInsurUnit(tips.getInsurer(), usedType, false)
				&& _checkInsurUnit(tips.getInsured(), usedType, false)
				&& _checkVehicle(tips)
				&& _checkSchema(tips.getSchema());
	}
	
	private boolean _checkInsurUnit(InsurUnit unit, VehicleUsedType usedType, boolean owner) {
		if (null == unit.getIdType() || null == unit.getIdNo() || null == unit.getName() || null == unit.getType())
			return false;
		if (null != unit.getMobile() && !Validator.isMobile(unit.getMobile(), Locale.CHINA.getCountry()))
			return false;
		if (owner)
			_correctUnitType(usedType, unit);
		if ((unit.getIdType().unitMod() & unit.getType().mark()) != unit.getType().mark())
			return false;
		if (!unit.getIdType().check(unit.getIdNo()))
			return false;
		return true;
	}
	
	private boolean _checkVehicle(VehiclePolicyTips tips) {
		if (!StringUtil.hasText(
				tips.getEngine(), tips.getLicense(), 
				tips.getVin(), tips.getEnrollDate()))
			return false;
		if (null == tips.getVehicleUsedType())
			return false;
		return Validator.isVehicleLisense(tips.getLicense());
	}
	
	private boolean _checkSchema(PolicySchema schema) {
		String cpi = schema.getCompulsiveStart();
		String cmi = schema.getCommercialStart();
		if (null == cpi && null == cmi)
			return false;
		if (null != cmi) {
			Map<CommercialInsuranceType, Insurance> map = schema.getInsurances();
			if (null == map)
				return false;
			map.remove(null);
			int mod = 0;
			for (CommercialInsuranceType type : map.keySet()) 
				mod |= type.mark();
			for (Entry<CommercialInsuranceType, Insurance> entry : map.entrySet()) {
				int need = entry.getKey().need();
				if ((mod & need) != need)
					return false;
			}
		}
		return true;
	}
	
	private boolean _correctUnitType(VehicleUsedType usedType, InsurUnit unit) {
		switch (usedType) {
		case HOME_USE:
			unit.setType(UnitType.PERSONAL);
			break;
		case ORGAN:
			unit.setType(UnitType.OFFICE);
			break;
		case ENTERPRISE:
		case CITY_BUS:
		case HIGHWAY_TRANSPORT:
		case PARTICULAR:
			unit.setType(UnitType.ENTERPRISE);
			break;
		case BIZ_TRUCK:
		case NO_BIZ_TRUCK:
		case LEASE:
			if (unit.getType() == UnitType.OFFICE)
				return false;
			break;
		default:
			break;
		}
		return true;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
