package org.btkj.vehicle.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.baotu.vehicle.api.BaoTuVehicle;
import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.config.api.ConfigService;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.DeliveryInfo;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.PolicyDetail;
import org.btkj.pojo.bo.PolicySchema;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.po.Insurer;
import org.btkj.pojo.po.Renewal;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.VehicleBrand;
import org.btkj.pojo.po.VehicleDept;
import org.btkj.pojo.po.VehicleModel;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.pojo.vo.VehicleInfo;
import org.btkj.pojo.vo.VehiclePolicyTips;
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
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.NumberUtil;
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
	private ConfigService configService;
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
	public Result<Renewal> renewal(Employee employee, String license, String name) {
		Result<Renewal> result = _renewalByLicense(employee, license);
		if (!result.isSuccess())
			return result;
		Renewal renewal = result.attach();
		if (!renewal.getTips().getOwner().getName().equals(name))
			return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
		return Result.result(renewal);
	}
	
	@Override
	public Result<Renewal> renewal(Employee employee, String vin, String engine, String name) {
		Result<Renewal> result = _renewalByVinAndEngine(employee, vin, engine);
		if (!result.isSuccess())
			return result;
		Renewal renewal = result.attach();
		if (!renewal.getTips().getOwner().getName().equals(name))
			return Result.result(BtkjCode.CAR_OWNER_NAME_ERROR);
		return Result.result(renewal);
	}
	
	private Result<Renewal> _renewalByLicense(Employee employee, String license) {
		Renewal renewal = renewalMapper.getByLicense(license);
		return null == renewal ? _flushRenewal(employee, license) : Result.result(renewal);
	}
	
	private Result<Renewal> _renewalByVinAndEngine(Employee employee, String vin, String engine) {
		Renewal renewal = renewalMapper.getByKey(vin);
		return null == renewal ? _flushRenewal(employee, vin, engine) : Result.result(renewal);
	}
	
	private Result<Renewal> _flushRenewal(Employee employee, String license) {
		Result<Renewal> result = biHuVehicle.renewal(employee.getUid(), employee.getTid(), license, configService.area(employee.getRegion()).getBiHuId());
		if (result.isSuccess())
			_saveRenewal(result.attach());
		return result;
	}
	
	private Result<Renewal> _flushRenewal(Employee employee, String vin, String engine) {
		Result<Renewal> result = biHuVehicle.renewal(employee.getUid(), employee.getTid(), vin, engine, configService.area(employee.getRegion()).getBiHuId());
		if (result.isSuccess()) 
			_saveRenewal(result.attach());
		return result;
	}
	
	private void _saveRenewal(Renewal renewal) {
		if (0 != renewal.getInsurerId()) {
			Insurer insurer = configService.getInsurerById(renewal.getInsurerId());
			if (null != insurer) {
				renewal.setInsurerId(insurer.getId());
				renewal.setInsurerName(insurer.getName());
				renewal.setInsurerIcon(insurer.getIcon());
			}
		}
		renewalMapper.insert(renewal);
	}

	@Override
	public Result<Void> order(int quoteMod, int insureMod, Employee employee, VehiclePolicyTips tips, String vehicleId) {
		ICode code = rule.orderCheck(employee, tips.getSchema());
		if (code != Code.OK)
			return Result.result(code);
		Map<Integer, Insurer> quoteMap = new HashMap<Integer, Insurer>();
		for (Insurer insurer : configService.insurers(NumberUtil.splitIntoPowerOfTwoList(quoteMod)))
			quoteMap.put(insurer.getId(), insurer);
		Set<Integer> insure = NumberUtil.splitIntoPowerOfTwoSet(insureMod);
		List<Route> routes = routeMapper.getByTid(employee.getTid());
		String batchId = _batchId(tips, employee);
		int biHuQuoteMod = 0;
		int biHuInsureMod = 0;
		Map<Lane, Map<Integer, VehicleOrder>> orders = new HashMap<Lane, Map<Integer, VehicleOrder>>();
		a : for (Entry<Integer, Insurer> entry : quoteMap.entrySet()) {
			Iterator<Route> itr = routes.iterator();
			while (itr.hasNext()) {
				Route route = itr.next();
				if (route.getInsurerId() != entry.getValue().getId())
					continue;
				itr.remove();
				Lane lane = Lane.match(route.getLane());
				Map<Integer, VehicleOrder> temp = orders.get(lane);
				if (null == temp) {
					temp = new HashMap<Integer, VehicleOrder>();
					orders.put(lane, temp);
				}
				boolean submit = null == insure ? false : insure.remove(entry.getKey());
				temp.put(entry.getKey(), new VehicleOrder(batchId, quoteMod, insureMod, employee, entry.getValue(), lane.mark(), submit, tips));
				switch (lane) {
				case BI_HU:
					if (0 == entry.getValue().getBiHuId())
						return BtkjConsts.RESULT.LANE_CONFIG_ERROR;
					biHuQuoteMod |= entry.getKey();
					if (submit)
						biHuInsureMod |= entry.getKey();
					break;
				case LE_BAO_BA:
					break;
				default:
					break;
				}
				continue a;
			}
			return BtkjConsts.RESULT.LANE_CONFIG_ERROR;
		}
		
		List<VehicleInfo> vehicleInfos = vehicleInfos(tips.getVin());
		VehicleInfo vehicleInfo = null;
		if (!CollectionUtil.isEmpty(vehicleInfos)) {
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
		if (0 != biHuQuoteMod) {
			Result<Void> result = biHuVehicle.order(employee, biHuQuoteMod, biHuInsureMod, tips, configService.area(employee.getRegion()).getBiHuId());
			if (!result.isSuccess())
				_orderRequestFailure(orders.get(Lane.BI_HU), result.getDesc());
		}
		for (Map<Integer, VehicleOrder> map : orders.values()) {
			for (VehicleOrder order : map.values())
				vehicleOrderMapper.insert(order);
		}
		return Result.success();
	}
	
	private void _orderRequestFailure(Map<Integer, VehicleOrder> orders, String desc) {
		for (VehicleOrder order : orders.values()) {
			order.setState(VehicleOrderState.SYSTEM_ERROR);
			order.setDesc(desc);
		}
	}
	
	@Override
	public Pager<VehicleOrderListInfo> orders(Employee employee, VehicleOrderSearcher searcher) {
		Pager<VehicleOrder> pager = vehicleOrderMapper.paging(searcher);
		List<VehicleOrderListInfo> result = new ArrayList<VehicleOrderListInfo>(pager.getList().size());
		for (VehicleOrder order : pager.getList()) {
			_orderInfo(employee, order);
			result.add(new VehicleOrderListInfo(order));
		}
		return new Pager<VehicleOrderListInfo>(pager.getTotal(), result);
	}
	
	@Override
	public Result<VehicleOrder> orderInfo(Employee employee, String id) {
		VehicleOrder order = vehicleOrderMapper.getByKey(id);
		if (null == order)
			return Result.result(BtkjCode.ORDER_NOT_EXIST);
		return _orderInfo(employee, order);
	}
	
	private Result<VehicleOrder> _orderInfo(Employee employee, VehicleOrder order) {
		VehicleOrderState state = order.getState();
		if (order.getLane() == Lane.BI_HU.mark()) {
			if (order.getState() == VehicleOrderState.QUOTING) {
				_quoteResult(employee, order);
				if (order.getState() == VehicleOrderState.QUOTE_SUCCESS) {
					bonusManager.calculateBonus(order);					// 报价成功计算手续费
					if (order.isInsure())								// 如果是核保了则状态变为核保中
						order.setState(VehicleOrderState.INSURING);
				}
			}
			if (order.getState() == VehicleOrderState.INSURING)
				_insureResult(employee, order);
			if (state != order.getState())								// 状态变了则更新保单
				vehicleOrderMapper.insert(order);
		}
		return Result.result(order);
	}
	
	private void _quoteResult(Employee employee, VehicleOrder order) {
		Result<PolicySchema> result = biHuVehicle.quoteResult(employee.getUid(), employee.getTid(), order.getTips().getLicense(), configService.getInsurerById(order.getInsurerId()).getBiHuId());
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
	
	private void _insureResult(Employee employee, VehicleOrder order) {
		Result<PolicyDetail> detail = biHuVehicle.insureResult(employee.getUid(), employee.getTid(), order.getTips().getLicense(), configService.getInsurerById(order.getInsurerId()).getBiHuId());
		if (!detail.isSuccess()) {
			if (detail.getCode() == BtkjCode.INSURE_FAILURE.id()) {
				order.setDesc(detail.getDesc());
				order.setState(VehicleOrderState.INSURE_FAILURE);
			} else if (detail.getCode() == BtkjCode.INSURE_REPEAT.id()) {
				order.setDesc(detail.getDesc());
				order.setState(VehicleOrderState.INSURE_FAILURE);
				if (VehicleUtils.isNewVehicleLicense(order.getTips().getLicense()))
					_flushRenewal(employee, order.getTips().getVin(), order.getTips().getEngine());
				else
					_flushRenewal(employee, order.getTips().getLicense());
			}
		} else {
			order.setDesc(detail.getDesc());
			order.setState(VehicleOrderState.INSURE_SUCCESS);
			order.getTips().setDetail(detail.attach());
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> insurers(TenantPO tenant) {
		List<Route> list = routeMapper.getByTid(tenant.getTid());
		if (CollectionUtil.isEmpty(list))
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
		return new ArrayList<VehicleBrand>(vehicleBrandMapper.getAll().values());
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
	
	private String _batchId(VehiclePolicyTips tips, Employee employee) {
		return employee.getId() + Consts.SYMBOL_UNDERLINE + tips.getLicense();
	}
}
