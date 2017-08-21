package org.btkj.vehicle.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.btkj.pojo.VehicleUtil;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.VehicleBrand;
import org.btkj.pojo.entity.VehicleDept;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.model.DeliveryInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.model.VehicleAuditModel;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.pojo.param.VehicleOrderParam;
import org.btkj.vehicle.LeBaoBaOrderTask;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.mongo.RenewalMapper;
import org.btkj.vehicle.mongo.VehicleOrderMapper;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.TenantInsurer;
import org.btkj.vehicle.pojo.model.VehicleOrderListInfo;
import org.btkj.vehicle.pojo.param.VehicleOrdersParam;
import org.btkj.vehicle.redis.TenantInsurerMapper;
import org.btkj.vehicle.redis.VehicleBrandMapper;
import org.btkj.vehicle.redis.VehicleDeptMapper;
import org.btkj.vehicle.redis.VehicleModelMapper;
import org.btkj.vehicle.rule.Rule;
import org.btkj.vehicle.rule.bonus.BonusManager;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.consts.code.ICode;
import org.rapid.util.common.message.Result;
import org.rapid.util.concurrent.ExecutorService;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.NumberUtil;
import org.rapid.util.lang.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
	
	@Resource
	private Rule rule;
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
	private ExecutorService executorService;
	@Resource
	private VehicleDeptMapper vehicleDeptMapper;
	@Resource
	private VehicleBrandMapper vehicleBrandMapper;
	@Resource
	private VehicleModelMapper vehicleModelMapper;
	@Resource
	private VehicleOrderMapper vehicleOrderMapper;
	@Resource
	private TenantInsurerMapper tenantInsurerMapper;
	
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
		return null == renewal ? _flushRenewal(employee.getTenant(), employee.getUid(), employee.getRegion(), license) : Result.result(renewal);
	}
	
	private Result<Renewal> _renewalByVinAndEngine(Employee employee, String vin, String engine) {
		Renewal renewal = renewalMapper.getByKey(vin);
		return null == renewal ? _flushRenewal(employee.getTenant(), employee.getUid(), employee.getRegion(), vin, engine) : Result.result(renewal);
	}
	
	private Result<Renewal> _flushRenewal(TenantPO tenant, int uid, int region, String license) {
		Result<Renewal> result = biHuVehicle.renewal(tenant, uid, license, configService.area(region).getBiHuId());
		if (result.isSuccess())
			_saveRenewal(result.attach());
		return result;
	}
	
	private Result<Renewal> _flushRenewal(TenantPO tenant, int uid, int region, String vin, String engine) {
		Result<Renewal> result = biHuVehicle.renewal(tenant, uid, vin, engine, configService.area(region).getBiHuId());
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
	public Result<Void> order(AppPO app, TenantPO tenant, UserPO user, EmployeePO employee, VehicleOrderParam param) {
		ICode code = rule.orderCheck(tenant.getRegion(), param);
		if (code != Code.OK)
			return Result.result(code);
		int biHuQuoteMod = 0;
		int biHuInsureMod = 0;
		Map<String, Boolean> leBaoBa = new HashMap<String, Boolean>();
		VehicleInfo vehicleInfo = _vehicleInfo(tenant, param);
		Set<Integer> insures = NumberUtil.splitIntoPowerOfTwoSet(param.getInsureMod());
		Map<Integer, Insurer> insurers = configService.insurers(NumberUtil.splitIntoPowerOfTwoSet(param.getQuoteMod()));
		Map<Lane, Map<Object, VehicleOrder>> orders = new HashMap<Lane, Map<Object, VehicleOrder>>();
		for (TenantInsurer tinsurer : tenantInsurerMapper.getByTid(tenant.getTid()).values()) {
			Insurer insurer = insurers.remove(tinsurer.getInsurerId());
			if (null == insurer)										
				continue;
			Lane lane = Lane.match(tinsurer.getLane());
			Map<Object, VehicleOrder> temp = orders.get(lane);
			if (null == temp) {
				temp = new HashMap<Object, VehicleOrder>();
				orders.put(lane, temp);
			}
			boolean insure = null == insures ? false : insures.remove(insurer.getId());
			VehicleOrder order = new VehicleOrder(app, tenant, user, employee, insurer, lane.mark(), insure, param, vehicleInfo);
			switch (lane) {
			case BI_HU:
				if (0 == insurer.getBiHuId())
					return BtkjConsts.RESULT.INSURER_UNSUPPORT_BI_HU;
				if (!StringUtil.hasText(tenant.getBiHuAgent(), tenant.getBiHuKey()))
					return BtkjConsts.RESULT.LANE_BI_HU_NOT_OPENED;
				biHuQuoteMod |= insurer.getBiHuId();
				if (insure)
					biHuInsureMod |= insurer.getBiHuId();
				temp.put(insurer.getBiHuId(), order);
				break;
			case LE_BAO_BA:
				if (!StringUtil.hasText(insurer.getLeBaoBaId()))
					return BtkjConsts.RESULT.INSURER_UNSUPPORT_LE_BAO_BA;
				if (!StringUtil.hasText(tenant.getLeBaoBaPassword(), tenant.getLeBaoBaUsername()))
					return BtkjConsts.RESULT.LANE_LE_BAO_BA_NOT_OPENED;
				leBaoBa.put(insurer.getLeBaoBaId(), insure);
				temp.put(insurer.getLeBaoBaId(), order);
				break;
			default:
				break;
			}
		}
		
		vehicleOrderMapper.delete(employee.getId(), param.getLicense());
		if (0 != biHuQuoteMod) {
			Result<Void> result = biHuVehicle.order(tenant.getBiHuAgent(), tenant.getBiHuKey(), user.getUid(), biHuQuoteMod, biHuInsureMod, configService.area(tenant.getRegion()).getBiHuId(), param);
			if (!result.isSuccess())
				_orderRequestFailure(orders.get(Lane.BI_HU), result.getDesc());
		}
		vehicleOrderMapper.insert(orders);			// 因为乐保吧报价是异步的，因此在报价之前要先把数据入库
		for (Entry<String, Boolean> entry : leBaoBa.entrySet()) {
			VehicleOrder order = orders.get(Lane.LE_BAO_BA).get(entry.getKey());
			executorService.execute(new LeBaoBaOrderTask(tenant, entry.getKey(), entry.getValue(), order, param, leBaoBaVehicle, vehicleOrderMapper));
		}
		return Result.success();
	}
	
	private VehicleInfo _vehicleInfo(TenantPO tenant, VehicleOrderParam param) {
		List<VehicleInfo> vehicleInfos = vehicleInfos(tenant, param.getVin());
		if (CollectionUtil.isEmpty(vehicleInfos))
			return null;
		VehicleInfo vehicleInfo = null;
		if (StringUtil.hasText(param.getVehicleId())) {				// 指定了乐保吧ID
			for (VehicleInfo temp : vehicleInfos) {
				if (temp.getId().equals(param.getVehicleId())) 
					return temp;
			}
			logger.warn("车架号 - {} 对应的乐保吧车型 - {}不存在！", param.getVin(), param.getVehicleId());
		} 
		if (StringUtil.hasText(param.getTransmissionName())) {		// 根据壁虎返回的变速器类型获取对应乐保吧的车型
			for (VehicleInfo temp : vehicleInfos) {
				if (temp.getTransmissionName().equals(param.getTransmissionName()))
					return temp;
			}
			logger.warn("车架号- {} 找不到变速器类型为 - {} 的乐保吧车型！", param.getVin(), param.getTransmissionName());
		} 
		String desc = new StringBuilder(param.getVin()).append("/").append(StringUtil.hasText(param.getVehicleId()) ? param.getVehicleId() : "")
						.append("/").append(StringUtil.hasText(param.getTransmissionName()) ? param.getTransmissionName() : "").toString();
		logger.info("{} 没有找到指定乐保吧车型，系统自动识别车型！", desc);
		BigDecimal price = null;
		for (VehicleInfo temp : vehicleInfos) {
			if (!StringUtil.hasText(temp.getPrice())) {
				logger.warn("车架号 - {} 发现购置价为 0 的车型！", param.getVin());
				return temp;
			}
			if (null == price) {
				price = new BigDecimal(temp.getPrice());
				vehicleInfo = temp;
			} else {
				BigDecimal decimal = new BigDecimal(temp.getPrice());
				if (price.compareTo(decimal) > 0) {
					price = decimal;
					vehicleInfo = temp;
				}
			}
		}
		return vehicleInfo;
	}
	
	private void _orderRequestFailure(Map<Object, VehicleOrder> orders, String desc) {
		for (VehicleOrder order : orders.values()) {
			order.setState(VehicleOrderState.QUOTE_FAILURE);
			order.setDesc(desc);
		}
	}
	
	@Override
	public Pager<VehicleOrderListInfo> orders(TenantPO tenant,  VehicleOrdersParam param) {
		Pager<VehicleOrder> pager = vehicleOrderMapper.orders(param);
		List<VehicleOrderListInfo> result = new ArrayList<VehicleOrderListInfo>(pager.getList().size());
		for (VehicleOrder order : pager.getList()) {
			_orderInfo(tenant, param.getUid(), tenant.getRegion(), order);
			result.add(new VehicleOrderListInfo(order));
		}
		return new Pager<VehicleOrderListInfo>(pager.getTotal(), result);
	}
	
	@Override
	public Result<VehicleOrder> orderInfo(Employee employee, String id) {
		VehicleOrder order = vehicleOrderMapper.getByKey(id);
		if (null == order)
			return BtkjConsts.RESULT.ORDER_NOT_EXIST;
		if (order.getEmployeeId() != employee.getId())
			return Consts.RESULT.FORBID;
		return _orderInfo(employee.getTenant(), employee.getUid(), employee.getRegion(), order);
	}
	
	private Result<VehicleOrder> _orderInfo(TenantPO tenant, int uid, int region, VehicleOrder order) {
		VehicleOrderState state = order.getState();
		if (order.getLane() == Lane.BI_HU.mark()) {
			if (order.getState() == VehicleOrderState.QUOTING) {
				_quoteResult(tenant, uid, order);
				if (order.getState() == VehicleOrderState.QUOTE_SUCCESS) {
					bonusManager.calculateBonus(order);					// 报价成功计算手续费
					if (order.isInsure())								// 如果是核保了则状态变为核保中
						order.setState(VehicleOrderState.INSURING);
				}
			}
			if (order.getState() == VehicleOrderState.INSURING)
				_insureResult(tenant, uid, region, order);
			if (state != order.getState())								// 状态变了则更新保单
				vehicleOrderMapper.insert(order);
		}
		return Result.result(order);
	}
	
	private void _quoteResult(TenantPO tenant, int uid, VehicleOrder order) {
		Result<PolicySchema> result = biHuVehicle.quoteResult(tenant, uid, order.getTips().getLicense(), configService.getInsurerById(order.getInsurerId()).getBiHuId());
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
	
	private void _insureResult(TenantPO tenant, int uid, int region, VehicleOrder order) {
		Result<VehicleAuditModel> result = biHuVehicle.insureResult(tenant, uid, order.getTips().getLicense(), configService.getInsurerById(order.getInsurerId()).getBiHuId());
		if (!result.isSuccess()) {
			if (result.getCode() == BtkjCode.INSURE_FAILURE.id()) {
				order.setDesc(result.getDesc());
				order.setState(VehicleOrderState.INSURE_FAILURE);
			} else if (result.getCode() == BtkjCode.INSURE_REPEAT.id()) {
				order.setDesc(result.getDesc());
				order.setState(VehicleOrderState.INSURE_FAILURE);
				if (VehicleUtil.isNewVehicleLicense(order.getTips().getLicense()))
					_flushRenewal(tenant, uid, region, order.getTips().getVin(), order.getTips().getEngine());
				else
					_flushRenewal(tenant, uid, region, order.getTips().getLicense());
			}
		} else {
			order.setDesc(result.getDesc());
			order.setState(VehicleOrderState.INSURE_SUCCESS);
			PolicySchema schema = order.getTips().getSchema();
			schema.setCommercialNo(result.attach().getCommercialNo());
			schema.setCompulsoryNo(result.attach().getCompulsoryNo());
		}
	}
	
	@Override
	public List<Integer> insurers(int tid) {
		Map<String, TenantInsurer> map = tenantInsurerMapper.getByTid(tid);
		if (CollectionUtil.isEmpty(map))
			return CollectionUtil.emptyArrayList();
		List<Integer> l = new ArrayList<Integer>();
		for (TenantInsurer route : map.values())
			l.add(route.getInsurerId());
		return l;
	}
	
	@Override
	public List<VehicleInfo> vehicleInfos(TenantPO tenant, String vin) {
		return leBaoBaVehicle.vehicleInfos(tenant.getLeBaoBaUsername(), tenant.getLeBaoBaPassword(), vin);
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
}
