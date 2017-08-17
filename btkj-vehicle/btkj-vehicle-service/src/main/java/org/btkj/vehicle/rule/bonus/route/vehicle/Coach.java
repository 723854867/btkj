package org.btkj.vehicle.rule.bonus.route.vehicle;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.vehicle.pojo.model.VehicleCoefficientType;
import org.btkj.vehicle.pojo.param.BonusPoundageEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientsParam;
import org.btkj.vehicle.rule.bonus.BonusUtils;
import org.btkj.vehicle.rule.bonus.route.BonusRoute;
import org.btkj.vehicle.rule.bonus.route.CoacheUseType;

public class Coach extends BonusRoute<CoacheUseType> {

	public Coach() {
		super("coach", "客车");
	}
	
	@Override
	protected List<VehicleCoefficientType> coefficients(List<VehicleCoefficient> coefficients, Map<Integer, Integer> spinner, PoundageCoefficientsParam param) {
		return BonusUtils.noProfitCoacheCommercialCoefficients(coefficients, spinner, param);
	}
	
	@Override
	protected Map<Integer, Integer> checkCommercialCommisionSpinner(BonusPoundageEditParam param, List<VehicleCoefficient> coefficients) {
		return BonusUtils.noProfitCoacheCommercialSpinnerCheck(param, coefficients);
	}
}
