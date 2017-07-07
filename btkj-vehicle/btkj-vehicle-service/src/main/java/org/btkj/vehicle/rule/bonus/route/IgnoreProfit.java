package org.btkj.vehicle.rule.bonus.route;

import java.util.LinkedList;
import java.util.List;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.VehicleBizType;
import org.btkj.pojo.model.insur.vehicle.BonusRouteBody;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.rapid.util.Node;
import org.rapid.util.common.message.Result;

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
	public List<VehicleCoefficientsInfo> coefficients(LinkedList<String> path, Node<BonusRouteBody> parent, List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		searcher.setBizType(VehicleBizType.IGNROE_PROFIT);
		return super.coefficients(path, parent, coefficients, searcher);
	}
	
	@Override
	public Result<Void> settings(LinkedList<String> paths, Node<BonusRouteBody> parent, BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		searcher.setBizType(VehicleBizType.IGNROE_PROFIT);
		return super.settings(paths, parent, searcher, coefficients);
	}
}
