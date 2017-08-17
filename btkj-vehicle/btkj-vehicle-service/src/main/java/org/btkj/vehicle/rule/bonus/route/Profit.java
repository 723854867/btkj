package org.btkj.vehicle.rule.bonus.route;

import java.util.LinkedList;
import java.util.List;

import org.btkj.pojo.bo.BonusRouteBody;
import org.btkj.pojo.enums.VehicleBizType;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.vehicle.pojo.model.VehicleCoefficients;
import org.btkj.vehicle.pojo.param.BonusPoundageEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientsParam;
import org.rapid.util.Node;
import org.rapid.util.common.message.Result;

public class Profit extends BonusRoute<BonusRoute<?>> {
	
	public Profit() {
		super("profit", "营利");
	}
	
	@Override
	public VehicleCoefficients coefficients(LinkedList<String> path, Node<BonusRouteBody> parent, List<VehicleCoefficient> coefficients, PoundageCoefficientsParam param) {
		param.setBizType(VehicleBizType.PROFIT);
		return super.coefficients(path, parent, coefficients, param);
	}
	
	@Override
	public Result<Void> settings(LinkedList<String> paths, Node<BonusRouteBody> parent, BonusPoundageEditParam param, List<VehicleCoefficient> coefficients) {
		param.setBizType(VehicleBizType.PROFIT);
		return super.settings(paths, parent, param, coefficients);
	}
}
