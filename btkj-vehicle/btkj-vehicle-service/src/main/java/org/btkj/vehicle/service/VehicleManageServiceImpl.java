package org.btkj.vehicle.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.VehicleBrand;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.entity.VehicleDept;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.info.JianJiePoliciesInfo;
import org.btkj.pojo.info.JianJiePoliciesInfo.BaseInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.vehicle.EntityGenerator;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.mongo.VehicleOrderMapper;
import org.btkj.vehicle.mongo.VehiclePolicyMapper;
import org.btkj.vehicle.mybatis.Tx;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.TenantInsurer;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.entity.VehiclePolicy.SalesmanMark;
import org.btkj.vehicle.pojo.param.BonusManageConfigEditParam;
import org.btkj.vehicle.pojo.param.BonusScaleConfigEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientEditParam;
import org.btkj.vehicle.pojo.param.VehicleBrandEditParam;
import org.btkj.vehicle.pojo.param.VehicleDeptEditParam;
import org.btkj.vehicle.pojo.param.VehicleModelEditParam;
import org.btkj.vehicle.pojo.param.VehicleOrdersParam;
import org.btkj.vehicle.pojo.param.VehiclePoliciesParam;
import org.btkj.vehicle.redis.BonusManageConfigMapper;
import org.btkj.vehicle.redis.BonusScaleConfigMapper;
import org.btkj.vehicle.redis.TenantInsurerMapper;
import org.btkj.vehicle.redis.VehicleBrandMapper;
import org.btkj.vehicle.redis.VehicleCoefficientMapper;
import org.btkj.vehicle.redis.VehicleDeptMapper;
import org.btkj.vehicle.redis.VehicleModelMapper;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.uuid.AlternativeJdkIdGenerator;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("vehicleManageService")
public class VehicleManageServiceImpl implements VehicleManageService {
	
	private static Logger logger = LoggerFactory.getLogger(VehicleManageServiceImpl.class);
	
	@Resource
	private Tx tx;
	@Resource
	private DistributeLock distributeLock;
	@Resource
	private VehicleDeptMapper vehicleDeptMapper;
	@Resource
	private VehicleModelMapper vehicleModelMapper;
	@Resource
	private VehicleOrderMapper vehicleOrderMapper;
	@Resource
	private VehicleBrandMapper vehicleBrandMapper;
	@Resource
	private VehiclePolicyMapper vehiclePolicyMapper;
	@Resource
	private TenantInsurerMapper tenantInsurerMapper;
	@Resource
	private BonusScaleConfigMapper bonusScaleConfigMapper;
	@Resource
	private BonusManageConfigMapper bonusManageConfigMapper;
	@Resource
	private VehicleCoefficientMapper vehicleCoefficientMapper;
	
	@Override
	public List<VehicleCoefficient> coefficients(int tid) {
		return vehicleCoefficientMapper.getByTid(tid);
	}
	
	@Override
	public Result<?> poundageCoefficientEdit(PoundageCoefficientEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			try {
				VehicleCoefficient coefficient = tx.coefficientAdd(param);
				vehicleCoefficientMapper.flush(coefficient);
				return Result.result(Code.OK, coefficient.getId());
			} catch (BusinessException e) {
				return Result.result(e.getCode());
			}
		case UPDATE:
			try {
				VehicleCoefficient coefficient = tx.coefficientUpdate(param);
				vehicleCoefficientMapper.flush(coefficient);
				return Consts.RESULT.OK;
			} catch (BusinessException e) {
				return Result.result(e.getCode());
			}
		case DELETE:
			VehicleCoefficient coefficient = vehicleCoefficientMapper.getByKey(param.getId());
			if (null == coefficient)
				return BtkjConsts.RESULT.COEFFICIENT_NOT_EXIST;
			if (coefficient.getTid() != param.getTid())
				return Consts.RESULT.FORBID;
			vehicleCoefficientMapper.delete(param.getId());
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}

	@Override
	public Map<String, BonusManageConfig> bonusManageConfigs(int tid) {
		return bonusManageConfigMapper.getByTid(tid);
	}
	
	@Override
	public Result<Void> bonusManageConfigEdit(int tid, BonusManageConfigEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			BonusManageConfig config = EntityGenerator.newBonusManageConfig(tid, param.getConfigType(), param.getTeamDepth(), param.getRate());
			try {
				bonusManageConfigMapper.insert(config);
			} catch (DuplicateKeyException e) {
				return Consts.RESULT.FAILURE;
			}
			return Consts.RESULT.OK;
		case UPDATE:
			config = bonusManageConfigMapper.getByKey(param.getId());
			if (null == config)
				return BtkjConsts.RESULT.BONUS_MANAGE_CONFIG_NOT_EXIST;
			if (tid != config.getTid())
				return Consts.RESULT.FORBID;
			config.setRate(param.getRate());
			config.setUpdated(DateUtil.currentTime());
			bonusManageConfigMapper.update(config);
			return Consts.RESULT.OK;
		case DELETE:
			config = bonusManageConfigMapper.getByKey(param.getId());
			if (null == config)
				return BtkjConsts.RESULT.BONUS_MANAGE_CONFIG_NOT_EXIST;
			if (tid != config.getTid())
				return Consts.RESULT.FORBID;
			bonusManageConfigMapper.delete(config);
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
	
	@Override
	public List<BonusScaleConfig> bonusScaleConfigs(int tid) {
		return bonusScaleConfigMapper.getByTid(tid);
	}
	
	@Override
	public Result<?> bonusScaleConfigEdit(int tid, BonusScaleConfigEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			try {
				BonusScaleConfig config = tx.bonusScaleConfigAdd(tid, param);
				bonusScaleConfigMapper.flush(config);
				return Result.result(Code.OK, config.getId());
			} catch (BusinessException e) {
				return Result.result(e.getCode());
			}
		case UPDATE:
			try {
				BonusScaleConfig config = tx.bonusScaleConfigUpdate(tid, param);
				bonusScaleConfigMapper.flush(config);
				return Consts.RESULT.OK;
			} catch (BusinessException e) {
				return Result.result(e.getCode());
			}
		case DELETE:
			BonusScaleConfig config = bonusScaleConfigMapper.getByKey(param.getId());
			if (null == config)
				return BtkjConsts.RESULT.BONUS_SCALE_CONFIG_NOT_EXIST;
			if (tid != config.getTid())
				return Consts.RESULT.FORBID;
			bonusScaleConfigMapper.delete(param.getId());
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
	
	@Override
	public void jianJieSynchronize(EmployeePO employee, Map<Integer, EmployeeTip> employees, JianJiePoliciesInfo info) {
		List<VehiclePolicy> policies = new ArrayList<VehiclePolicy>();
		Map<String, BaseInfo> cms = new HashMap<String, BaseInfo>();
		Map<String, BaseInfo> cmps = new HashMap<String, BaseInfo>();
		Set<String> cmNos = new HashSet<String>();
		Set<String> cmpNos = new HashSet<String>();
		for (BaseInfo temp : info.getResult()) {
			if (temp.getBdType().equals(InsuranceType.COMMERCIAL.title())) {
				cms.put(temp.getBDH(), temp);
				cmNos.add(temp.getTBDH());
			} else if (temp.getBdType().equals(InsuranceType.COMPULSORY.title())) {
				cmps.put(temp.getBDH(), temp);
				cmpNos.add(temp.getTBDH());
			} else
				throw new RuntimeException("未知的简捷保单类型  : " + temp.getBdType());
		}
		List<VehicleOrder> updates = new ArrayList<VehicleOrder>();
		_jianJiePolicyProcess(employees, employee, InsuranceType.COMMERCIAL, cmNos, cms, cmpNos, cmps, updates, policies);
		_jianJiePolicyProcess(employees, employee, InsuranceType.COMPULSORY, cmpNos, cmps, cmNos, cms, updates, policies);
		vehicleOrderMapper.issuedUpdate(updates);
		vehiclePolicyMapper.batchInsert(policies);
	}
	
	private void _jianJiePolicyProcess(Map<Integer, EmployeeTip> employees, EmployeePO employee, InsuranceType insuranceType, Set<String> deliverNos, Map<String, BaseInfo> processing, Set<String> relationDeliverNos, Map<String, BaseInfo> relation, List<VehicleOrder> updates, List<VehiclePolicy> policies) {  
		List<VehicleOrder> orders = CollectionUtil.isEmpty(deliverNos) ? CollectionUtil.emptyArrayList() : vehicleOrderMapper.getByDeliverNos(insuranceType, employee.getTid(), deliverNos);
		Iterator<Entry<String, BaseInfo>> iterator = processing.entrySet().iterator();
		while (iterator.hasNext()) {
			String policyId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
			BaseInfo info = iterator.next().getValue();
			iterator.remove();
			Iterator<VehicleOrder> itr = orders.iterator();
			VehicleOrder order = null;
			while (itr.hasNext()) {
				VehicleOrder vo = itr.next();
				String cno = insuranceType == InsuranceType.COMMERCIAL ? vo.getTips().getSchema().getCommercialNo() : vo.getTips().getSchema().getCompulsoryNo();
				if (!StringUtil.hasText(cno) || !cno.equals(info.getTBDH()))
					continue;
				order = vo;
				order.setState(VehicleOrderState.ISSUED);
				order.setPolicyId(policyId);
				updates.add(order);
				itr.remove();
				break;
			}
			BaseInfo relationInfo = null;
			if (null != info.getRelationPolicyNo()) {
				relationDeliverNos.remove(info.getTBDH());
				relationInfo = relation.remove(info.getRelationPolicyNo());
				if (null == relationInfo) 
					logger.error("简捷保单 - {}" + info.getBdType() + "险关联单丢失！", policyId);
				if (info.getCompanyId() != relationInfo.getCompanyId() || 0 == info.getCompanyId())
					logger.error("简捷保单 - {} 关联单险企不匹配", policyId);
				if (!info.getGsUser().equals(relationInfo.getGsUser()))
					logger.error("简捷保单 - {} 关联单业务员归属不匹配", policyId);
			}
			TenantInsurer route = tenantInsurerMapper.getByTidAndJianJieId(employee.getTid(), info.getCompanyId());
			if (null == route)
				logger.error("简捷保单 - {} 险企 - {} 不存在对应的保途险企映射", policyId, info.getCompanyId());
			if (null != order && route.getInsurerId() != order.getInsurerId())
				logger.error("简捷保单 - {} 险企 - {} 和保途订单 险企ID不匹配, 以保途订单险企为准！", policyId, info.getCompanyId());
			VehiclePolicy policy = EntityGenerator.newVehiclePolicy(employee, route.getInsurerId(), policyId, order, info, relationInfo);
			_processJianJieGsUser(employee.getTid(), employees, policy, order, info.getGsUser());
			policies.add(policy);
		}
	}
	
	private void _processJianJieGsUser(int tid, Map<Integer, EmployeeTip> employees, VehiclePolicy policy, VehicleOrder order, String gsUser) {
		if (null != order) {
			policy.setMark(SalesmanMark.NORMAL);
			policy.setSalesmanId(order.getEmployeeId());
			policy.setSalesman(order.getSalesman());
			policy.setSalesmanMobile(order.getSalesmanMobile());
		} else {
			int employeeId = 0;
			try {
				employeeId = Integer.valueOf(gsUser.substring(gsUser.indexOf(":") + 1, gsUser.length() - 1));
			} catch (NumberFormatException e) {
				logger.error("保单 - {} 简捷用户雇员id解析出错 - {}", policy.get_id(), gsUser);
				policy.setMark(SalesmanMark.NONE);
				return;
			}
			policy.setSalesmanId(employeeId);
			EmployeeTip employee = employees.get(employeeId);
			if (null == employee) {
				logger.error("保单 - {} 没有指定业务员！", policy.get_id());
				policy.setMark(SalesmanMark.NOT_EXIST);
			} else {
				if (employee.getTid() != tid) {
					logger.error("非保途保单 - {} 业务员归属错误，当前代理公司 - {}，业务员所在代理公司 - {}；业务员 - {}！", policy.get_id(), tid, employee.getTid(), employee.getId());
					policy.setMark(SalesmanMark.UNSUITABLE);
				} else 
					policy.setMark(SalesmanMark.NORMAL);
				policy.setSalesman(employee.getName());
				policy.setSalesmanMobile(employee.getMobile());
			}
		}
	}
	
	@Override
	public void vehicleOrderIssue(int tid) {
		
	}
	
	@Override
	public List<VehicleBrand> brands() {
		return new ArrayList<VehicleBrand>(vehicleBrandMapper.getAll().values());
	}
	
	@Override
	public Result<?> brandEdit(VehicleBrandEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			VehicleBrand brand = EntityGenerator.newVehicleBrand(param.getName());
			vehicleBrandMapper.insert(brand);
			return Result.result(Code.OK, brand.getId());
		case UPDATE:
			brand = vehicleBrandMapper.getByKey(param.getId());
			if (null == brand)
				return BtkjConsts.RESULT.VEHICLE_BRAND_NOT_EXIST;
			brand.setName(param.getName());
			brand.setUpdated(DateUtil.currentTime());
			vehicleBrandMapper.update(brand);
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
		
	}
	
	@Override
	public List<VehicleDept> depts(int brandId) {
		return vehicleDeptMapper.getByBrandId(brandId);
	}
	
	@Override
	public Result<?> deptEdit(VehicleDeptEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			VehicleBrand brand = vehicleBrandMapper.getByKey(param.getBrandId());
			if (null == brand)
				return BtkjConsts.RESULT.VEHICLE_BRAND_NOT_EXIST;
			VehicleDept dept = EntityGenerator.newVehicleDept(param.getBrandId(), param.getName());
			vehicleDeptMapper.insert(dept);
			return Result.result(Code.OK, dept.getId());
		case UPDATE:
			dept = vehicleDeptMapper.getByKey(param.getId());
			if (null == dept)
				return BtkjConsts.RESULT.VEHICLE_DEPT_NOT_EXIST;
			dept.setName(param.getName());
			dept.setUpdated(DateUtil.currentTime());
			vehicleDeptMapper.update(dept);
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
	
	@Override
	public List<VehicleModel> models(int deptId) {
		return vehicleModelMapper.getByDeptId(deptId);
	}
	
	@Override
	public Result<?> modelEdit(VehicleModelEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			VehicleDept dept = vehicleDeptMapper.getByKey(param.getDeptId());
			if (null == dept)
				return BtkjConsts.RESULT.VEHICLE_DEPT_NOT_EXIST;
			VehicleModel model = EntityGenerator.newVehicleModel(param.getDeptId(), param.getName());
			vehicleModelMapper.insert(model);
			return Result.result(Code.OK, model.getId());
		case UPDATE:
			model = vehicleModelMapper.getByKey(param.getId());
			if (null == model)
				return BtkjConsts.RESULT.VEHICLE_MODEL_NOT_EXIST;
			model.setName(param.getName());
			model.setUpdated(DateUtil.currentTime());
			vehicleModelMapper.update(model);
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
	
	@Override
	public Result<VehicleOrder> order(int tid, String orderId) {
		VehicleOrder order = vehicleOrderMapper.getByKey(orderId);
		if (null == order)
			return BtkjConsts.RESULT.ORDER_NOT_EXIST;
		if (tid != order.getTid())
			return Consts.RESULT.FORBID;
		return Result.result(order);
	}
	
	@Override
	public Pager<VehicleOrder> orders(VehicleOrdersParam param) {
		return vehicleOrderMapper.orders(param);
	}

	@Override
	public Result<VehiclePolicy> policy(int tid, String orderId) {
		VehiclePolicy policy = vehiclePolicyMapper.getByKey(orderId);
		if (null == policy)
			return BtkjConsts.RESULT.POLICY_NOT_EXIST;
		if (policy.getTid() != tid)
			return Consts.RESULT.FORBID;
		return Result.result(policy);
	}
	
	@Override
	public Map<String, VehiclePolicy> policies(int tid, int start, int end) {
		return null;
	}
	
	@Override
	public Map<String, VehiclePolicy> policies(Collection<String> ids) {
		return vehiclePolicyMapper.getByKeys(ids);
	}
	
	@Override
	public Pager<VehiclePolicy> policies(VehiclePoliciesParam param) {
		return vehiclePolicyMapper.policies(param);
	}
	
	@Override
	public Result<Map<String, VehicleOrder>> orderRewardStandby(int tid) {
		String lock = BtkjConsts.LOCKS.tenantResourceLock(tid);
		String lockId = distributeLock.lock(lock);
		if (null == lockId)
			return Consts.RESULT.LOCK_CONFLICT;
		try {
			return Result.result(vehicleOrderMapper.rewardStandbyUpdate(tid));
		} finally {
			distributeLock.unLock(lock, lockId);
		}
	}
	
	@Override
	public void orderRewardComplete(int tid) {
		String lock = BtkjConsts.LOCKS.tenantResourceLock(tid);
		String lockId = distributeLock.lock(lock, 30000);
		if (null == lockId){
			logger.warn("商户 - {} 车险订单结算完成状态更新锁获取失败!", tid);
			return;
		}
		try {
			vehicleOrderMapper.rewardComplete(tid);
		} finally {
			distributeLock.unLock(lock, lockId);
		}
	}
	
	@Override
	public Map<String, TenantInsurer> insurers(int tid) {
		return tenantInsurerMapper.getByTid(tid);
	}
	
	@Override
	public void insurerEdit(int tid, Set<String> insurersDelete, Map<String, TenantInsurer> insurersUpdate, Map<String, TenantInsurer> insurersInsert) {
		tx.insurerEdit(tid, insurersDelete, insurersUpdate, insurersInsert);
	}
}
