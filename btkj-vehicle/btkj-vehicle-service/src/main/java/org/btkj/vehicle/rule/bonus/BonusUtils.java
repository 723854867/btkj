package org.btkj.vehicle.rule.bonus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.enums.VehicleBizType;
import org.btkj.vehicle.pojo.model.VehicleCoefficientType;
import org.btkj.vehicle.pojo.param.BonusPoundageEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientsParam;

public class BonusUtils {

	/**
	 * 非营利客车商业险系数检查
	 * 
	 * @param searcher
	 * @param coefficients
	 * @return
	 */
	public static Map<Integer, Integer> noProfitCoacheCommercialSpinnerCheck(BonusPoundageEditParam param, List<VehicleCoefficient> coefficients) { 
		Map<Integer, Integer> spinner = param.getRouteBody().getCommercialCommisionSpinner();
		if (param.getBizType() == VehicleBizType.NO_PROFIT && null != spinner) {// 非营利客车
			Iterator<Entry<Integer, Integer>> iterator = spinner.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Integer, Integer> entry = iterator.next();
				boolean find = false;
				for (VehicleCoefficient coefficient : coefficients) {
					if (coefficient.getId() != entry.getKey())
						continue;
					find = true;
				}
				if (!find)
					iterator.remove();
			}
			return spinner.isEmpty() ? null : spinner;
		} else 
			return null;
	}
	
	/**
	 * 非营利客车配置系数
	 * 
	 * @param searcher
	 * @param coefficients
	 * @return
	 */
	public static List<VehicleCoefficientType> noProfitCoacheCommercialCoefficients(List<VehicleCoefficient> coefficients, Map<Integer, Integer> spinner, PoundageCoefficientsParam param) { 
		if (param.getBizType() == VehicleBizType.NO_PROFIT) {			// 非营利客车
			List<VehicleCoefficientType> list = new ArrayList<VehicleCoefficientType>();
			for (CoefficientType type : CoefficientType.values()) {
				VehicleCoefficientType info = new VehicleCoefficientType(type);
				list.add(info);
				
				Iterator<VehicleCoefficient> iterator = coefficients.iterator();
				while (iterator.hasNext()) {
					VehicleCoefficient coefficient = iterator.next();
					CoefficientType coefficientType = CoefficientType.match(coefficient.getType());
					if (null == coefficientType)
						iterator.remove();
					if (coefficientType != type)
						continue;
					iterator.remove();
					info.addCoefficient(coefficient, null == spinner ? null : spinner.get(coefficient.getId()));
				}
			}
			return list;
		} else 
			return null;
	}
}
