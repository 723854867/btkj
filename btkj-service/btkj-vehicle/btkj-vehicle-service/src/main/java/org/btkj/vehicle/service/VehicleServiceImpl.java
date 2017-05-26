package org.btkj.vehicle.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.baotu.vehicle.api.BaoTuVehicle;
import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.VehicleOrderType;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.Policy;
import org.btkj.pojo.submit.VehicleOrderSubmit;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.model.Lane;
import org.btkj.vehicle.mongo.OrderMapper;
import org.btkj.vehicle.mongo.RenewalMapper;
import org.btkj.vehicle.mybatis.entity.Route;
import org.btkj.vehicle.mybatis.entity.VehicleConfig;
import org.btkj.vehicle.redis.RouteMapper;
import org.btkj.vehicle.redis.VehicleConfigMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtils;
import org.springframework.stereotype.Service;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {
	
	@Resource
	private RouteMapper routeMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private BiHuVehicle biHuVehicle;			// 壁虎车险
	@Resource
	private BaoTuVehicle baoTuVehicle;			// 保途车险
	@Resource
	private RenewalMapper renewalMapper;		// 续保缓存
	@Resource
	private LeBaoBaVehicle leBaoBaVehicle;		// 乐宝吧车险
	@Resource
	private VehicleConfigMapper vehicleConfigMapper;
	
	@Override
	public Result<Renewal> renewal(EmployeeForm employeeForm, String license, String name) {
		Renewal renewal = renewalMapper.getByLicense(license);
		if (null != renewal) {
			if (!renewal.getOwner().getName().equals(name))
				return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
			return Result.result(renewal);
		}
		
		Result<Renewal> result = null;
		VehicleConfig config = vehicleConfigMapper.getByKey(employeeForm.getTenant().getTid());
		// 默认使用壁虎线路获取续保信息
		Lane lane = null != config ? Lane.match(config.getRenewLane()) : Lane.BI_HU;
		switch (lane) {
		case BI_HU:					// 壁虎
			result = biHuVehicle.renewal(employeeForm, license, name);
			break;
		case LE_BAO_BA:				// 乐宝吧
			break;
		default:
			break;					// 保途
		}
		if (result.isSuccess()) {
			renewalMapper.insert(result.attach());
			if (!result.attach().getOwner().getName().equals(name))
				return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
		}
		return result;
	}
	
	@Override
	public Result<Renewal> renewal(EmployeeForm employeeForm, String vin, String engine, String name) {
		Renewal renewal = renewalMapper.getByVin(vin);
		if (null != renewal)
			return Result.result(renewal);
		
		Result<Renewal> result = null;
		VehicleConfig config = vehicleConfigMapper.getByKey(employeeForm.getTenant().getTid());
		// 默认使用壁虎线路获取续保信息
		Lane lane = null != config ? Lane.match(config.getRenewLane()) : Lane.BI_HU;
		switch (lane) {
		case BI_HU:					// 壁虎
			result = biHuVehicle.renewal(employeeForm, vin, engine, name);
			break;
		case LE_BAO_BA:				// 乐宝吧
			break;
		default:
			break;					// 保途
		}
		if (result.isSuccess())
			renewalMapper.insert(result.attach());
		return result;
	}

	@Override
	public Result<Void> order(EmployeeForm employeeForm, VehicleOrderSubmit submit) {
		Tenant tenant = employeeForm.getTenant();
		List<Route> routes = routeMapper.routes(tenant);
		int quoteMod = submit.getQuoteMod();
		int insureMod = submit.getInsureMod();
		int biHuInsurMod = 0;
		int biHuQuoteMod = 0;
		int leBaoBaInsurMod = 0;
		int leBaoBaQuoteMod = 0;
		Map<Integer, Policy> policies = new HashMap<Integer, Policy>();
		if (!CollectionUtils.isEmpty(routes)) {
			Iterator<Route> itr = routes.iterator();
			while (itr.hasNext()) {
				Route route = itr.next();
				Lane lane = Lane.match(route.getLane());
				int insurerId = route.getInsurerId();
				Policy policy = new Policy(insurerId, lane.mark());
				VehicleOrderType type = null;
				if ((quoteMod & insurerId) == insurerId) {
					type = VehicleOrderType.QUOTE;
					switch (lane) {
					case BI_HU:
						biHuQuoteMod |= insurerId;
						break;
					case LE_BAO_BA:
						leBaoBaQuoteMod |= insurerId;
						break;
					default:
						break;
					}
				}
				if ((insureMod & insurerId) == insurerId) {
					type = null == type ? VehicleOrderType.INSURE : VehicleOrderType.QUOTE_AND_INSURE;
					switch (lane) {
					case BI_HU:
						biHuInsurMod |= insurerId;
						break;
					case LE_BAO_BA:
						leBaoBaInsurMod |= insurerId;
						break;
					default:
						break;
					}
				}
				policy.setType(type);
				policies.put(insurerId, policy);
			}
		}
		Renewal renewal = submit.getRenewal();
		int employeeId = employeeForm.getEmployee().getId();
		String license = renewal.getVehicle().getLicense();
		VehicleOrder order = new VehicleOrder(_orderId(license, employeeId), tenant.getTid(), employeeId, policies, renewal.getOwner(), renewal.getVehicle());
		orderMapper.insert(order);
		quoteMod ^= (biHuQuoteMod | leBaoBaQuoteMod);
		insureMod ^= (biHuInsurMod | leBaoBaInsurMod);
		if (0 != biHuQuoteMod) {
			Result<Void> result = biHuVehicle.order(employeeForm, biHuQuoteMod, biHuInsurMod, renewal);
			if (!result.isSuccess()) 				
				_systemError(order, biHuQuoteMod, result.getDesc());
		}
		if (0 != leBaoBaQuoteMod) {
			Result<Void> result = leBaoBaVehicle.order(employeeForm, leBaoBaQuoteMod, leBaoBaInsurMod, renewal);
			if (!result.isSuccess()) 				
				_systemError(order, biHuQuoteMod, result.getDesc());
		} 
		if (0 != quoteMod) {
			Result<Void> result = baoTuVehicle.order(employeeForm, quoteMod, insureMod, renewal);
			if (!result.isSuccess()) 				
				_systemError(order, biHuQuoteMod, result.getDesc());
		}
		return Result.success();
	}
	
	@Override
	public Result<Void> orderInfo(EmployeeForm employeeForm, String license) {
		String id = _orderId(license, employeeForm.getEmployee().getId());
		VehicleOrder order = orderMapper.getByKey(id);
		if (null == order)
			return Result.result(BtkjCode.ORDER_NOT_EXIST);
		
		// 只需要刷新壁虎的订单即可：保途和乐宝吧的订单状态是异步回调的，壁虎的需要主动请求
		Map<Integer, Policy> policies = order.getPolicies();
		return null;
	}
	
	/**
	 * 真实报价请求并没有发送出去，在保途后端校验失败，直接返回系统错误：理论上不会出现这种错误，因为
	 * 车险 lane 模块不会关心真实的规则，所有的规则校验全部在本服务中完成。
	 * 
	 * @param order
	 * @param quoteMod
	 */
	private void _systemError(VehicleOrder order, int quoteMod, String desc) {
		char[] arr = Integer.toBinaryString(quoteMod).toCharArray();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0, len = arr.length; i < len; i++) {
			if (arr[i] == '0')
				continue;
			int insurerId = (int) Math.pow(2, len - i - 1);
			list.add(insurerId);
		}
		orderMapper.biHuRequestFailure(order, list, desc);
	}
	
	private String _orderId(String license, int employeeId) {
		return employeeId + Consts.SYMBOL_UNDERLINE + license;
	}
}
