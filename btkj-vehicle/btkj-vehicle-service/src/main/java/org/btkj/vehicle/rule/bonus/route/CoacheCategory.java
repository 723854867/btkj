package org.btkj.vehicle.rule.bonus.route;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.btkj.vehicle.rule.bonus.BonusUtils;

/**
 * 客车种类
 * 
 * @author ahab
 */
public class CoacheCategory extends BonusRoute<BonusRoute<?>> {

	public CoacheCategory(String id, String title) {
		super(id, title);
	}
	
	@Override
	protected List<VehicleCoefficientsInfo> coefficients(List<VehicleCoefficient> coefficients, Map<Integer, Integer> spinner, BonusSearcher searcher) {
		return BonusUtils.noProfitCoacheCommercialCoefficients(coefficients, spinner, searcher);
	}
	
	@Override
	protected Map<Integer, Integer> checkCommercialCommisionSpinner(BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		return BonusUtils.noProfitCoacheCommercialSpinnerCheck(searcher, coefficients);
	}
}
