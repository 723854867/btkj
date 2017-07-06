package org.btkj.vehicle.rule.bonus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.pojo.enums.vehicle.VehicleBizType;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;

public class BonusUtils {

	/**
	 * 非营利客车商业险系数检查
	 * 
	 * @param searcher
	 * @param coefficients
	 * @return
	 */
	public static Map<Integer, Integer> noProfitCoacheCommercialSpinnerCheck(BonusSearcher searcher, List<VehicleCoefficient> coefficients) { 
		Map<Integer, Integer> spinner = searcher.getRouteBody().getCommercialCommisionSpinner();
		if (searcher.getBizType() == VehicleBizType.NO_PROFIT && null != spinner) {// 非营利客车
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
	public static List<VehicleCoefficientsInfo> noProfitCoacheCommercialCoefficients(List<VehicleCoefficient> coefficients, Map<Integer, Integer> spinner, BonusSearcher searcher) { 
		if (searcher.getBizType() == VehicleBizType.NO_PROFIT) {			// 非营利客车
			List<VehicleCoefficientsInfo> list = new ArrayList<VehicleCoefficientsInfo>();
			for (CoefficientType type : CoefficientType.values()) {
				VehicleCoefficientsInfo info = new VehicleCoefficientsInfo(type);
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
