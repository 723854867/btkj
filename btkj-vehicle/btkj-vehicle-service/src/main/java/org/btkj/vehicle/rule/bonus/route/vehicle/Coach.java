package org.btkj.vehicle.rule.bonus.route.vehicle;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.vo.BonusSearcher;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.btkj.vehicle.rule.bonus.BonusUtils;
import org.btkj.vehicle.rule.bonus.route.BonusRoute;
import org.btkj.vehicle.rule.bonus.route.CoacheUseType;

public class Coach extends BonusRoute<CoacheUseType> {

	public Coach() {
		super("coach", "客车");
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
