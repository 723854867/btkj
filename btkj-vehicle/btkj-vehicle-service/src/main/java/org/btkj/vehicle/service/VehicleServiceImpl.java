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
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.VehicleBrand;
import org.btkj.pojo.entity.VehicleDept;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.insur.vehicle.DeliveryInfo;
import org.btkj.pojo.model.insur.vehicle.PolicyDetail;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.btkj.vehicle.VehicleUtils;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.mongo.RenewalMapper;
import org.btkj.vehicle.mongo.VehicleOrderMapper;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.vehicle.pojo.model.VehicleOrderListInfo;
import org.btkj.vehicle.pojo.model.VehicleOrderSearcher;
import org.btkj.vehicle.redis.RouteMapper;
import org.btkj.vehicle.redis.VehicleBrandMapper;
import org.btkj.vehicle.redis.VehicleDeptMapper;
import org.btkj.vehicle.redis.VehicleModelMapper;
import org.btkj.vehicle.rule.Rule;
import org.btkj.vehicle.rule.bonus.BonusManager;
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
	private BonusManager bonusManager;
	@Resource
	private BaoTuVehicle baoTuVehicle;			// 保途车险
	@Resource
	private RenewalMapper renewalMapper;		// 续保缓存
	@Resource
	private LeBaoBaVehicle leBaoBaVehicle;		// 乐宝吧车险
	@Resource
	private VehicleOrderMapper vehicleOrderMapper;
	
	@Resource
	private VehicleDeptMapper vehicleDeptMapper;
	@Resource
	private VehicleBrandMapper vehicleBrandMapper;
	@Resource
	private VehicleModelMapper vehicleModelMapper;
	
	@Override
	public Result<Renewal> renewal(EmployeeForm ef, String license, String name) {
		Result<Renewal> result = _renewalByLicense(ef, license);
		if (!result.isSuccess())
			return result;
		Renewal renewal = result.attach();
		if (!renewal.getTips().getOwner().getName().equals(name))
			return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
		return Result.result(renewal);
	}
	
	@Override
	public Result<Renewal> renewal(EmployeeForm ef, String vin, String engine, String name) {
		Result<Renewal> result = _renewalByVinAndEngine(ef, vin, engine);
		if (!result.isSuccess())
			return result;
		Renewal renewal = result.attach();
		if (!renewal.getTips().getOwner().getName().equals(name))
			return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
		return Result.result(renewal);
	}
	
	private Result<Renewal> _renewalByLicense(EmployeeForm ef, String license) {
		Renewal renewal = renewalMapper.getByLicense(license);
		return null == renewal ? _flushRenewal(ef, license) : Result.result(renewal);
	}
	
	private Result<Renewal> _renewalByVinAndEngine(EmployeeForm ef, String vin, String engine) {
		Renewal renewal = renewalMapper.getByKey(vin);
		return null == renewal ? _flushRenewal(ef, vin, engine) : Result.result(renewal);
	}
	
	private Result<Renewal> _flushRenewal(EmployeeForm ef, String license) {
		Result<Renewal> result = biHuVehicle.renewal(ef, license);
		if (result.isSuccess())
			renewalMapper.insert(result.attach());
		return result;
	}
	
	private Result<Renewal> _flushRenewal(EmployeeForm ef, String vin, String engine) {
		Result<Renewal> result = biHuVehicle.renewal(ef, vin, engine);
		if (result.isSuccess())
			renewalMapper.insert(result.attach());
		return result;
	}

	@Override
	public Result<Void> order(List<Insurer> quote, List<Insurer> insure, EmployeeForm ef, VehiclePolicyTips tips, String vehicleId) {
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
				VehicleOrder order = new VehicleOrder(_orderId(tips.getLicense(), ef.getEmployee().getId(), insurer.getId()), 
						batchId, ef.getEmployee().getId(), ef.getApp().getId(), tenant.getTid(), insurer, lane.mark(), flag, tips);
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
		
		List<VehicleInfo> vehicleInfos = vehicleInfos(tips.getVin());
		VehicleInfo vehicleInfo = null;
		if (!CollectionUtils.isEmpty(vehicleInfos)) {
			for (VehicleInfo temp : vehicleInfos) {
				if (!temp.getId().equals(vehicleId))
					continue;
				vehicleInfo = temp;
			}
		}
		if (null == vehicleInfo)
			return Consts.RESULT.FAILURE;
		tips.bind(vehicleInfo);
		vehicleOrderMapper.deleteBatchOrder(batchId);
		if (null != bihuQuote) {
			Result<Void> result = biHuVehicle.order(ef, bihuQuote, bihuInsure, tips);
			if (!result.isSuccess())
				_orderRequestFailure(orders, bihuQuote, result.getDesc());
		}
		if (null != leBaoBaQuote) {
			Result<Void> result = leBaoBaVehicle.order(ef, leBaoBaQuote, leBaoBaInsure, tips);
			if (!result.isSuccess()) 	
				_orderRequestFailure(orders, leBaoBaQuote, result.getDesc());
		} 
		if (null != baotuQuote) {
			Result<Void> result = baoTuVehicle.order(ef, baotuQuote, baotuInsure, tips);
			if (!result.isSuccess()) 	
				_orderRequestFailure(orders, baotuQuote, result.getDesc());
		}
		for (VehicleOrder order : orders.values())
			vehicleOrderMapper.insert(order);
		return Result.success();
	}
	
	private void _orderRequestFailure(Map<Integer, VehicleOrder> orders, Set<Integer> insurers, String desc) {
		for (int insurerId : insurers) {
			VehicleOrder order = orders.get(insurerId);
			if (null == order)
				continue;
			order.setState(VehicleOrderState.SYSTEM_ERROR);
			order.setDesc(desc);
		}
	}
	
	@Override
	public Pager<VehicleOrderListInfo> orders(EmployeeForm ef, VehicleOrderSearcher searcher) {
		Pager<VehicleOrder> pager = vehicleOrderMapper.list(ef, searcher);
		List<VehicleOrderListInfo> result = new ArrayList<VehicleOrderListInfo>(pager.getList().size());
		for (VehicleOrder order : pager.getList()) {
			_orderInfo(ef, order);
			result.add(new VehicleOrderListInfo(order));
		}
		return new Pager<VehicleOrderListInfo>(pager.getTotal(), result);
	}
	
	@Override
	public Result<VehicleOrder> orderInfo(EmployeeForm ef, String id) {
		VehicleOrder order = vehicleOrderMapper.getByKey(id);
		if (null == order)
			return Result.result(BtkjCode.ORDER_NOT_EXIST);
		return _orderInfo(ef, order);
	}
	
	private Result<VehicleOrder> _orderInfo(EmployeeForm ef, VehicleOrder order) {
		VehicleOrderState state = order.getState();
		if (order.getLane() == Lane.BI_HU.mark()) {
			if (order.getState() == VehicleOrderState.QUOTING) {
				_quoteResult(ef, order);
				if (order.getState() == VehicleOrderState.QUOTE_SUCCESS) {
					bonusManager.calculateBonus(order);					// 报价成功计算手续费
					if (order.isInsure())								// 如果是核保了则状态变为核保中
						order.setState(VehicleOrderState.INSURING);
				}
			}
			if (order.getState() == VehicleOrderState.INSURING)
				_insureResult(ef, order);
			if (state != order.getState())								// 状态变了则更新保单
				vehicleOrderMapper.insert(order);
		}
		return Result.result(order);
	}
	
	private void _quoteResult(EmployeeForm employeeForm, VehicleOrder order) {
		Result<PolicySchema> result = biHuVehicle.quoteResult(employeeForm, order.getTips().getLicense(), order.getInsurerId());
		if (!result.isSuccess()) {
			if (result.getCode() == BtkjCode.QUOTE_FAILURE.id()) {
				order.setState(VehicleOrderState.QUOTE_FAILURE);
				order.setDesc(result.getDesc());
			}
		} else {
			order.setState(VehicleOrderState.QUOTE_SUCCESS);
			order.setDesc(result.getDesc());
			order.getTips().setSchema(result.attach());
		}
	}
	
	private void _insureResult(EmployeeForm ef, VehicleOrder order) {
		Result<PolicyDetail> detail = biHuVehicle.insureResult(ef, order.getTips().getLicense(), order.getInsurerId());
		if (!detail.isSuccess()) {
			if (detail.getCode() == BtkjCode.INSURE_FAILURE.id()) {
				order.setDesc(detail.getDesc());
				order.setState(VehicleOrderState.INSURE_FAILURE);
			} else if (detail.getCode() == BtkjCode.INSURE_REPEAT.id()) {
				order.setDesc(detail.getDesc());
				order.setState(VehicleOrderState.INSURE_FAILURE);
				if (VehicleUtils.isNewVehicleLicense(order.getTips().getLicense()))
					_flushRenewal(ef, order.getTips().getVin(), order.getTips().getEngine());
				else
					_flushRenewal(ef, order.getTips().getLicense());
			}
		} else {
			order.setDesc(detail.getDesc());
			order.setState(VehicleOrderState.INSURE_SUCCESS);
			order.getTips().setDetail(detail.attach());
		}
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
	public List<VehicleInfo> vehicleInfos(String vin) {
		return leBaoBaVehicle.vehicleInfos(vin);
	}
	
	@Override
	public List<VehicleBrand> vehicleBrands() {
		return vehicleBrandMapper.getAll();
	}
	
	@Override
	public List<VehicleDept> vehicleDepts(int brandId) {
		return vehicleDeptMapper.getByBrandId(brandId);
	}
	
	@Override
	public List<VehicleModel> vehicleModels(int deptId) {
		return vehicleModelMapper.getByDeptId(deptId);
	}
	
	@Override
	public Result<Void> deliveryEdit(String orderId, DeliveryInfo deliveryInfo) {
		VehicleOrder order = vehicleOrderMapper.getByKey(orderId);
		if (null == order)
			return BtkjConsts.RESULT.ORDER_NOT_EXIST;
		if (order.getState() != VehicleOrderState.INSURE_SUCCESS && order.getState() != VehicleOrderState.ISSUE_APPOINTED)
			return BtkjConsts.RESULT.ORDER_STATE_ERROR;
		order.setDeliveryInfo(deliveryInfo);
		order.setState(VehicleOrderState.ISSUE_APPOINTED);
		vehicleOrderMapper.insert(order);
		return Consts.RESULT.OK;
	}
	
	@Override
	public long orderNum(int employeeId, int begin, int end, int stateMod) {
		return vehicleOrderMapper.orderNum(employeeId, begin, end, stateMod);
	}
	
	private String _orderId(String license, int employeeId, int insurerId) {
		return employeeId + Consts.SYMBOL_UNDERLINE + license + Consts.SYMBOL_UNDERLINE + insurerId;
	}
	
	private String _batchId(String license, int employeeId) {
		return employeeId + Consts.SYMBOL_UNDERLINE + license;
	}
}
