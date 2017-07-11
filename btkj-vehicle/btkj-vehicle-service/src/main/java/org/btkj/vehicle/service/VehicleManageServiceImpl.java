package org.btkj.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.redis.VehicleCoefficientMapper;
import org.btkj.vehicle.rule.Rule;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.springframework.stereotype.Service;

@Service("vehicleManageService")
public class VehicleManageServiceImpl implements VehicleManageService {
	
	@Resource
	private Rule rule;
	@Resource
	private VehicleCoefficientMapper vehicleCoefficientMapper;

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
		List<VehicleCoefficient> coefficients = vehicleCoefficientMapper.getByTidAndType(tid, type);
		if (type.isCustom() && coefficients.size() >= type.maxCustomNum())
			return BtkjConsts.RESULT.COEFFICIENT_NUM_MAXMIUM;
		
		return null;
	}
	
	@Override
	public Result<Void> coefficientUpdate(int tid, CoefficientType type, int id, ComparisonSymbol symbol, String[] value, String name) {
		VehicleCoefficient coefficient = vehicleCoefficientMapper.getByKey(id);
		if (null == coefficient)
			return BtkjConsts.RESULT.COEFFICIENT_NOT_EXIST;
		if (coefficient.getTid() != tid || coefficient.getType() != type.mark())
			return Consts.RESULT.FORBID;
		List<VehicleCoefficient> coefficients = vehicleCoefficientMapper.getByTidAndType(tid, type);
		
		return null;
	}
}
