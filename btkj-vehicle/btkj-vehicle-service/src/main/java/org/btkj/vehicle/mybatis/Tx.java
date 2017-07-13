package org.btkj.vehicle.mybatis;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.TxCallback;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.vehicle.mybatis.dao.BonusScaleConfigDao;
import org.btkj.vehicle.mybatis.dao.VehicleCoefficientDao;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.redis.BonusScaleConfigMapper;
import org.btkj.vehicle.redis.VehicleCoefficientMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.lang.DateUtils;
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
	private VehicleCoefficientDao vehicleCoefficientDao;
	@Resource
	private BonusScaleConfigMapper bonusScaleConfigMapper;
	@Resource
	private VehicleCoefficientMapper vehicleCoefficientMapper;

	@Transactional
	public TxCallback coefficientUpdate(int tid, CoefficientType type, int id, ComparisonSymbol symbol, String[] value, String name) {
		List<VehicleCoefficient> coefficients = vehicleCoefficientDao.getByTidAndTypeForUpdate(tid, type.mark());
		Iterator<VehicleCoefficient> itr = coefficients.iterator();
		VehicleCoefficient coefficient = null;
		while (itr.hasNext()) {
			VehicleCoefficient temp = itr.next();
			if (temp.getId() != id)
				continue;
			coefficient = temp;
			itr.remove();
			break;
		}
		if (null == coefficient)
			throw new BusinessException(BtkjCode.COEFFICIENT_NOT_EXIST);
		for (VehicleCoefficient temp : coefficients) {
			ComparisonSymbol c = ComparisonSymbol.match(temp.getComparison());
			String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
			if (c.isOverlap(symbol, value, val))
				throw new BusinessException(Code.FAILURE);
		}
		StringBuilder builder = new StringBuilder();
		for (String val : value)
			builder.append(val).append(Consts.SYMBOL_UNDERLINE);
		builder.deleteCharAt(builder.length() - 1);
		coefficient.setName(name);
		coefficient.setComparison(symbol.mark());
		coefficient.setUpdated(DateUtils.currentTime());
		coefficient.setComparableValue(builder.toString());
		vehicleCoefficientDao.update(coefficient);
		final VehicleCoefficient update = coefficient;
		return new TxCallback() {
			@Override
			public void finish() {
				vehicleCoefficientMapper.flush(update);
			}
		};
	}
	
	@Transactional
	public TxCallback coefficientAdd(int tid, CoefficientType type, ComparisonSymbol symbol, String[] value, String name) {
		List<VehicleCoefficient> coefficients = vehicleCoefficientDao.getByTidAndTypeForUpdate(tid, type.mark());
		if (type.isCustom() && coefficients.size() >= type.maxCustomNum())
			throw new BusinessException(BtkjCode.COEFFICIENT_NUM_MAXMIUM);
		for (VehicleCoefficient temp : coefficients) {
			ComparisonSymbol c = ComparisonSymbol.match(temp.getComparison());
			String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
			if (c.isOverlap(symbol, value, val))
				throw new BusinessException(Code.FAILURE);
		}
		StringBuilder builder = new StringBuilder();
		for (String val : value)
			builder.append(val).append(Consts.SYMBOL_UNDERLINE);
		builder.deleteCharAt(builder.length() - 1);
		final VehicleCoefficient coefficient = EntityGenerator.newVehicleCoefficient(tid, type, symbol, builder.toString(), name);
		vehicleCoefficientDao.insert(coefficient);
		return new TxCallback() {
			@Override
			public void finish() {
				vehicleCoefficientMapper.flush(coefficient);
			}
		};
	}
	
	@Transactional
	public TxCallback bonusScaleConfigAdd(int tid, int rate, ComparisonSymbol symbol, String[] cval) {
		List<BonusScaleConfig> configs = bonusScaleConfigDao.getByTidForUpdate(tid);
		if (configs.size() >= GlobalConfigContainer.getGlobalConfig().getMaxBonusScaleConfig())
			throw new BusinessException(BtkjCode.BONUS_SCALE_CONFIG_MAXMIUM);
		for (BonusScaleConfig temp : configs) {
			ComparisonSymbol c = ComparisonSymbol.match(temp.getComparison());
			String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
			if (c.isOverlap(symbol, cval, val))
				throw new BusinessException(Code.FAILURE);
		}
		StringBuilder builder = new StringBuilder();
		for (String val : cval)
			builder.append(val).append(Consts.SYMBOL_UNDERLINE);
		builder.deleteCharAt(builder.length() - 1);
		final BonusScaleConfig config = EntityGenerator.newBonusScaleConfig(tid, rate, symbol, builder.toString());
		return new TxCallback() {
			@Override
			public void finish() {
				bonusScaleConfigMapper.flush(config);
			}
		};
	}
	
	public TxCallback bonusScaleConfigUpdate(int id, int tid, int rate, ComparisonSymbol symbol, String[] cval) {
		List<BonusScaleConfig> configs = bonusScaleConfigDao.getByTidForUpdate(tid);
		Iterator<BonusScaleConfig> itr = configs.iterator();
		BonusScaleConfig config = null;
		while (itr.hasNext()) {
			BonusScaleConfig temp = itr.next();
			if (temp.getId() != id)
				continue;
			config = temp;
			itr.remove();
			break;
		}
		if (null == config)
			throw new BusinessException(BtkjCode.BONUS_SCALE_CONFIG_NOT_EXIST);
		for (BonusScaleConfig temp : configs) {
			ComparisonSymbol c = ComparisonSymbol.match(temp.getComparison());
			String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
			if (c.isOverlap(symbol, cval, val))
				throw new BusinessException(Code.FAILURE);
		}
		StringBuilder builder = new StringBuilder();
		for (String val : cval)
			builder.append(val).append(Consts.SYMBOL_UNDERLINE);
		builder.deleteCharAt(builder.length() - 1);
		config.setRate(rate);
		config.setComparison(symbol.mark());
		config.setUpdated(DateUtils.currentTime());
		config.setComparableValue(builder.toString());
		bonusScaleConfigDao.update(config);
		final BonusScaleConfig update = config;
		return new TxCallback() {
			@Override
			public void finish() {
				bonusScaleConfigMapper.flush(update);
			}
		};
	}
}
