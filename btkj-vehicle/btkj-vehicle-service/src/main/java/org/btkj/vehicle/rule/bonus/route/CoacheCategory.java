package org.btkj.vehicle.rule.bonus.route;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.submit.BonusSearcher;
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
	protected List<VehicleCoefficient> coefficients(List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		return BonusUtils.noProfitCoacheCommercialCoefficients(searcher, coefficients);
	}
	
	@Override
	protected Map<Integer, Integer> checkCommercialCommisionSpinner(BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		return BonusUtils.noProfitCoacheCommercialSpinnerCheck(searcher, coefficients);
	}
}
