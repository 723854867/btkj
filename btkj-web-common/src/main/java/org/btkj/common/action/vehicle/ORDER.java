package org.btkj.common.action.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.UnitType;
import org.btkj.pojo.enums.vehicle.VehicleUsedType;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.InsurUnit;
import org.btkj.pojo.model.insur.vehicle.Insurance;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.CollectionUtils;
import org.rapid.util.lang.StringUtils;
import org.rapid.util.validator.Validator;

/**
 * 车险下单：包括报价、投保、报价并投保三种方式
 * 
 * @author ahab
 */
public class ORDER extends TenantAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(Request request, Client client, EmployeeForm employeeForm) {
		Set<Integer> quote = request.getParam(Params.QUOTE_GROUP);
		Set<Integer> insure = request.getOptionalParam(Params.INSURE_GROUP);
		String vehicleId = request.getParam(Params.VEHICLE_ID);
		if (!_checkInsurer(quote, insure))
			return Consts.RESULT.FORBID;
		List<Insurer> quoteInsurer = configService.insurers(new ArrayList<Integer>(quote));
		if (quoteInsurer.size() != quote.size())
			return Consts.RESULT.FORBID;
		
		List<Insurer> insureInsurer = null;
		if (!CollectionUtils.isEmpty(insure)) {
			insureInsurer = new ArrayList<Insurer>(insure.size());
			for (int insurerId : insure) {
				for (Insurer insurer : quoteInsurer) {
					if (insurer.getId() == insurerId)
						insureInsurer.add(insurer);
				}
			}
		}
		VehiclePolicyTips tips = request.getParam(Params.VEHICLE_POLICY_TIPS);
		if (!_check(employeeForm.getTenant(), tips))
			throw ConstConvertFailureException.errorConstException(Params.VEHICLE_POLICY_TIPS);
		return vehicleService.order(quoteInsurer, insureInsurer, employeeForm, tips, vehicleId);
	}
	
	private boolean _checkInsurer(Set<Integer> quote, Set<Integer> insure) {
		if (!CollectionUtils.isSubSet(quote, insure))
			return false;
		int quoteNum = null == quote ? 0 : quote.size();
		int insureNum = null == insure ? 0 : insure.size();
		if (0 == quoteNum 
				|| GlobalConfigContainer.getGlobalConfig().getMaxQuoteNum() < quoteNum
				|| GlobalConfigContainer.getGlobalConfig().getMaxInsurNum() < insureNum)
			return false;
		return true;
	}
	
	/**
	 * 只检查参数的基本合法性比如是否为null，是否为空等等，不会检验参数是否符合保险规则
	 * 
	 * @param submit
	 */
	private boolean _check(Tenant tenant, VehiclePolicyTips tips) {
		if (null == tips.getOwner() || null == tips.getInsurer() || null == tips.getInsured() || null == tips.getSchema())
			return false;
		VehicleUsedType usedType = tips.getVehicleUsedType();
		if (null == usedType)
			return false;
		return _checkInsurUnit(tips.getOwner(), usedType)
				&& _checkInsurUnit(tips.getInsurer(), usedType)
				&& _checkInsurUnit(tips.getInsured(), usedType)
				&& _checkVehicle(tips)
				&& _checkSchema(tips.getSchema());
	}
	
	private boolean _checkInsurUnit(InsurUnit unit, VehicleUsedType usedType) {
		if (null == unit.getIdType() || null == unit.getIdNo() || null == unit.getName())
			return false;
		if (null != unit.getMobile() && !Validator.isMobile(unit.getMobile(), Locale.CHINA.getCountry()))
			return false;
		if (!unit.getIdType().check(unit.getIdNo()))
			return false;
		_correctUnitType(usedType, unit);
		return true;
	}
	
	private boolean _checkVehicle(VehiclePolicyTips tips) {
		if (!StringUtils.hasText(
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
			Map<InsuranceType, Insurance> map = schema.getInsurances();
			if (null == map)
				return false;
			map.remove(null);
			int mod = 0;
			for (InsuranceType type : map.keySet()) 
				mod |= type.mark();
			for (Entry<InsuranceType, Insurance> entry : map.entrySet()) {
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
