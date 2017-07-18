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

public class Profit extends BonusRoute<BonusRoute<?>> {
	
	public Profit() {
		super("profit", "营利");
	}
	
	@Override
	public List<VehicleCoefficientsInfo> coefficients(LinkedList<String> path, Node<BonusRouteBody> parent, List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		searcher.setBizType(VehicleBizType.PROFIT);
		return super.coefficients(path, parent, coefficients, searcher);
	}
	
	@Override
	public Result<Void> settings(LinkedList<String> paths, Node<BonusRouteBody> parent, BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		searcher.setBizType(VehicleBizType.PROFIT);
		return super.settings(paths, parent, searcher, coefficients);
	}
}
