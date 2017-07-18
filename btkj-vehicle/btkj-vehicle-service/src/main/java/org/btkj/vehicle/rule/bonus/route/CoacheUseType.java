package org.btkj.vehicle.rule.bonus.route;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.vo.BonusSearcher;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.btkj.vehicle.rule.bonus.BonusUtils;

/**
 * 客车使用性质
 * 
 * @author ahab
 */
public class CoacheUseType extends BonusRoute<CoacheCategory> {
	
	private VehicleUsedType usedType;

	public CoacheUseType(String id, String title) {
		super(id, title);
		usedType = VehicleUsedType.match(id);
		if (null == usedType)
			throw new IllegalArgumentException("Error coache used type - " + id);
	}
	
	@Override
	protected List<VehicleCoefficientsInfo> coefficients(List<VehicleCoefficient> coefficients, Map<Integer, Integer> spinner, BonusSearcher searcher) {
		switch (usedType) {
		case HOME_USE:								// 家庭自用
		case ENTERPRISE:							// 企业
		case ORGAN:									// 机关
			return BonusUtils.noProfitCoacheCommercialCoefficients(coefficients, spinner, searcher);
		default:
			return null;
		}
	}
	
	@Override
	protected Map<Integer, Integer> checkCommercialCommisionSpinner(BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		switch (usedType) {
		case HOME_USE:								// 家庭自用
		case ENTERPRISE:							// 企业
		case ORGAN:									// 机关
			return BonusUtils.noProfitCoacheCommercialSpinnerCheck(searcher, coefficients);
		default:
			return null;
		}
	}
}
