package org.btkj.vehicle.rule.bonus.route;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.VehicleBizType;
import org.btkj.pojo.submit.BonusSearcher;

public class NoProfit extends BonusRoute<BonusRoute<?>> {
	
	private Brand brand;

	public NoProfit() {
		super("noProfit", "非营利");
	}
	
	@Override
	protected List<VehicleCoefficient> coefficients(List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		searcher.setBizType(VehicleBizType.NO_PROFIT);
		return null;
	}
	
	@Override
	protected Map<Integer, Integer> checkCommercialCommisionSpinner(BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		searcher.setBizType(VehicleBizType.NO_PROFIT);
		return null;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
