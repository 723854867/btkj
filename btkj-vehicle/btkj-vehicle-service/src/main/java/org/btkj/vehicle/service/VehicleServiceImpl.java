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
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.PolicyState;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.insur.vehicle.PolicyDetail;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.model.Lane;
import org.btkj.vehicle.model.VehicleOrderSearcher;
import org.btkj.vehicle.mongo.RenewalMapper;
import org.btkj.vehicle.mongo.VehicleOrderMapper;
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
	private BiHuVehicle biHuVehicle;			// 壁虎车险
	@Resource
	private BaoTuVehicle baoTuVehicle;			// 保途车险
	@Resource
	private RenewalMapper renewalMapper;		// 续保缓存
	@Resource
	private LeBaoBaVehicle leBaoBaVehicle;		// 乐宝吧车险
	@Resource
	private VehicleOrderMapper vehicleOrderMapper;
	
	@Override
	public Result<Renewal> renewal(EmployeeForm employeeForm, String license, String name) {
		Renewal renewal = renewalMapper.getByLicense(license);
		if (null != renewal) {
			if (!renewal.getTips().getOwner().getName().equals(name))
				return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
			return Result.result(renewal);
		}
		Result<Renewal> result = biHuVehicle.renewal(employeeForm, license, name);
		if (result.isSuccess()) {
			renewalMapper.insert(result.attach());
			if (!result.attach().getTips().getOwner().getName().equals(name))
				return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
		}
		return result;
	}
	
	@Override
	public Result<Renewal> renewal(EmployeeForm employeeForm, String vin, String engine, String name) {
		Renewal renewal = renewalMapper.getByVin(vin);
		if (null != renewal) {
			if (!renewal.getTips().getOwner().getName().equals(name))
				return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
			return Result.result(renewal);
		}
		Result<Renewal> result = biHuVehicle.renewal(employeeForm, vin, engine, name);
		if (result.isSuccess()) {
			renewalMapper.insert(result.attach());
			if (!result.attach().getTips().getOwner().getName().equals(name))
				return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
		}
		return result;
	}

	@Override
	public Result<Void> order(List<Insurer> quote, List<Insurer> insure, EmployeeForm ef, VehiclePolicyTips tips) {
		ICode code = rule.orderCheck(ef.getTenant().getRegion(), tips.getSchema());
		if (code != Code.OK)
			return Result.result(code);
		Tenant tenant = ef.getTenant();
		List<Route> routes = routeMapper.routes(tenant);
		String batchId = _batchId(tips.getLicense(), ef.getEmployee().getId());
		Set<Integer> baotuQuote = null;
		Set<Integer> baotuInsure = null;
		Set<Integer> bihuQuote = null;
		Set<Integer> bihuInsure = null;
		Set<Integer> leBaoBaQuote = null;
		Set<Integer> leBaoBaInsure = null;
		Map<Integer, VehicleOrder> orders = new HashMap<Integer, VehicleOrder>();
		for (Insurer insurer : quote) {
			Iterator<Route> itr = routes.iterator();
			boolean find = false;
			while (itr.hasNext()) {
				Route route = itr.next();
				if (route.getInsurerId() != insurer.getId())
					continue;
				itr.remove();
				find = true;
				Lane lane = Lane.match(route.getLane());
				boolean flag = false;
				if (!CollectionUtils.isEmpty(insure)) {
					Iterator<Insurer> itr1 = insure.iterator();
					while (itr1.hasNext()) {
						Insurer temp = itr1.next();
						if (temp.getId() == insurer.getId()) {
							itr1.remove();
							flag = true;
							break;
						}
					}
				}
				VehicleOrder order = new VehicleOrder(_orderId(tips.getLicense(), ef.getEmployee().getId(), insurer.getId()), batchId, insurer, lane.mark(), flag, tips);
				orders.put(insurer.getId(), order);
				switch (lane) {
				case BI_HU:
					if (null == bihuQuote)
						bihuQuote = new HashSet<Integer>();
					bihuQuote.add(insurer.getId());
					if (order.isInsure()) {
						if (null == bihuInsure)
							bihuInsure = new HashSet<Integer>();
						bihuInsure.add(insurer.getId());
					}
					break;
				case LE_BAO_BA:
					if (null == leBaoBaQuote)
						leBaoBaQuote = new HashSet<Integer>();
					leBaoBaQuote.add(insurer.getId());
					if (order.isInsure()) {
						if (null == leBaoBaInsure)
							leBaoBaInsure = new HashSet<Integer>();
						leBaoBaInsure.add(insurer.getId());
					}
					break;
				default:
					if (null == baotuQuote)
						baotuQuote = new HashSet<Integer>();
					baotuQuote.add(insurer.getId());
					if (order.isInsure()) {
						if (null == baotuInsure)
							baotuInsure = new HashSet<Integer>();
						baotuInsure.add(insurer.getId());
					}
					break;
				}
			}
			if (!find)
				return Result.result(BtkjCode.LANE_NOT_SET);
		}
		vehicleOrderMapper.deleteBatchOrder(batchId);
		for (VehicleOrder order : orders.values())
			vehicleOrderMapper.insert(order);
		if (null != bihuQuote) {
			Result<Void> result = biHuVehicle.order(ef, bihuQuote, bihuInsure, tips);
			if (!result.isSuccess()) 				
				vehicleOrderMapper.requestFailure(orders, bihuQuote, result.getDesc());
		}
		if (null != leBaoBaQuote) {
			Result<Void> result = leBaoBaVehicle.order(ef, leBaoBaQuote, leBaoBaInsure, tips);
			if (!result.isSuccess()) 				
				vehicleOrderMapper.requestFailure(orders, leBaoBaQuote, result.getDesc());
		} 
		if (!quote.isEmpty()) {
			Result<Void> result = baoTuVehicle.order(ef, baotuQuote, baotuInsure, tips);
			if (!result.isSuccess()) 				
				vehicleOrderMapper.requestFailure(orders, baotuQuote, result.getDesc());
		}
		return Result.success();
	}
	
	@Override
	public Result<VehicleOrder> orderInfo(EmployeeForm ef, String id) {
		VehicleOrder order = vehicleOrderMapper.getByKey(id);
		if (null == order)
			return Result.result(BtkjCode.ORDER_NOT_EXIST);
		
		// 只需要刷新壁虎的订单即可：保途和乐宝吧的订单状态是异步回调的，壁虎的需要主动请求
		if (order.getLane() == Lane.BI_HU.mark() && order.getState() == PolicyState.NEW) {
			Result<PolicySchema> result = _quoteResult(ef, order);
			if (result.isSuccess() && order.isInsure()) {			// 还要获取一次核保信息
				Result<PolicyDetail> detail = biHuVehicle.insureResult(ef, order.getTips().getLicense(), order.getInsurerId());
				if (!detail.isSuccess()) {
					if (detail.getCode() == BtkjCode.INSURE_FAILURE.id()) 
						vehicleOrderMapper.insureFailure(order, detail.getDesc());
				} else
					vehicleOrderMapper.insureSuccess(order, detail.attach(), detail.getDesc());
			}
		}
		return Result.result(order);
	}
	
	private Result<PolicySchema> _quoteResult(EmployeeForm employeeForm, VehicleOrder order) {
		Result<PolicySchema> result = biHuVehicle.quoteResult(employeeForm, order.getTips().getLicense(), order.getInsurerId());
		if (!result.isSuccess()) {
			if (result.getCode() == BtkjCode.QUOTE_FAILURE.id()) {
				vehicleOrderMapper.quoteFailure(order, result.getDesc());
				order.setState(PolicyState.QUOTE_FAILURE);
				order.setDesc(result.getDesc());
			}
		} else {
			vehicleOrderMapper.quoteSuccess(order, result.getDesc(), result.attach());
			order.getTips().setSchema(result.attach());
			order.setDesc(result.getDesc());
		}
		return result;
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
	
	@Override
	public Pager<VehicleOrder> orders(EmployeeForm ef, VehicleOrderSearcher searcher) {
		return vehicleOrderMapper.list(ef, searcher);
	}
	
	private String _orderId(String license, int employeeId, int insurerId) {
		return employeeId + Consts.SYMBOL_UNDERLINE + license + Consts.SYMBOL_UNDERLINE + insurerId;
	}
	
	private String _batchId(String license, int employeeId) {
		return employeeId + Consts.SYMBOL_UNDERLINE + license;
	}
}
