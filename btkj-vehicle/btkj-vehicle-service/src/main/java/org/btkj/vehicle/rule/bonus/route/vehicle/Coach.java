package org.btkj.vehicle.rule.bonus.route.vehicle;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.rule.bonus.BonusUtils;
import org.btkj.vehicle.rule.bonus.route.BonusRoute;
import org.btkj.vehicle.rule.bonus.route.CoacheUseType;

public class Coach extends BonusRoute<CoacheUseType> {

	public Coach() {
		super("coach", "客车");
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
