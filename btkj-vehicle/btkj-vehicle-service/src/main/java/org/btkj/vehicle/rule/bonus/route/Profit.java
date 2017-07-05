package org.btkj.vehicle.rule.bonus.route;

import java.util.LinkedList;
import java.util.List;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.VehicleBizType;
import org.btkj.pojo.model.insur.vehicle.BonusRouteBody;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.rapid.util.Node;

public class Profit extends BonusRoute<BonusRoute<?>> {
	
	public Profit() {
		super("profit", "营利");
	}
	
	@Override
	public List<VehicleCoefficientsInfo> coefficients(LinkedList<String> path, Node<BonusRouteBody> parent,
			List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		searcher.setBizType(VehicleBizType.PROFIT);
		return super.coefficients(path, parent, coefficients, searcher);
	}
}
