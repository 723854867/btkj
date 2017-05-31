package org.btkj.vehicle.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.baotu.vehicle.api.BaoTuVehicle;
import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.PolicyState;
import org.btkj.pojo.enums.VehicleOrderType;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.InsuranceSchema;
import org.btkj.pojo.model.insur.vehicle.Policy;
import org.btkj.pojo.model.insur.vehicle.PolicyDetail;
import org.btkj.pojo.submit.VehicleOrderSubmit;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.model.Lane;
import org.btkj.vehicle.mongo.OrderMapper;
import org.btkj.vehicle.mongo.RenewalMapper;
import org.btkj.vehicle.mybatis.entity.Route;
import org.btkj.vehicle.redis.RouteMapper;
import org.btkj.vehicle.rule.Rule;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.consts.code.ICode;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtils;
import org.springframework.stereotype.Service;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {
	
	@Resource
	private Rule rule;
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
	
	@Override
	public Result<Renewal> renewal(EmployeeForm employeeForm, String license, String name) {
		Renewal renewal = renewalMapper.getByLicense(license);
		if (null != renewal) {
			if (!renewal.getOwner().getName().equals(name))
				return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
			return Result.result(renewal);
		}
		Result<Renewal> result = biHuVehicle.renewal(employeeForm, license, name);
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
		if (null != renewal) {
			if (!renewal.getOwner().getName().equals(name))
				return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
			return Result.result(renewal);
		}
		Result<Renewal> result = biHuVehicle.renewal(employeeForm, vin, engine, name);
		if (result.isSuccess())
			renewalMapper.insert(result.attach());
		return result;
	}

	@Override
	public Result<Void> order(EmployeeForm employeeForm, VehicleOrderSubmit submit) {
		ICode code = rule.orderCheck(employeeForm.getTenant().getRegion(), submit);
		if (code != Code.OK)
			return Result.result(code);
		Tenant tenant = employeeForm.getTenant();
		List<Route> routes = routeMapper.routes(tenant);
		Set<Integer> quote = submit.getQuote();
		Set<Integer> insure = submit.getInsure();
		Map<Integer, Policy> policies = new HashMap<Integer, Policy>();
		Set<Integer> bihuQuote = null;
		Set<Integer> bihuInsure = null;
		Set<Integer> leBaoBaQuote = null;
		Set<Integer> leBaoBaInsure = null;
		for (int insurerId : quote) {
			Iterator<Route> itr = routes.iterator();
			boolean find = false;
			while (itr.hasNext()) {
				Route route = itr.next();
				if (route.getInsurerId() != insurerId)
					continue;
				find = true;
				itr.remove();
				Lane lane = Lane.match(route.getLane());
				Policy policy = new Policy(insurerId, lane.mark(), insure.remove(insurerId) ? VehicleOrderType.QUOTE_AND_INSURE : VehicleOrderType.QUOTE);
				policies.put(insurerId, policy);
				switch (lane) {
				case BI_HU:
					if (null == bihuQuote)
						bihuQuote = new HashSet<Integer>();
					bihuQuote.add(insurerId);
					if (policy.getType() == VehicleOrderType.QUOTE_AND_INSURE) {
						if (null == bihuInsure)
							bihuInsure = new HashSet<Integer>();
						bihuInsure.add(insurerId);
					}
					break;
				case LE_BAO_BA:
					if (null == leBaoBaQuote)
						leBaoBaQuote = new HashSet<Integer>();
					leBaoBaQuote.add(insurerId);
					if (policy.getType() == VehicleOrderType.QUOTE_AND_INSURE) {
						if (null == leBaoBaInsure)
							leBaoBaInsure = new HashSet<Integer>();
						leBaoBaInsure.add(insurerId);
					}
					break;
				default:
					break;
				}
				break;
			}
			if (!find)
				return Result.result(BtkjCode.LANE_NOT_SET);
		}
		if (null != bihuQuote)
			quote.removeAll(bihuQuote);
		if (null != leBaoBaQuote)
			quote.removeAll(leBaoBaQuote);
		Renewal renewal = submit.getRenewal();
		int employeeId = employeeForm.getEmployee().getId();
		String license = renewal.getVehicle().getLicense();
		VehicleOrder order = new VehicleOrder(_orderId(license, employeeId), tenant.getTid(), employeeId, policies, renewal.getOwner(), renewal.getVehicle());
		orderMapper.insert(order);
		if (null != bihuQuote) {
			Result<Void> result = biHuVehicle.order(employeeForm, bihuQuote, bihuInsure, renewal);
			if (!result.isSuccess()) 				
				orderMapper.requestFailure(order, bihuQuote, result.getDesc());
		}
		if (null != leBaoBaQuote) {
			Result<Void> result = leBaoBaVehicle.order(employeeForm, leBaoBaQuote, leBaoBaInsure, renewal);
			if (!result.isSuccess()) 				
				orderMapper.requestFailure(order, leBaoBaQuote, result.getDesc());
		} 
		if (!quote.isEmpty()) {
			Result<Void> result = baoTuVehicle.order(employeeForm, quote, insure, renewal);
			if (!result.isSuccess()) 				
				orderMapper.requestFailure(order, quote, result.getDesc());
		}
		return Result.success();
	}
	
	@Override
	public Result<VehicleOrder> orderInfo(EmployeeForm employeeForm, String license) {
		String id = _orderId(license, employeeForm.getEmployee().getId());
		VehicleOrder order = orderMapper.getByKey(id);
		if (null == order)
			return Result.result(BtkjCode.ORDER_NOT_EXIST);
		
		// 只需要刷新壁虎的订单即可：保途和乐宝吧的订单状态是异步回调的，壁虎的需要主动请求
		Map<Integer, Policy> policies = order.getPolicies();
		for (Policy policy : policies.values()) {
			if (policy.getLane() != Lane.BI_HU.mark() || policy.getState() != PolicyState.NEW) 
				continue;
			Result<InsuranceSchema> result = _quoteResult(employeeForm, license, order, policy);
			if (result.isSuccess() && policy.getType() == VehicleOrderType.QUOTE_AND_INSURE) {			// 还要获取一次核保信息
				Result<PolicyDetail> ir = biHuVehicle.insureResult(employeeForm, license, policy.getInsurerId());
				if (!ir.isSuccess()) {
					if (ir.getCode() == BtkjCode.INSURE_FAILURE.id()) 
						orderMapper.insureFailure(order, policy, ir.getDesc());
				} else
					orderMapper.insureSuccess(order, policy, ir.attach(), ir.getDesc());
			}
		}
		return Result.result(order);
	}
	
	private Result<InsuranceSchema> _quoteResult(EmployeeForm employeeForm, String license, VehicleOrder order, Policy policy) {
		Result<InsuranceSchema> result = biHuVehicle.quoteResult(employeeForm, license, policy.getInsurerId());
		if (!result.isSuccess()) {
			if (result.getCode() == BtkjCode.QUOTE_FAILURE.id()) 
				orderMapper.quoteFailure(order, policy, result.getDesc());
		} else
			orderMapper.quoteSuccess(order, policy, result.getDesc(), result.attach());
		return result;
	}
	
	private String _orderId(String license, int employeeId) {
		return employeeId + Consts.SYMBOL_UNDERLINE + license;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> insurers(Tenant tenant) {
		List<Route> list = routeMapper.routes(tenant);
		if (CollectionUtils.isEmpty(list))
			return Collections.EMPTY_LIST;
		List<Integer> l = new ArrayList<Integer>();
		for (Route route : list)
			l.add(route.getInsurerId());
		return l;
	}
}
