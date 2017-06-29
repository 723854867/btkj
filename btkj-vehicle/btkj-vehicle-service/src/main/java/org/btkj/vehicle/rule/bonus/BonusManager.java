package org.btkj.vehicle.rule.bonus;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.BonusConfig;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.PolicyState;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.model.BonusRouteView;
import org.btkj.vehicle.model.VehicleCoefficientInfo;
import org.btkj.vehicle.mongo.BonusConfigMapper;
import org.btkj.vehicle.redis.VehicleBrandMapper;
import org.btkj.vehicle.redis.VehicleCoefficientMapper;
import org.btkj.vehicle.redis.VehicleDeptMapper;
import org.btkj.vehicle.rule.bonus.route.BonusRoute;
import org.rapid.util.Node;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtils;

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
	
	public Result<Void> bonusSettings(BonusSearcher searcher) {
		String path = searcher.getPath();
		LinkedList<String> paths = CollectionUtils.toStrLinkedList(path.split(Consts.SYMBOL_UNDERLINE));
		String nextId = paths.poll();
		BonusRoute route = null == nextId ? null : bonusRoutes.get(nextId);
		if (null == route)
			return Consts.RESULT.FAILURE;
		BonusConfig config = bonusConfigMapper.getByKey(searcher.getId());
		if (!searcher.isDelete() && null == config)
			config = new BonusConfig(searcher.getTid(), searcher.getInsurerId());
		if (searcher.isDelete() && null == config)
			return Consts.RESULT.FAILURE;
		
		List<VehicleCoefficient> coefficients = vehicleCoefficientMapper.getByTid(BtkjConsts.GLOBAL_TENANT_ID);
		coefficients.addAll(vehicleCoefficientMapper.getByTid(searcher.getTid()));
		Result<Void> result = searcher.isDelete() ? route.delete(paths, config) : route.settings(paths, config, searcher, coefficients);
		if (result.isSuccess()) {
			if (searcher.isDelete()) {
				if ((null == config.getChildren() || config.getChildren().isEmpty()))
					bonusConfigMapper.delete(config.get_id());
				else
					bonusConfigMapper.insert(config);
			} else 
				bonusConfigMapper.insert(config);
		}
		return result;
	}
	
	public Result<List<VehicleCoefficientInfo>> coefficients(BonusSearcher searcher) {
		LinkedList<String> path = CollectionUtils.toStrLinkedList(searcher.getPath().split(Consts.SYMBOL_UNDERLINE));
		String nextId = path.poll();
		BonusRoute route = null == nextId ? null : bonusRoutes.get(nextId);
		if (null == route)
			return Consts.RESULT.FAILURE;
		BonusConfig config = bonusConfigMapper.getByKey(searcher.getId());
		List<VehicleCoefficient> coefficients = vehicleCoefficientMapper.getByTid(BtkjConsts.GLOBAL_TENANT_ID);
		coefficients.addAll(vehicleCoefficientMapper.getByTid(searcher.getTid()));
		return Result.result(route.coefficients(path, config, coefficients, searcher));
	}
	
	/**
	 * 计算佣金
	 * 
	 * @param order
	 */
	public void calculateBonus(VehicleOrder order) {
		if (order.getState().mark() < PolicyState.QUOTE_SUCCESS.mark() || null != order.getBonus() || null == order.getTips())
			return;
		LinkedList<String> paths = order.getTips().commisionRoutePath();
		BonusRoute route = null == paths.peek() ? null : bonusRoutes.get(paths.poll());
		if (null == route)
			return;
		BonusConfig config = bonusConfigMapper.getByKey(order.getTid() + Consts.SYMBOL_UNDERLINE + order.getInsurerId());
		if (null == config)
			return;
		List<VehicleCoefficient> coefficients = vehicleCoefficientMapper.getByTid(BtkjConsts.GLOBAL_TENANT_ID);
		coefficients.addAll(vehicleCoefficientMapper.getByTid(order.getTid()));
		route.calculateBonus(paths, order, config, null, coefficients);
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
