package org.btkj.vehicle.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.PolicyDetail;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.po.VehicleBrand;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.po.VehicleDept;
import org.btkj.pojo.po.VehicleModel;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.pojo.vo.JianJiePoliciesInfo;
import org.btkj.pojo.vo.JianJiePoliciesInfo.BaseInfo;
import org.btkj.vehicle.EntityGenerator;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.mongo.VehicleOrderMapper;
import org.btkj.vehicle.mybatis.Tx;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
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
	public Result<Void> bonusScaleConfigAdd(int tid, int rate, ComparisonSymbol symbol, String[] val) {
		try {
			tx.bonusScaleConfigAdd(tid, rate, symbol, val).finish();
			return Consts.RESULT.OK;
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
	public void jianJieSynchronize(int tid, JianJiePoliciesInfo info) {
		List<VehiclePolicy> policies = new ArrayList<VehiclePolicy>();
		Map<String, BaseInfo> commercials = new HashMap<String, BaseInfo>();
		Map<String, BaseInfo> compulsories = new HashMap<String, BaseInfo>();
		for (BaseInfo temp : info.getResult()) {
			if (temp.getBdType().equals(InsuranceType.COMMERCIAL.title()))
				commercials.put(temp.getBDH(), temp);
			else if (temp.getBdType().equals(InsuranceType.COMPULSORY.title()))
				compulsories.put(temp.getBDH(), temp);
			else
				throw new RuntimeException("未知的简捷保单类型  : " + temp.getBdType());
		}
		
		List<VehicleOrder> orders = vehicleOrderMapper.getByNos(InsuranceType.COMMERCIAL, commercials.keySet());
		for (VehicleOrder order : orders) {
			PolicyDetail detail = order.getTips().getDetail();
			BaseInfo commercial = commercials.remove(detail.getCommercialNo());
			String relationNo = commercial.getRelationPolicyNo();
			BaseInfo compulsory = null;
			if (null != relationNo) {
				compulsory = compulsories.remove(relationNo);
				if (null == compulsory) {
					logger.error("JianJie complete policy {}-{} compulsory data miss!", commercial.getTBDH(), relationNo);
					continue;
				}
				String no = detail.getCompulsiveNo();
				if (!StringUtil.hasText(no)) {
					logger.error("JianJie complete policy {}-{} mapping with baotu single policy {}!", commercial.getTBDH(), relationNo, order.get_id());
					continue;
				}
				if (!no.equals(relationNo)) {
					logger.error("JianJie complete policy {}-{} compulsory no not same with baotu compulsory no {}!", commercial.getTBDH(), relationNo, no);
					continue;
				}
				if ((compulsory.getCompanyId() != commercial.getCompanyId()) || (0 == commercial.getCompanyId())) {
					logger.error("JianJie complete policy {}-{} insurerIds {}-{} are different from each other!", commercial.getTBDH(), no, commercial.getCompanyId(), compulsory.getCompanyId());
					continue;
				}
			} else {
				if (StringUtil.hasText(detail.getCompulsiveNo())) {
					logger.error("JianJie single policy {} mapping with baotu complete policy {}-{}!", commercial.getTBDH(), order.get_id(), detail.getCompulsiveNo());
					continue;
				}
				if (0 == commercial.getCompanyId()) {
					logger.error("JianJie single policy {} has no insurer!", commercial.getTBDH());
					continue;
				}
			}
			Route route = routeMapper.getByTidAndJianJieId(tid, commercial.getCompanyId());
			if (null == route) {
				logger.error("JianJie insurer - {} not exist", commercial.getCompanyId());
				continue;
			}
			if (route.getInsurerId() != order.getInsurerId()) {
				logger.error("JianJie insurer - {} is differ with baotu order - {} insurer - {}", route.getJianJieId(), order.get_id(), order.getInsurerId());
				continue;
			}
			policies.add(new VehiclePolicy());
		}
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
		return new ArrayList<VehicleDept>(vehicleDeptMapper.getAll().values());
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
		return new ArrayList<VehicleModel>(vehicleModelMapper.getAll().values());
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
}
