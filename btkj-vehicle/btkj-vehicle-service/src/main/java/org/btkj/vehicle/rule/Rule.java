package org.btkj.vehicle.rule;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.btkj.vehicle.pojo.entity.City;
import org.btkj.vehicle.redis.CityMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.consts.code.ICode;
import org.rapid.util.lang.DateUtils;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.springframework.stereotype.Component;

@Component("rule")
public class Rule {

	@Resource
	private CityMapper cityMapper;
	
	/**
	 * 检查投保规则
	 * 
	 * @param order
	 * @return
	 */
	public ICode orderCheck(int cityCode, PolicySchema schema) {
		// 检查起保时间
		if (null != schema.getCommercialStart()) {
			ICode code = _checkRenewalPeriod(cityCode, schema.getCommercialStart());
			if (code != Code.OK)
				return code;
		}
		if (null != schema.getCompulsiveStart()) {
			ICode code = _checkRenewalPeriod(cityCode, schema.getCompulsiveStart());
			if (code != Code.OK)
				return code;
		}
		return Code.OK;
	}
	
	/**
	 * 检查续保期限
	 * 
	 * @return
	 */
	private ICode _checkRenewalPeriod(int cityCode, String startTime) {
		City city = cityMapper.getByKey(cityCode);
		if (null == city)
			return BtkjCode.CITY_UNSUPPORT;
		int renewalInterval = city.getRenewalPeriod() * 24 * 3600;
		int timestamp = (int) (DateUtils.getTime(startTime, DateUtils.YYYY_MM_DD_HH_MM_SS) / 1000);
		int maxTime = DateUtils.currentTime() + renewalInterval;
		if (timestamp > maxTime || timestamp < DateUtils.currentTime())
			return BtkjCode.NOT_IN_RENEWAL_PERIOD;
		return Code.OK;
	}
	
	public String processCoefficientComparisonValue(VehicleCoefficient coefficient, ComparisonSymbol symbol, String[] value) {
//		ComparisonSymbol cSymbol = ComparisonSymbol.match(coefficient.getComparison());
//		switch (cSymbol) {
//		case GREATER_THAN:
//			if (symbol == ComparisonSymbol.BETWEEN || symbol == ComparisonSymbol.LE_BETWEEN || symbol == ComparisonSymbol.RE_BETWEEN)
//				_checlRange(value);
//			switch (symbol) {
//			case EQUAL:
//			case LESS_THAN:
//			case LESS_THAN_OR_EQUAL:
//				if (Integer.valueOf(value[0]) <= Integer.valueOf(coefficient.getComparableValue()))
//					return value[0];
//				return null;
//			case BETWEEN:
//			case LE_BETWEEN:
//			case RE_BETWEEN:
//				if (Integer.valueOf(value[1]) <= Integer.valueOf(coefficient.getComparableValue()))
//					return value[0] + Consts.SYMBOL_UNDERLINE +  value[1];
//				return null;
//			default:
//				return null;
//			}
//		case GREATER_THAN_OR_EQUAL:
//			switch (symbol) {
//			case LESS_THAN:
//				if (Integer.valueOf(value[0]) <= Integer.valueOf(coefficient.getComparableValue()))
//					return value[0];
//				return null;
//			case EQUAL:
//			case LESS_THAN_OR_EQUAL:
//				if (Integer.valueOf(value[0]) < Integer.valueOf(coefficient.getComparableValue()))
//					return value[0];
//				return null;
//			case BETWEEN:
//			case LE_BETWEEN:
//				if (Integer.valueOf(value[1]) <= Integer.valueOf(coefficient.getComparableValue()))
//					return value[0] + Consts.SYMBOL_UNDERLINE +  value[1];
//				return null;
//			case RE_BETWEEN:
//				if (Integer.valueOf(value[1]) < Integer.valueOf(coefficient.getComparableValue()))
//					return value[0] + Consts.SYMBOL_UNDERLINE +  value[1];
//				return null;
//			default:
//				return null;
//			}
//		case LESS_THAN:
//			switch (symbol) {
//			case EQUAL:
//			case GREATER_THAN:
//			case GREATER_THAN_OR_EQUAL:
//				if (Integer.valueOf(value[0]) >= Integer.valueOf(coefficient.getComparableValue()))
//					return value[0];
//				return null;
//			case BETWEEN:
//			case LE_BETWEEN:
//			case RE_BETWEEN:
//				if (Integer.valueOf(value[0]) >= Integer.valueOf(coefficient.getComparableValue()))
//					return value[0] + Consts.SYMBOL_UNDERLINE +  value[1];
//				return null;
//			default:
//				return null;
//			}
//		case LESS_THAN_OR_EQUAL:
//			switch (symbol) {
//			case EQUAL:
//			case LE_BETWEEN:
//			case GREATER_THAN_OR_EQUAL:
//				return Integer.valueOf(value[0]) > Integer.valueOf(coefficient.getComparableValue());
//			case BETWEEN:
//			case RE_BETWEEN:
//			case GREATER_THAN:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(coefficient.getComparableValue());
//			default:
//				return false;
//			}
//		case EQUAL:
//			CoefficientType coefficientType = CoefficientType.match(coefficient.getType());
//			switch (coefficientType) {
//			case LICENSE:
//				switch (symbol) {
//				case EQUAL:
//					return !value[0].equals(coefficient.getComparableValue());
//				default:
//					return false;
//				}
//			default:
//				switch (symbol) {
//				case GREATER_THAN:
//				case GREATER_THAN_OR_EQUAL:
//					return Integer.valueOf(value[0]) > Integer.valueOf(coefficient.getComparableValue());
//				case LESS_THAN:
//				case LESS_THAN_OR_EQUAL:
//					return Integer.valueOf(value[0]) < Integer.valueOf(coefficient.getComparableValue());
//				case EQUAL:
//					return Integer.valueOf(value[0]) != Integer.valueOf(coefficient.getComparableValue());
//				case BETWEEN:
//					return Integer.valueOf(value[0]) >= Integer.valueOf(coefficient.getComparableValue())
//							|| Integer.valueOf(value[1]) <= Integer.valueOf(coefficient.getComparableValue());
//				case LE_BETWEEN:
//					return Integer.valueOf(value[0]) > Integer.valueOf(coefficient.getComparableValue())
//							|| Integer.valueOf(value[1]) <= Integer.valueOf(coefficient.getComparableValue());
//				case RE_BETWEEN:
//					return Integer.valueOf(value[0]) >= Integer.valueOf(coefficient.getComparableValue())
//						|| Integer.valueOf(value[1]) < Integer.valueOf(coefficient.getComparableValue());
//				default:
//					return false;
//				}
//			}
//		case BETWEEN:
//			String[] carr = coefficient.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
//			switch (symbol) {
//			case GREATER_THAN:
//			case GREATER_THAN_OR_EQUAL:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(carr[1]);
//			case LESS_THAN:
//			case LESS_THAN_OR_EQUAL:
//				return Integer.valueOf(value[0]) <= Integer.valueOf(carr[0]);
//			case EQUAL:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(carr[1])
//					|| Integer.valueOf(value[0]) <= Integer.valueOf(carr[0]);
//			case BETWEEN:
//			case LE_BETWEEN:
//			case RE_BETWEEN:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(carr[1])
//					|| Integer.valueOf(value[1]) <= Integer.valueOf(carr[0]);
//			default:
//				return false;
//			}
//		case LE_BETWEEN:
//			carr = coefficient.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
//			switch (symbol) {
//			case GREATER_THAN:
//			case GREATER_THAN_OR_EQUAL:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(carr[1]);
//			case LESS_THAN:
//				return Integer.valueOf(value[0]) <= Integer.valueOf(carr[0]);
//			case LESS_THAN_OR_EQUAL:
//				return Integer.valueOf(value[0]) < Integer.valueOf(carr[0]);
//			case EQUAL:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(carr[1])
//					|| Integer.valueOf(value[0]) < Integer.valueOf(carr[0]);
//			case BETWEEN:
//			case LE_BETWEEN:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(carr[1])
//					|| Integer.valueOf(value[1]) <= Integer.valueOf(carr[0]);
//			case RE_BETWEEN:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(carr[1])
//					|| Integer.valueOf(value[1]) < Integer.valueOf(carr[0]);
//			default:
//				return false;
//			}
//		case RE_BETWEEN:
//			carr = coefficient.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
//			switch (symbol) {
//			case GREATER_THAN:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(carr[1]);
//			case GREATER_THAN_OR_EQUAL:
//				return Integer.valueOf(value[0]) > Integer.valueOf(carr[1]);
//			case LESS_THAN:
//			case LESS_THAN_OR_EQUAL:
//				return Integer.valueOf(value[0]) <= Integer.valueOf(carr[0]);
//			case EQUAL:
//				return Integer.valueOf(value[0]) > Integer.valueOf(carr[1])
//						|| Integer.valueOf(value[0]) <= Integer.valueOf(carr[0]);
//			case BETWEEN:
//			case RE_BETWEEN:
//				return Integer.valueOf(value[0]) >= Integer.valueOf(carr[1])
//						|| Integer.valueOf(value[1]) <= Integer.valueOf(carr[0]);
//			case LE_BETWEEN:
//				return Integer.valueOf(value[0]) > Integer.valueOf(carr[1])
//						|| Integer.valueOf(value[1]) <= Integer.valueOf(carr[0]);
//			default:
//				return false;
//			}
//		default:
//			return false;
//		}
		return null;
	}
	
	private boolean _checlRange(String[] value) {
		return Integer.valueOf(value[0]) < Integer.valueOf(value[1]);
	}
}
