package org.btkj.vehicle.mybatis;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.vehicle.EntityGenerator;
import org.btkj.vehicle.mybatis.dao.BonusScaleConfigDao;
import org.btkj.vehicle.mybatis.dao.VehicleCoefficientDao;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.TenantInsurer;
import org.btkj.vehicle.pojo.param.BonusScaleConfigEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientEditParam;
import org.btkj.vehicle.redis.BonusScaleConfigMapper;
import org.btkj.vehicle.redis.TenantInsurerMapper;
import org.btkj.vehicle.redis.VehicleCoefficientMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 所有事物中的 mapper 不能直接 insert，防止前面 insert 成功刷入缓存了，
 * 但是 后面的 insert 失败导致所有 db 数据回滚，但是redis缓存数据没有回滚
 * 
 * @author ahab
 */
@Service("tx")
public class Tx {
	
	@Resource
	private BonusScaleConfigDao bonusScaleConfigDao;
	@Resource
	private TenantInsurerMapper tenantInsurerMapper;
	@Resource
	private VehicleCoefficientDao vehicleCoefficientDao;
	@Resource
	private BonusScaleConfigMapper bonusScaleConfigMapper;
	@Resource
	private VehicleCoefficientMapper vehicleCoefficientMapper;
	

	@Transactional
	public VehicleCoefficient coefficientUpdate(PoundageCoefficientEditParam param) {
		Map<Integer, VehicleCoefficient> coefficients = vehicleCoefficientDao.getByTidAndTypeForUpdate(param.getTid(), param.getCoefficientType().mark());
		VehicleCoefficient coefficient = coefficients.remove(param.getId());
		if (null == coefficient)
			throw new BusinessException(BtkjCode.COEFFICIENT_NOT_EXIST);
		if (null != param.getSymbol())
			coefficient.setComparison(param.getSymbol().mark());
		if (null != param.getVal()) {
			if (!CoefficientType.match(coefficient.getType()).checkValue(ComparisonSymbol.match(coefficient.getComparison()), param.getVal()))
				throw new BusinessException(Code.FORBID);
			for (VehicleCoefficient temp : coefficients.values()) {
				ComparisonSymbol c = ComparisonSymbol.match(temp.getComparison());
				String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
				if (c.isOverlap(ComparisonSymbol.match(coefficient.getComparison()), param.getVal(), val))
					throw new BusinessException(Code.FAILURE);
			}
			StringBuilder builder = new StringBuilder();
			for (String val : param.getVal())
				builder.append(val).append(Consts.SYMBOL_UNDERLINE);
			builder.deleteCharAt(builder.length() - 1);
			coefficient.setComparableValue(builder.toString());
		}
		if (null != param.getName())
			coefficient.setName(param.getName());
		coefficient.setUpdated(DateUtil.currentTime());
		vehicleCoefficientDao.update(coefficient);
		return coefficient;
	}
	
	@Transactional
	public VehicleCoefficient coefficientAdd(PoundageCoefficientEditParam param) {
		if (!param.getCoefficientType().checkValue(param.getSymbol(), param.getVal()))
			throw new BusinessException(Code.FORBID);
		Map<Integer, VehicleCoefficient> coefficients = vehicleCoefficientDao.getByTidAndTypeForUpdate(param.getTid(), param.getCoefficientType().mark());
		if (param.getCoefficientType().isCustom() && coefficients.size() >= param.getCoefficientType().maxCustomNum())
			throw new BusinessException(BtkjCode.COEFFICIENT_NUM_MAXMIUM);
		for (VehicleCoefficient temp : coefficients.values()) {
			ComparisonSymbol c = ComparisonSymbol.match(temp.getComparison());
			String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
			if (c.isOverlap(param.getSymbol(), param.getVal(), val))
				throw new BusinessException(Code.FAILURE);
		}
		StringBuilder builder = new StringBuilder();
		for (String val : param.getVal())
			builder.append(val).append(Consts.SYMBOL_UNDERLINE);
		builder.deleteCharAt(builder.length() - 1);
		VehicleCoefficient coefficient = EntityGenerator.newVehicleCoefficient(param, builder.toString());
		vehicleCoefficientDao.insert(coefficient);
		return coefficient;
	}
	
	@Transactional
	public BonusScaleConfig bonusScaleConfigAdd(int tid, BonusScaleConfigEditParam param) {
		Map<Integer, BonusScaleConfig> configs = bonusScaleConfigDao.getByTidForUpdate(tid);
		if (configs.size() >= GlobalConfigContainer.getGlobalConfig().getMaxBonusScaleConfig())
			throw new BusinessException(BtkjCode.BONUS_SCALE_CONFIG_MAXMIUM);
		for (BonusScaleConfig temp : configs.values()) {
			ComparisonSymbol c = ComparisonSymbol.match(temp.getComparison());
			String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
			if (c.isOverlap(param.getSymbol(), param.getVal(), val))
				throw new BusinessException(Code.FAILURE);
		}
		StringBuilder builder = new StringBuilder();
		for (String val : param.getVal())
			builder.append(val).append(Consts.SYMBOL_UNDERLINE);
		builder.deleteCharAt(builder.length() - 1);
		final BonusScaleConfig config = EntityGenerator.newBonusScaleConfig(tid, param.getRate(), param.getSymbol(), builder.toString());
		bonusScaleConfigDao.insert(config);
		return config;
	}
	
	@Transactional
	public BonusScaleConfig bonusScaleConfigUpdate(int tid, BonusScaleConfigEditParam param) {
		Map<Integer, BonusScaleConfig> configs = bonusScaleConfigDao.getByTidForUpdate(tid);
		BonusScaleConfig config = configs.remove(param.getId());
		if (null == config)
			throw new BusinessException(BtkjCode.BONUS_SCALE_CONFIG_NOT_EXIST);
		if (null != param.getRate())
			config.setRate(param.getRate());
		if (null != param.getSymbol())
			config.setComparison(param.getSymbol().mark());
		if (null != param.getVal()) {
			for (BonusScaleConfig temp : configs.values()) {
				ComparisonSymbol symbol = ComparisonSymbol.match(temp.getComparison());
				ComparisonSymbol csymbol = ComparisonSymbol.match(config.getComparison());
				String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
				if (symbol.isOverlap(csymbol, param.getVal(), val))
					throw new BusinessException(Code.RANGE_OVERLAP);
			}
			StringBuilder builder = new StringBuilder();
			for (String val : param.getVal())
				builder.append(val).append(Consts.SYMBOL_UNDERLINE);
			builder.deleteCharAt(builder.length() - 1);
			config.setComparableValue(builder.toString());
		}
		config.setUpdated(DateUtil.currentTime());
		bonusScaleConfigDao.update(config);
		return config;
	}
	
	@Transactional
	public void insurerEdit(int tid, Set<String> insurersDelete, Map<String, TenantInsurer> insurersUpdate, Map<String, TenantInsurer> insurersInsert) {
		tenantInsurerMapper.insurerEdit(tid, insurersDelete, insurersUpdate, insurersInsert);
	}
}
