package org.btkj.vehicle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.info.JianJiePoliciesInfo;
import org.btkj.pojo.info.JianJiePoliciesInfo.BaseInfo;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.mybatis.EntityGenerator;
import org.btkj.vehicle.mybatis.Tx;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.vehicle.redis.BonusManageConfigMapper;
import org.btkj.vehicle.redis.BonusScaleConfigMapper;
import org.btkj.vehicle.redis.RouteMapper;
import org.btkj.vehicle.redis.VehicleCoefficientMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("vehicleManageService")
public class VehicleManageServiceImpl implements VehicleManageService {
	
	@Resource
	private Tx tx;
	@Resource
	private RouteMapper routeMapper;
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
	public void jianjieSynchronize(JianJiePoliciesInfo info) {
		List<BaseInfo> infos = info.getResult();
		int left = infos.size();
		int idx = 0;
		int pageSize = 50;
		while (left > 0) {
			pageSize = Math.min(pageSize, left);
			List<BaseInfo> list = infos.subList(idx, idx + pageSize);
			Map<String, BaseInfo> commercials = new HashMap<String, BaseInfo>();
			Map<String, BaseInfo> compulsories = new HashMap<String, BaseInfo>();
			for (BaseInfo temp : list) {
				if (temp.getBdType().equals(InsuranceType.COMMERCIAL.title())) {
					
				} else if (temp.getBdType().equals(InsuranceType.COMPULSORY.title())) {
					
				}
			}
		}
	}
	
	@Override
	public List<Route> routes(int tid) {
		return routeMapper.getByTid(tid);
	}
	
	@Override
	public Result<Void> routeAdd(int tid, int insurerId, Lane lane) {
		Route route = EntityGenerator.newRoute(tid, insurerId, lane);
		try {
			routeMapper.insert(route);
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> routeUpdate(String key, Lane lane) {
		Route route = routeMapper.getByKey(key);
		if (null == route)
			return BtkjConsts.RESULT.ROUTE_NOT_EXIST;
		if (route.getLane() != lane.mark()) {
			route.setLane(lane.mark());
			route.setUpdated(DateUtil.currentTime());
			routeMapper.update(route);
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public void routeDelete(String key) {
		routeMapper.delete(key);		
	}
}
