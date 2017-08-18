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
	public VehicleCoefficients coefficients(LinkedList<String> path, Node<BonusRouteBody> parent, List<VehicleCoefficient> coefficients, PoundageCoefficientsParam param) {
		param.setBizType(VehicleBizType.IGNROE_PROFIT);
		return super.coefficients(path, parent, coefficients, param);
	}
	
	@Override
	public Result<Void> settings(LinkedList<String> paths, Node<BonusRouteBody> parent, BonusPoundageEditParam param, List<VehicleCoefficient> coefficients) {
		param.setBizType(VehicleBizType.IGNROE_PROFIT);
		return super.settings(paths, parent, param, coefficients);
	}
}
