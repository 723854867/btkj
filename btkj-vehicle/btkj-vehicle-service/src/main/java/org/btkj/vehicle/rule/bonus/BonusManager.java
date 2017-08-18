package org.btkj.vehicle.rule.bonus;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.BonusConfig;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.model.Bonus;
import org.btkj.vehicle.mongo.BonusConfigMapper;
import org.btkj.vehicle.pojo.model.BonusRouteView;
import org.btkj.vehicle.pojo.model.VehicleCoefficients;
import org.btkj.vehicle.pojo.param.BonusPoundageEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientsParam;
import org.btkj.vehicle.redis.VehicleBrandMapper;
import org.btkj.vehicle.redis.VehicleCoefficientMapper;
import org.btkj.vehicle.redis.VehicleDeptMapper;
import org.btkj.vehicle.rule.bonus.route.BonusRoute;
import org.rapid.util.Node;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.StringUtil;

public class BonusManager {
	
	private VehicleDeptMapper vehicleDeptMapper;
	private BonusConfigMapper bonusConfigMapper;
	private VehicleBrandMapper vehicleBrandMapper;
	private VehicleCoefficientMapper vehicleCoefficientMapper;
	
	private Map<String, BonusRoute<BonusRoute<?>>> bonusRoutes;
	private BonusRouteView routeView = new BonusRouteView();
	
	
	private void _init() {
		if (null == bonusRoutes)
			return;
		for (BonusRoute<BonusRoute<?>> route : bonusRoutes.values()) {
			Node<Node<?>> node = new Node<Node<?>>(route.getId(), route.getTitle());
			routeView.addChiled(node);
			if (null != route.getChildren())
				_buildRouteView(node, route);
		}
	}
	
	private void _buildRouteView(Node<Node<?>> parent, BonusRoute<?> route) {
		for (BonusRoute<?> subRoute : route.getChildren().values()) {
			Node<Node<?>> node = new Node<Node<?>>(subRoute.getId(), subRoute.getTitle());
			parent.addChiled(node);
			if (null == subRoute.getChildren())
				continue;
			_buildRouteView(node, subRoute);
		}
	}
	
	public BonusRouteView bonusRouteInfo() {
		return routeView;
	}
	
	public Result<Void> bonusPoundageEdit(BonusPoundageEditParam param) {
		String path = param.getPath();
		LinkedList<String> paths = CollectionUtil.toStrLinkedList(path.split(Consts.SYMBOL_UNDERLINE));
		String nextId = paths.poll();
		BonusRoute route = null == nextId ? null : bonusRoutes.get(nextId);
		if (null == route)
			return Consts.RESULT.FAILURE;
		BonusConfig config = bonusConfigMapper.getByKey(param.getId());
		if (!param.isDelete() && null == config)
			config = new BonusConfig(param.getTid(), param.getInsurerId());
		if (param.isDelete() && null == config)
			return Consts.RESULT.FAILURE;
		
		List<VehicleCoefficient> coefficients = vehicleCoefficientMapper.getByTid(BtkjConsts.GLOBAL_TENANT_ID);
		coefficients.addAll(vehicleCoefficientMapper.getByTid(param.getTid()));
		Result<Void> result = param.isDelete() ? route.delete(paths, config) : route.settings(paths, config, param, coefficients);
		if (result.isSuccess()) {
			if (param.isDelete()) {
				if ((null == config.getChildren() || config.getChildren().isEmpty()))
					bonusConfigMapper.delete(config.get_id());
				else
					bonusConfigMapper.insert(config);
			} else 
				bonusConfigMapper.insert(config);
		}
		return result;
	}
	
	public Result<VehicleCoefficients> poundageCoefficients(PoundageCoefficientsParam param) {
		LinkedList<String> path = CollectionUtil.toStrLinkedList(param.getPath().split(Consts.SYMBOL_UNDERLINE));
		String nextId = path.poll();
		BonusRoute route = null == nextId ? null : bonusRoutes.get(nextId);
		if (null == route)
			return Consts.RESULT.FAILURE;
		BonusConfig config = bonusConfigMapper.getByKey(param.getId());
		List<VehicleCoefficient> coefficients = vehicleCoefficientMapper.getByTid(BtkjConsts.GLOBAL_TENANT_ID);
		coefficients.addAll(vehicleCoefficientMapper.getByTid(param.getTid()));
		// 车牌省份过滤
		String provinceAbbreviation = 0 == param.getSubordinateProvince() ? null : StringUtil.provinceAbbreviation(param.getSubordinateProvince());
		if (null != provinceAbbreviation) {
			Iterator<VehicleCoefficient> iterator = coefficients.iterator();
			while (iterator.hasNext()) {
				VehicleCoefficient coefficient = iterator.next();
				if (coefficient.getType() != CoefficientType.LICENSE.mark())
					continue;
				if (!provinceAbbreviation.equals(coefficient.getComparableValue().substring(0, 1)))
					iterator.remove();
			}
		}
		return Result.result(route.coefficients(path, config, coefficients, param));
	}
	
	/**
	 * 计算佣金
	 * 
	 * @param order
	 */
	public void calculateBonus(VehicleOrder order) {
		if (order.getState().mark() < VehicleOrderState.QUOTE_SUCCESS.mark() || null != order.getBonus() || null == order.getTips())
			return;
		Bonus bonus = new Bonus();
		LinkedList<String> paths = order.getTips().commisionRoutePath();
		BonusRoute route = null == paths.peek() ? null : bonusRoutes.get(paths.poll());
		if (null != route) {
			BonusConfig config = bonusConfigMapper.getByKey(order.getTid() + Consts.SYMBOL_UNDERLINE + order.getInsurerId());
			if (null != config) {
				List<VehicleCoefficient> coefficients = vehicleCoefficientMapper.getByTid(BtkjConsts.GLOBAL_TENANT_ID);
				coefficients.addAll(vehicleCoefficientMapper.getByTid(order.getTid()));
				route.calculateBonus(paths, order, config, null, coefficients, bonus);
			}
		}
		order.setBonus(bonus);
	}
	
	public void setVehicleDeptMapper(VehicleDeptMapper vehicleDeptMapper) {
		this.vehicleDeptMapper = vehicleDeptMapper;
	}
	
	public void setBonusConfigMapper(BonusConfigMapper bonusConfigMapper) {
		this.bonusConfigMapper = bonusConfigMapper;
	}
	
	public void setVehicleBrandMapper(VehicleBrandMapper vehicleBrandMapper) {
		this.vehicleBrandMapper = vehicleBrandMapper;
	}
	
	public void setBonusRoutes(Map<String, BonusRoute<BonusRoute<?>>> bonusRoutes) {
		this.bonusRoutes = bonusRoutes;
	}
	
	public void setVehicleCoefficientMapper(VehicleCoefficientMapper vehicleCoefficientMapper) {
		this.vehicleCoefficientMapper = vehicleCoefficientMapper;
	}
}
