package org.btkj.vehicle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.cache.CacheService;
import org.btkj.vehicle.cache.domain.CfgCoefficient;
import org.btkj.vehicle.cache.domain.CfgCoefficientRange;
import org.btkj.vehicle.mybatis.Tx;
import org.btkj.vehicle.pojo.entity.PoundageCoefficientRange;
import org.btkj.vehicle.pojo.model.CoefficientRange;
import org.btkj.vehicle.pojo.model.PoundageNodeConfigInfo;
import org.btkj.vehicle.pojo.model.PoundageStructure;
import org.btkj.vehicle.pojo.param.PoundageCoefficientRangeEditParam;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam;
import org.btkj.vehicle.pojo.param.PoundageNodeConfigParam;
import org.btkj.vehicle.realm.Poundage;
import org.btkj.vehicle.redis.PoundageCoefficientRangeMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.springframework.stereotype.Service;

@Service("bonusService")
public class BonusServiceImpl implements BonusService {
	
	@Resource
	private Tx tx;
	@Resource
	private Poundage poundage;
	@Resource
	private CacheService cacheService;
	@Resource
	private PoundageCoefficientRangeMapper poundageCoefficientRangeMapper;
	
	@Override
	public Map<Integer, PoundageStructure> poundageNodeStructure() {
		return poundage.getPoundageStructures();
	}
	
	@Override
	public Result<List<CoefficientRange>> poundageCoefficientRanges(int tid, int cfgCoefficientId) {
		CfgCoefficient cfgCoefficient = cacheService.getById(CacheService.CFG_COEFFICIENT, cfgCoefficientId);
		if (null == cfgCoefficient)
			return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_EXIST;
		List<CoefficientRange> ranges = new ArrayList<CoefficientRange>();
		if (!cfgCoefficient.isCustom()) {					// 直接获取系统的默认配置
			Set<Integer> set = cfgCoefficient.getCfgCoefficientRangeIds();
			Map<Integer, CfgCoefficientRange> map = CollectionUtil.isEmpty(set) ? null : cacheService.getByIds(CacheService.CFG_COEFFICIENT_RANGE, set);
			if (CollectionUtil.isEmpty(map))
				return Result.result(ranges);
			for (CfgCoefficientRange range : map.values()) {
				CoefficientRange temp = new CoefficientRange();
				temp.setId(range.getId());
				temp.setName(range.getName());
				temp.setComparison(range.getComparison());
				temp.setComparableValue(range.getComparableValue());
				ranges.add(temp);
			}
		} else {
			Map<Integer, PoundageCoefficientRange> map = poundageCoefficientRangeMapper.ranges(tid, cfgCoefficientId);
			if (CollectionUtil.isEmpty(map))
				return Result.result(ranges);
			for (PoundageCoefficientRange range : map.values()) {
				CoefficientRange temp = new CoefficientRange();
				temp.setId(range.getId());
				temp.setName(range.getName());
				temp.setComparison(range.getComparison());
				temp.setComparableValue(range.getComparableValue());
				ranges.add(temp);
			}
		}
		return Result.result(ranges);
	}
	
	@Override
	public Result<Integer> poundageCoefficientRangeEdit(PoundageCoefficientRangeEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			try {
				CfgCoefficient coefficient = cacheService.getById(CacheService.CFG_COEFFICIENT, param.getCfgCoefficientId());
				if (null == coefficient)
					return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_EXIST;
				if (!coefficient.isCustom())
					return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_CUSTOM;
				PoundageCoefficientRange range = tx.poundageCoefficientRangeAdd(param, coefficient);
				poundageCoefficientRangeMapper.flush(range);
				return Result.result(Code.OK, coefficient.getId());
			} catch (BusinessException e) {
				return Result.result(e.getCode());
			}
		case UPDATE:
			try {
				PoundageCoefficientRange range = tx.poundageCoefficientRangeUpdate(param);
				poundageCoefficientRangeMapper.flush(range);
				return Consts.RESULT.OK;
			} catch (BusinessException e) {
				return Result.result(e.getCode());
			}
		case DELETE:
			PoundageCoefficientRange range = poundageCoefficientRangeMapper.getByKey(param.getId());
			if (null == range)
				return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_RANGE_NOT_EXIST;
			if (range.getTid() != param.getTid())
				return Consts.RESULT.FORBID;
			poundageCoefficientRangeMapper.delete(param.getId());
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
	
	@Override
	public Result<Void> poundageConfigEdit(PoundageConfigEditParam param) {
		return poundage.poundageConfigEdit(param);
	}
	
	@Override
	public PoundageNodeConfigInfo poundageNodeConfig(PoundageNodeConfigParam param) {
		return poundage.poundageNodeConfig(param);
	}
}
