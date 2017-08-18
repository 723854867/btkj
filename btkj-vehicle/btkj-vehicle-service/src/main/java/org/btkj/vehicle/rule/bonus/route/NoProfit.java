package org.btkj.vehicle.rule.bonus.route;

import java.util.LinkedList;
import java.util.List;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.VehicleBizType;
import org.btkj.pojo.model.BonusRouteBody;
import org.btkj.vehicle.pojo.model.VehicleCoefficients;
import org.btkj.vehicle.pojo.param.BonusPoundageEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientsParam;
import org.rapid.util.Node;
import org.rapid.util.common.message.Result;

public class NoProfit extends BonusRoute<BonusRoute<?>> {
	
	private Brand brand;

	public NoProfit() {
		super("noProfit", "非营利");
	}
	
	@Override
	public VehicleCoefficients coefficients(LinkedList<String> path, Node<BonusRouteBody> parent, List<VehicleCoefficient> coefficients, PoundageCoefficientsParam param) {
		param.setBizType(VehicleBizType.NO_PROFIT);
		return super.coefficients(path, parent, coefficients, param);
	}
	
	@Override
	public Result<Void> settings(LinkedList<String> paths, Node<BonusRouteBody> parent, BonusPoundageEditParam param, List<VehicleCoefficient> coefficients) {
		param.setBizType(VehicleBizType.NO_PROFIT);
		return super.settings(paths, parent, param, coefficients);
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
