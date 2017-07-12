package org.btkj.vehicle.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.TxCallback;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.vehicle.mybatis.dao.VehicleCoefficientDao;
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
	private VehicleCoefficientDao vehicleCoefficientDao;
	@Resource
	private VehicleCoefficientMapper vehicleCoefficientMapper;

	@Transactional
	public TxCallback coefficientUpdate(int tid, CoefficientType type, int id, ComparisonSymbol symbol, String[] value, String name) {
		List<VehicleCoefficient> coefficients = vehicleCoefficientDao.getByTidAndTypeForUpdate(tid, type.mark());
		VehicleCoefficient coefficient = null;
		for (VehicleCoefficient temp : coefficients) {
			if (temp.getId() == id) {
				coefficient = temp;
				break;
			}
		}
		if (null == coefficient)
			throw new BusinessException(BtkjCode.COEFFICIENT_NOT_EXIST);
		if (coefficient.getTid() != tid)
			throw new BusinessException(Code.FORBID);
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
}
