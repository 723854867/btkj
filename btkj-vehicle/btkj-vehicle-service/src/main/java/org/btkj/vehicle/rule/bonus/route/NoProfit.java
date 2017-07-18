package org.btkj.vehicle.rule.bonus.route;

import java.util.LinkedList;
import java.util.List;

import org.btkj.pojo.bo.BonusRouteBody;
import org.btkj.pojo.enums.VehicleBizType;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.vo.BonusSearcher;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.rapid.util.Node;
import org.rapid.util.common.message.Result;

public class NoProfit extends BonusRoute<BonusRoute<?>> {
	
	private Brand brand;

	public NoProfit() {
		super("noProfit", "非营利");
	}
	
	@Override
	public List<VehicleCoefficientsInfo> coefficients(LinkedList<String> path, Node<BonusRouteBody> parent, List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		searcher.setBizType(VehicleBizType.NO_PROFIT);
		return super.coefficients(path, parent, coefficients, searcher);
	}
	
	@Override
	public Result<Void> settings(LinkedList<String> paths, Node<BonusRouteBody> parent, BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		searcher.setBizType(VehicleBizType.NO_PROFIT);
		return super.settings(paths, parent, searcher, coefficients);
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
