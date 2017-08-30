package org.btkj.vehicle.mybatis;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.vehicle.EntityGenerator;
import org.btkj.vehicle.cache.CacheService;
import org.btkj.vehicle.cache.domain.CfgCoefficient;
import org.btkj.vehicle.mybatis.dao.BonusScaleConfigDao;
import org.btkj.vehicle.mybatis.dao.PoundageCoefficientRangeDao;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.PoundageCoefficientRange;
import org.btkj.vehicle.pojo.param.BonusScaleConfigEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientRangeEditParam;
import org.btkj.vehicle.pojo.param.TenantSetParam;
import org.btkj.vehicle.redis.BonusScaleConfigMapper;
import org.btkj.vehicle.redis.JianJieInsurerMapper;
import org.btkj.vehicle.redis.TenantInsurerMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.Comparison;
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
	private CacheService cacheService;
	@Resource
	private BonusScaleConfigDao bonusScaleConfigDao;
	@Resource
	private TenantInsurerMapper tenantInsurerMapper;
	@Resource
	private JianJieInsurerMapper jianJieInsurerMapper;
	@Resource
	private BonusScaleConfigMapper bonusScaleConfigMapper;
	@Resource
	private PoundageCoefficientRangeDao poundageCoefficientRangeDao;
	
	@Transactional
	public PoundageCoefficientRange poundageCoefficientRangeUpdate(PoundageCoefficientRangeEditParam param) {
		PoundageCoefficientRange range = poundageCoefficientRangeDao.getByKey(param.getId());
		if (null == range)
			throw new BusinessException(BtkjCode.POUNDAGE_COEFFICIENT_RANGE_NOT_EXIST);
		CfgCoefficient coefficient = cacheService.getById(CacheService.CFG_COEFFICIENT, param.getCfgCoefficientId());
		Map<Integer, PoundageCoefficientRange> ranges = poundageCoefficientRangeDao.getByTidAndCfgCoefficientIdForUpdate(param.getTid(), range.getCfgCoefficientId());
		if (null != param.getVal()) {
			int comparison = null != param.getSymbol() ? param.getSymbol().mark() : range.getComparison();
			if (!coefficient.getType().checkValue(Comparison.match(comparison), param.getVal()))
				throw new BusinessException(BtkjCode.COMPARISON_VALUE_ERROR);
			for (PoundageCoefficientRange temp : ranges.values()) {
				Comparison c = Comparison.match(temp.getComparison());
				String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
				if (c.isOverlap(Comparison.match(comparison), param.getVal(), val))
					throw new BusinessException(Code.RANGE_OVERLAP);
			}
			StringBuilder builder = new StringBuilder();
			for (String val : param.getVal())
				builder.append(val).append(Consts.SYMBOL_UNDERLINE);
			builder.deleteCharAt(builder.length() - 1);
			range.setComparison(comparison);
			range.setComparableValue(builder.toString());
		}
		if (null != param.getName())
			range.setName(param.getName());
		range.setUpdated(DateUtil.currentTime());
		poundageCoefficientRangeDao.update(range);
		return range;
	}
	
	@Transactional
	public PoundageCoefficientRange poundageCoefficientRangeAdd(PoundageCoefficientRangeEditParam param, CfgCoefficient coefficient) {
		Map<Integer, PoundageCoefficientRange> ranges = poundageCoefficientRangeDao.getByTidAndCfgCoefficientIdForUpdate(param.getTid(), coefficient.getId());
		if (ranges.size() > coefficient.getMaxRanges())
			throw new BusinessException(BtkjCode.POUNDAGE_COEFFICIENT_RANGE_MAXMIUM);
		for (PoundageCoefficientRange temp : ranges.values()) {
			Comparison c = Comparison.match(temp.getComparison());
			String[] val = temp.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
			if (c.isOverlap(param.getSymbol(), param.getVal(), val))
				throw new BusinessException(Code.RANGE_OVERLAP);
		}
		StringBuilder builder = new StringBuilder();
		for (String val : param.getVal())
			builder.append(val).append(Consts.SYMBOL_UNDERLINE);
		builder.deleteCharAt(builder.length() - 1);
		PoundageCoefficientRange range = EntityGenerator.newPoundageCoefficientRange(param, builder.toString());
		poundageCoefficientRangeDao.insert(range);
		return range;
	}
	
	@Transactional
	public BonusScaleConfig bonusScaleConfigAdd(int tid, BonusScaleConfigEditParam param) {
		Map<Integer, BonusScaleConfig> configs = bonusScaleConfigDao.getByTidForUpdate(tid);
		if (configs.size() >= GlobalConfigContainer.getGlobalConfig().getMaxBonusScaleConfig())
			throw new BusinessException(BtkjCode.BONUS_SCALE_CONFIG_MAXMIUM);
		for (BonusScaleConfig temp : configs.values()) {
			Comparison c = Comparison.match(temp.getComparison());
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
				Comparison symbol = Comparison.match(temp.getComparison());
				Comparison csymbol = Comparison.match(config.getComparison());
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
	public void insurerEdit(TenantSetParam param) {
		tenantInsurerMapper.insurerEdit(param);
	}
}
