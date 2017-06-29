package org.btkj.vehicle.rule.bonus.route;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.VehicleBizType;
import org.btkj.pojo.submit.BonusSearcher;

public class Profit extends BonusRoute<BonusRoute<?>> {
	
	public Profit() {
		super("profit", "营利");
	}
	
	@Override
	protected List<VehicleCoefficient> coefficients(List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		searcher.setBizType(VehicleBizType.PROFIT);
		return null;
	}
	
	@Override
	protected Map<Integer, Integer> checkCommercialCommisionSpinner(BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		searcher.setBizType(VehicleBizType.PROFIT);
		return null;
	}
}
