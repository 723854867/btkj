package org.btkj.vehicle.rule.bonus.route;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.VehicleBizType;
import org.btkj.pojo.submit.BonusSearcher;

/**
 * 不区分营利和非营利：摩托车、拖拉机、特种车
 * 
 * @author ahab
 *
 */
public class IgnoreProfit extends BonusRoute<BonusRoute<?>> {
	
	public IgnoreProfit() {
		super("ignoreProfit", "不区分营利和非营利");
	}
	
	@Override
	protected List<VehicleCoefficient> coefficients(List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		searcher.setBizType(VehicleBizType.IGNROE_PROFIT);
		return null;
	}
	
	@Override
	protected Map<Integer, Integer> checkCommercialCommisionSpinner(BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		searcher.setBizType(VehicleBizType.IGNROE_PROFIT);
		return null;
	}
}
