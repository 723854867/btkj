package org.btkj.vehicle.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.VehicleBrand;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.po.VehicleDept;
import org.btkj.pojo.po.VehicleModel;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.pojo.vo.JianJiePoliciesInfo;
import org.btkj.pojo.vo.JianJiePoliciesInfo.BaseInfo;
import org.btkj.vehicle.EntityGenerator;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.mongo.VehicleOrderMapper;
import org.btkj.vehicle.mongo.VehiclePolicyMapper;
import org.btkj.vehicle.mybatis.Tx;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.entity.VehiclePolicy.PolicyMark;
import org.btkj.vehicle.pojo.model.VehicleOrderSearcher;
import org.btkj.vehicle.pojo.submit.VehiclePolicySearcher;
import org.btkj.vehicle.redis.BonusManageConfigMapper;
import org.btkj.vehicle.redis.BonusScaleConfigMapper;
import org.btkj.vehicle.redis.RouteMapper;
import org.btkj.vehicle.redis.VehicleBrandMapper;
import org.btkj.vehicle.redis.VehicleCoefficientMapper;
import org.btkj.vehicle.redis.VehicleDeptMapper;
import org.btkj.vehicle.redis.VehicleModelMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.uuid.AlternativeJdkIdGenerator;
import org.rapid.util.concurrent.ThreadLocalUtil;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.math.compare.ComparisonSymbol;
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
	private RouteMapper routeMapper;
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
	public Result<Void> coefficientDelete(int tid, CoefficientType type, int id) {
		VehicleCoefficient coefficient = vehicleCoefficientMapper.getByKey(id);
		if (null == coefficient)
			return BtkjConsts.RESULT.COEFFICIENT_NOT_EXIST;
		if (coefficient.getTid() != tid || coefficient.getType() != type.mark())
			return Consts.RESULT.FORBID;
		vehicleCoefficientMapper.delete(id);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> coefficientAdd(int tid, CoefficientType type, ComparisonSymbol symbol, String[] value, String name) {
		try {
			tx.coefficientAdd(tid, type, symbol, value, name).finish();
			return Consts.RESULT.OK;
		} catch (BusinessException e) {
			return Result.result(e.getCode());
		}
	}
	
	@Override
	public Result<Void> coefficientUpdate(int tid, CoefficientType type, int id, ComparisonSymbol symbol, String[] value, String name) {
		try {
			tx.coefficientUpdate(tid, type, id, symbol, value, name).finish();
			return Consts.RESULT.OK;
		} catch (BusinessException e) {
			return Result.result(e.getCode());
		}
	}
	
	@Override
	public List<BonusScaleConfig> bonusScaleConfigs(int tid) {
		return bonusScaleConfigMapper.getByTid(tid);
	}
	
	@Override
	public List<BonusManageConfig> bonusManageConfigs(int tid) {
		return bonusManageConfigMapper.getByTid(tid);
	}
	
	@Override
	public Result<Void> bonusManageConfigAdd(int tid, BonusManageConfigType type, int depth, int rate) {
		BonusManageConfig config = EntityGenerator.newBonusManageConfig(tid, type, depth, rate);
		try {
			bonusManageConfigMapper.insert(config);
			return Consts.RESULT.OK;
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.FAILURE;
		}
	}
	
	@Override
	public Result<Void> bonusManageConfigUpdate(String id, int tid, int rate) {
		BonusManageConfig config = bonusManageConfigMapper.getByKey(id);
		if (null == config)
			return BtkjConsts.RESULT.BONUS_MANAGE_CONFIG_NOT_EXIST;
		if (tid != config.getTid())
			return Consts.RESULT.FORBID;
		config.setRate(rate);
		config.setUpdated(DateUtil.currentTime());
		bonusManageConfigMapper.update(config);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> bonusManageConfigDelete(String id, int tid) {
		BonusManageConfig config = bonusManageConfigMapper.getByKey(id);
		if (null == config)
			return BtkjConsts.RESULT.BONUS_MANAGE_CONFIG_NOT_EXIST;
		if (tid != config.getTid())
			return Consts.RESULT.FORBID;
		bonusManageConfigMapper.delete(id);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Integer> bonusScaleConfigAdd(int tid, int rate, ComparisonSymbol symbol, String[] val) {
		try {
			tx.bonusScaleConfigAdd(tid, rate, symbol, val).finish();
			Result<Integer> result = Result.result(Code.OK);
			result.setAttach(ThreadLocalUtil.getAndRemove(ThreadLocalUtil.INT_HOLDER));
			return result;
		} catch (BusinessException e) {
			return Result.result(e.getCode());
		}
	}
	
	@Override
	public Result<Void> bonusScaleConfigUpdate(int id, int tid, int rate, ComparisonSymbol symbol, String[] val) {
		try {
			tx.bonusScaleConfigUpdate(id, tid, rate, symbol, val).finish();
			return Consts.RESULT.OK;
		} catch (BusinessException e) {
			return Result.result(e.getCode());
		}
	}
	
	@Override
	public Result<Void> bonusScaleConfigDelete(int id, int tid) {
		BonusScaleConfig config = bonusScaleConfigMapper.getByKey(id);
		if (null == config)
			return BtkjConsts.RESULT.BONUS_SCALE_CONFIG_NOT_EXIST;
		if (tid != config.getTid())
			return Consts.RESULT.FORBID;
		bonusScaleConfigMapper.delete(id);
		return Consts.RESULT.OK;
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
		List<VehicleOrder> orders = CollectionUtil.isEmpty(deliverNos) ? Collections.EMPTY_LIST : vehicleOrderMapper.getByDeliverNos(insuranceType, employee.getTid(), deliverNos);
		Iterator<Entry<String, BaseInfo>> iterator = processing.entrySet().iterator();
		while (iterator.hasNext()) {
			String policyId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
			BaseInfo info = iterator.next().getValue();
			iterator.remove();
			Iterator<VehicleOrder> itr = orders.iterator();
			VehicleOrder order = null;
			while (itr.hasNext()) {
				VehicleOrder vo = itr.next();
				String cno = insuranceType == InsuranceType.COMMERCIAL ? vo.getTips().getDetail().getCommercialNo() : vo.getTips().getDetail().getCompulsiveNo();
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
			Route route = routeMapper.getByTidAndJianJieId(employee.getTid(), info.getCompanyId());
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
		int employeeId = 0;
		try {
			employeeId = Integer.valueOf(gsUser.substring(gsUser.indexOf(":") + 1, gsUser.length() - 1));
		} catch (NumberFormatException e) {
			logger.error("简捷用户雇员id解析出错 - {}", gsUser);
			return;
		}
		policy.setSalesmanId(employeeId);
		EmployeeTip employee = employees.get(employeeId);
		if (null == employee) {
			logger.error("保单 - {} 没有指定业务员！", policy.get_id());
			policy.setMark(PolicyMark.NO_EMPLOYEE);
		} else {
			if (employee.getTid() != tid) {
				policy.setMark(PolicyMark.EMPLOYEE_UNSUITABLE);
				if (null != order) {
					policy.setSalesmanId(order.getEmployeeId());
					policy.setSalesman(order.getSalesman());
					policy.setSalesmanMobile(order.getSalesmanMobile());
					logger.error("保途保单 - {} 业务员归属错误，当前代理公司 - {}，业务员所在代理公司 - {}；业务员 - {} 矫正为 - {}！", policy.get_id(), tid, employee.getTid(), employee.getId(), order.getEmployeeId());
					return;
				} else
					logger.error("非保途保单 - {} 业务员归属错误，当前代理公司 - {}，业务员所在代理公司 - {}；业务员 - {}！", policy.get_id(), tid, employee.getTid(), employee.getId());
			} else 
				policy.setMark(PolicyMark.NORMAL);
			policy.setSalesman(employee.getName());
			policy.setSalesmanMobile(employee.getMobile());
		}
	}
	
	@Override
	public void vehicleOrderIssue(int tid) {
		
	}
	
	@Override
	public List<Route> routes(int tid) {
		return routeMapper.getByTid(tid);
	}
	
	@Override
	public Result<Void> routeAdd(int tid, int insurerId, Lane lane, int jianJieId) {
		Route route = EntityGenerator.newRoute(tid, insurerId, lane, jianJieId);
		try {
			routeMapper.insert(route);
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> routeUpdate(String key, Lane lane, int jianJieId) {
		Route route = routeMapper.getByKey(key);
		if (null == route)
			return BtkjConsts.RESULT.ROUTE_NOT_EXIST;
		if (0 != jianJieId)
			route.setJianJieId(jianJieId);
		if (route.getLane() != lane.mark()) {
			route.setLane(lane.mark());
			route.setUpdated(DateUtil.currentTime());
		}
		routeMapper.update(route);
		return Consts.RESULT.OK;
	}
	
	@Override
	public void routeDelete(String key) {
		routeMapper.delete(key);		
	}
	
	@Override
	public List<VehicleBrand> brands() {
		return new ArrayList<VehicleBrand>(vehicleBrandMapper.getAll().values());
	}
	
	@Override
	public Result<Integer> brandAdd(String name) {
		VehicleBrand brand = EntityGenerator.newVehicleBrand(name);
		vehicleBrandMapper.insert(brand);
		Result<Integer> result = Result.result(Code.OK);
		result.setAttach(brand.getId());
		return result;
	}
	
	@Override
	public Result<Void> brandUpdate(int id, String name) {
		VehicleBrand brand = vehicleBrandMapper.getByKey(id);
		if (null == brand)
			return BtkjConsts.RESULT.VEHICLE_BRAND_NOT_EXIST;
		brand.setName(name);
		brand.setUpdated(DateUtil.currentTime());
		vehicleBrandMapper.update(brand);
		return Consts.RESULT.OK;
	}
	
	@Override
	public List<VehicleDept> depts(int brandId) {
		return vehicleDeptMapper.getByBrandId(brandId);
	}
	
	@Override
	public Result<Integer> deptAdd(int brandId, String name) {
		VehicleDept dept = EntityGenerator.newVehicleDept(brandId, name);
		vehicleDeptMapper.insert(dept);
		Result<Integer> result = Result.result(Code.OK);
		result.setAttach(dept.getId());
		return result;
	}
	
	@Override
	public Result<Void> deptUpdate(int id, String name) {
		VehicleDept dept = vehicleDeptMapper.getByKey(id);
		if (null == dept)
			return BtkjConsts.RESULT.VEHICLE_DEPT_NOT_EXIST;
		dept.setName(name);
		dept.setUpdated(DateUtil.currentTime());
		vehicleDeptMapper.update(dept);
		return Consts.RESULT.OK;
	}
	
	@Override
	public List<VehicleModel> models(int deptId) {
		return vehicleModelMapper.getByDeptId(deptId);
	}
	
	@Override
	public Result<Integer> modelAdd(int deptId, String name) {
		VehicleModel model = EntityGenerator.newVehicleModel(deptId, name);
		vehicleModelMapper.insert(model);
		Result<Integer> result = Result.result(Code.OK);
		result.setAttach(model.getId());
		return result;
	}
	
	@Override
	public Result<Void> modelUpdate(int id, String name) {
		VehicleModel model = vehicleModelMapper.getByKey(id);
		if (null == model)
			return BtkjConsts.RESULT.VEHICLE_MODEL_NOT_EXIST;
		model.setName(name);
		model.setUpdated(DateUtil.currentTime());
		vehicleModelMapper.update(model);
		return Consts.RESULT.OK;
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
	public Pager<VehicleOrder> orders(VehicleOrderSearcher searcher) {
		return vehicleOrderMapper.paging(searcher);
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
	public Pager<VehiclePolicy> policies(VehiclePolicySearcher searcher) {
		return vehiclePolicyMapper.paging(searcher);
	}
	
	@Override
	public List<VehicleOrder> orders(int tid, int stateMod) {
		return vehicleOrderMapper.orders(tid, stateMod);
	}
}
