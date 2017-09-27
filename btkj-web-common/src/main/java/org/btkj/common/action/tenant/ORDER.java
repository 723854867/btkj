package org.btkj.common.action.tenant;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.VehicleUnitType;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.param.VehicleOrderParam;
import org.btkj.pojo.param.VehicleOrderParam.InsuranceItem;
import org.btkj.pojo.param.VehicleOrderParam.Unit;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 车险下单：包括报价、投保、报价并投保三种方式
 * 
 * @author ahab
 */
public class ORDER extends EmployeeAction<VehicleOrderParam> {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(App app, User user, Tenant tenant, Employee employee, VehicleOrderParam param) {
		Result<Void> result = _check(param);
		if (!result.isSuccess())
			return result;
		return vehicleService.order(app, tenant, user, employee, param);
	}
	
	/**
	 * 只检查参数的基本合法性比如是否为null，是否为空等等，不会检验参数是否符合保险规则
	 * 
	 * @param submit
	 */
	private Result<Void> _check(VehicleOrderParam param) {
		VehicleUsedType usedType = param.getUsedType();
		Result<Void> result = _checkInsurUnit(param.getOwner(), usedType, true);
		if (!result.isSuccess())
			return result;
		result = _checkInsurUnit(param.getInsurer(), usedType, false);
		if (!result.isSuccess())
			return result;
		result = _checkInsurUnit(param.getInsured(), usedType, false);
		return result.isSuccess() ? _checkSchema(param) : result;
	}
	
	private Result<Void> _checkInsurUnit(Unit unit, VehicleUsedType usedType, boolean owner) {
		if (owner) {								// 车主需要修正类型
			switch (usedType) {
			case HOME_USE:
				unit.setType(VehicleUnitType.PERSONAL);
				break;
			case ORGAN:
				unit.setType(VehicleUnitType.OFFICE);
				break;
			case ENTERPRISE:
			case CITY_BUS:
			case HIGHWAY_TRANSPORT:
			case PARTICULAR:
				unit.setType(VehicleUnitType.ENTERPRISE);
				break;
			default:
				break;
			}
		}
		if ((unit.getIdType().unitMod() & unit.getType().mark()) != unit.getType().mark())
			return BtkjConsts.RESULT.ID_TYPE_UNSUITABLE_TO_UNIT_TYPE;
		if (!unit.getIdType().check(unit.getIdNo()))
			return Consts.RESULT.ERROR_ID_NO;
		return Consts.RESULT.OK;
	}
	
	private Result<Void> _checkSchema(VehicleOrderParam param) {
		String cpi = param.getCompulsoryStart();
		String cmi = param.getCommercialStart();
		if (null == cpi && null == cmi)
			return BtkjConsts.RESULT.ERROR_INSURANCE_SCHEMA;
		if (null != cmi) {
			Map<CommercialInsuranceType, InsuranceItem> map = param.getInsurances();
			map.remove(null);
			if (CollectionUtil.isEmpty(map))
				return BtkjConsts.RESULT.ERROR_INSURANCE_SCHEMA;
			int mod = 0;
			for (CommercialInsuranceType type : map.keySet()) 
				mod |= type.mark();
			for (Entry<CommercialInsuranceType, InsuranceItem> entry : map.entrySet()) {
				int need = entry.getKey().need();
				if ((mod & need) != need)
					return BtkjConsts.RESULT.ERROR_INSURANCE_SCHEMA;
			}
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
