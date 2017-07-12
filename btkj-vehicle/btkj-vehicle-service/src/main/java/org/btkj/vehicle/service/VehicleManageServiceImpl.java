package org.btkj.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.mybatis.Tx;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.redis.BonusScaleConfigMapper;
import org.btkj.vehicle.redis.VehicleCoefficientMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.springframework.stereotype.Service;

@Service("vehicleManageService")
public class VehicleManageServiceImpl implements VehicleManageService {
	
	@Resource
	private Tx tx;
	@Resource
	private BonusScaleConfigMapper bonusScaleConfigMapper;
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
}
