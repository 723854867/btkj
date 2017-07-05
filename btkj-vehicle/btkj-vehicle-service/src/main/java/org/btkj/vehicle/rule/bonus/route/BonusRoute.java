package org.btkj.vehicle.rule.bonus.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.insur.vehicle.Bonus;
import org.btkj.pojo.model.insur.vehicle.BonusRouteBody;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.VehicleUtils;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.rapid.util.Node;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.GENDER;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtils;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.rapid.util.math.compare.IntComparable;
import org.rapid.util.math.compare.StrComparable;
import org.rapid.util.validator.IdentityValidator;

/**
 * 佣金路由
 * 
 * @author ahab
 *
 * @param <NODE>
 */
public class BonusRoute<NODE extends BonusRoute<?>> extends Node<NODE>{
	
	public BonusRoute() {}
	
	public BonusRoute(String id, String title) {
		super(id, title);
	}
	
	/**
	 * 设置佣金参数
	 * 
	 * @param paths
	 * @param parent
	 * @param searcher
	 * @return
	 */
	public Result<Void> settings(LinkedList<String> paths, Node<BonusRouteBody> parent, BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		BonusRouteBody body = parent.getChild(id);
		if (null == body) {
			body = new BonusRouteBody(id, title);
			parent.addChiled(body);
		}
		String nextId = paths.poll();
		if (null == nextId) {
			BonusRouteBody update = searcher.getRouteBody();
			body.setCommercialRate(update.getCommercialRate());
			body.setCompulsoryRate(update.getCompulsoryRate());
			body.setCommercialRetainRate(update.getCommercialRetainRate());
			body.setCompulsoryRetainRate(update.getCompulsoryRetainRate());
			body.setCommercialCommisionSpinner(checkCommercialCommisionSpinner(searcher, coefficients));
			return Consts.RESULT.OK;
		}
		BonusRoute nextRoute = null == children ? null : children.get(nextId);
		if (null == nextRoute)
			return Consts.RESULT.FORBID;
		return nextRoute.settings(paths, body, searcher, coefficients);
	}
	
	/**
	 * 删除佣金设置
	 * 
	 * @param paths
	 * @param parent
	 * @return
	 */
	public final Result<Void> delete(LinkedList<String> paths, Node<BonusRouteBody> parent) { 
		BonusRouteBody body = parent.getChild(id);
		if (null == body)
			return Consts.RESULT.FAILURE;
		String nextId = paths.poll();
		if (null == nextId) {
			if (null == body.getChildren() || body.getChildren().isEmpty())
				parent.removeChild(id);
			else {
				body.setCommercialCommisionSpinner(null);
				body.setCommercialRate(0);
				body.setCompulsoryRate(0);
				body.setCommercialRetainRate(0);
				body.setCompulsoryRetainRate(0);
			}
		} else {
			BonusRoute nextRoute = null == children ? null : children.get(nextId);
			if (null == nextRoute)
				return Consts.RESULT.FAILURE;
			Result<Void> result = nextRoute.delete(paths, body);
			if (!result.isSuccess())
				return result;
			if ((null == body.getChildren() || body.getChildren().isEmpty())
					&& (null == body.getCommercialCommisionSpinner() || body.getCommercialCommisionSpinner().isEmpty())
					&& 0 == body.getCommercialRate() && 0 == body.getCommercialRetainRate()
					&& 0 == body.getCompulsoryRate() && 0 == body.getCompulsoryRetainRate()) {
				parent.removeChild(body.getId());
			}
		}
		return Consts.RESULT.OK;
	}
	
	public void calculateBonus(LinkedList<String> paths, VehicleOrder order, Node<BonusRouteBody> parent, 
			BonusRouteBody preEffectBody, List<VehicleCoefficient> coefficients) {
		BonusRouteBody body = parent.getChild(id);
		if (null != body && body.isValid()) 
			preEffectBody = body;
		String nextId = paths.poll();
		if (null == nextId) {					// 计算本次的佣金
			if (null == preEffectBody)
				return;
			Bonus bonus = new Bonus();
			int totalCommercialRate = 0;
			VehiclePolicyTips tips = order.getTips();
			Map<Integer, Integer> spinner = preEffectBody.getCommercialCommisionSpinner();
			if (null != spinner) {
				Map<CoefficientType, Map<VehicleCoefficient, Integer>> map = new HashMap<CoefficientType, Map<VehicleCoefficient, Integer>>();
				for (VehicleCoefficient coefficient : coefficients) {
					Integer rate = spinner.remove(coefficient.getId());
					if (null == rate)
						continue;
					CoefficientType coefficientType = CoefficientType.match(coefficient.getType());
					if (null == coefficientType)
						continue;
					Map<VehicleCoefficient, Integer> temp = map.get(coefficientType);
					if (null == temp) {
						temp = new HashMap<VehicleCoefficient, Integer>();
						map.put(coefficientType, temp);
					}
					temp.put(coefficient, rate);
				}
				
				for (Entry<CoefficientType, Map<VehicleCoefficient, Integer>> entry : map.entrySet()) {
					CoefficientType coefficientType = entry.getKey();
					Map<VehicleCoefficient, Integer> temp = entry.getValue();
					for (Entry<VehicleCoefficient, Integer> tempEntry : temp.entrySet()) {
						VehicleCoefficient coefficient = tempEntry.getKey();
						ComparisonSymbol symbol = ComparisonSymbol.match(coefficient.getComparison());
						if (null == symbol)
							continue;
						String[] params = coefficient.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
						switch (coefficientType) {
						case ZXB:
						case CLAIMS:
						case NO_CLAIMS:
						case GENDER:
							int src = IdentityValidator.isMale(tips.getOwner().getIdNo()) ? GENDER.MALE.mark() : GENDER.FEMALE.mark();
						case SEAT_COUNT:
							src = tips.getSeat();
							if (IntComparable.SINGLETON.compare(symbol, src, CollectionUtils.toIntegerArray(params)));
								totalCommercialRate += tempEntry.getValue();
							break;
						case VEHICLE_AGE:
							src = VehicleUtils.vehicleAge(order);
							if (IntComparable.SINGLETON.compare(symbol, src, CollectionUtils.toIntegerArray(params)))
								totalCommercialRate += tempEntry.getValue();
							break;
						case AGE:
							src = IdentityValidator.getAge(tips.getOwner().getIdNo());
							if (IntComparable.SINGLETON.compare(symbol, src, CollectionUtils.toIntegerArray(params)))
								totalCommercialRate += tempEntry.getValue();
							break;
						case PURCHASE_PRICE:
							src = (int) (tips.getPrice());
							if (IntComparable.SINGLETON.compare(symbol, src, CollectionUtils.toIntegerArray(params)))
								totalCommercialRate += tempEntry.getValue();
							break;
						case LICENSE:
							if (StrComparable.SINGLETON.compare(symbol, tips.getLicense().substring(0, 2), params))
								totalCommercialRate += tempEntry.getValue();
							break;
						default:
							break;
						}
					}
				}
			}
			int commercialRate = preEffectBody.getCommercialRate() + totalCommercialRate - preEffectBody.getCommercialRetainRate();
			int compulsoryRate = preEffectBody.getCompulsoryRate() - preEffectBody.getCompulsoryRetainRate();
			commercialRate = Math.max(0, commercialRate);
			compulsoryRate = Math.max(0, compulsoryRate);
			bonus.setCommercialBonus(tips.getSchema().getCommericalTotal() * commercialRate);
			bonus.setCompulsoryBonus((tips.getSchema().getCompulsiveTotal() + tips.getSchema().getVehicleVesselTotal()) * compulsoryRate);
			order.setBonus(bonus);
		} else {
			calculateBonus(paths, order, body, preEffectBody, coefficients);
		}
	}
	
	/**
	 * 获取车险手续费配置系数
	 * 
	 * @return
	 */
	public List<VehicleCoefficientsInfo> coefficients(LinkedList<String> path, Node<BonusRouteBody> parent,  List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		String nextId = path.poll();
		BonusRouteBody body = null == parent ? null : parent.getChild(id);
		if (null == nextId) {
			List<VehicleCoefficient> list = coefficients(coefficients, searcher);
			Map<Integer, Integer> spinner = null == body ? null : body.getCommercialCommisionSpinner();
			if (null == list) 
				return null;
			Map<CoefficientType, VehicleCoefficientsInfo> map = new HashMap<CoefficientType, VehicleCoefficientsInfo>();
			for (VehicleCoefficient coefficient : list) {
				Integer rate = null == spinner ? null : spinner.get(coefficient.getId());
				CoefficientType type = CoefficientType.match(coefficient.getType());
				if (null == type)
					continue;
				VehicleCoefficientsInfo temp = map.get(type);
				if (null == temp) {
					temp = new VehicleCoefficientsInfo(type);
					map.put(type, temp);
				}
				temp.addCoefficient(coefficient, rate);
			}
			return new ArrayList<VehicleCoefficientsInfo>(map.values());
		} else {
			BonusRoute nextRoute = null == children ? null : children.get(nextId);
			if (null == nextRoute)
				return null;
			return nextRoute.coefficients(path, body, coefficients, searcher);
		}
	}
	
	protected List<VehicleCoefficient> coefficients(List<VehicleCoefficient> coefficients, BonusSearcher searcher) {
		return null;
	}
	
	/**
	 * 默认不关联系数
	 * 
	 * @param searcher
	 * @return
	 */
	protected Map<Integer, Integer> checkCommercialCommisionSpinner(BonusSearcher searcher, List<VehicleCoefficient> coefficients) {
		return null;
	}
}
