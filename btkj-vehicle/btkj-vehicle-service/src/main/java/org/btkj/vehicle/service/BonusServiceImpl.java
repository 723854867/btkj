package org.btkj.vehicle.service;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.cache.CacheService;
import org.btkj.vehicle.mybatis.Tx;
import org.btkj.vehicle.pojo.entity.CoefficientNode;
import org.btkj.vehicle.pojo.entity.CoefficientRange;
import org.btkj.vehicle.pojo.entity.PoundageCoefficientRange;
import org.btkj.vehicle.pojo.model.CoefficientDocument;
import org.btkj.vehicle.pojo.model.NodeConfigModel;
import org.btkj.vehicle.pojo.model.PoundageDocument;
import org.btkj.vehicle.pojo.param.CoefficientRangeEditParam;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam;
import org.btkj.vehicle.realm.poundage.Poundage;
import org.btkj.vehicle.redis.PoundageCoefficientRangeMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
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
	public Map<Integer, PoundageDocument> poundageDocuments() {
		return poundage.poundageDocuments();
	}
	
	@Override
	public Result<Map<Integer, CoefficientDocument>> coefficientDocuments(int poundageNodeId) {
		return poundage.coefficientDocuments(poundageNodeId);
	}
	
	@Override
	public Map<Integer, CoefficientRange> coefficientRanges(int tid, int coefficientId) {
		return poundage.coefficientRanges(tid, coefficientId);
	}
	
	@Override
	public Result<Integer> coefficientRangeEdit(CoefficientRangeEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			try {
				CoefficientNode coefficient = cacheService.getById(CacheService.COEFFICIENT_NODE, param.getCoefficientId());
				if (null == coefficient)
					return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_EXIST;
				if (!coefficient.isCustom())
					return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_CUSTOM;
				if (!coefficient.getType().checkValue(param.getSymbol(), param.getVal()))
					return BtkjConsts.RESULT.COMPARISON_VALUE_ERROR;
				PoundageCoefficientRange range = tx.coefficientRangeAdd(param, coefficient);
				poundageCoefficientRangeMapper.flush(range);
				return Result.result(Code.OK, coefficient.getId());
			} catch (BusinessException e) {
				return Result.result(e.getCode());
			}
		case UPDATE:
			try {
				PoundageCoefficientRange range = tx.coefficientRangeUpdate(param);
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
	public NodeConfigModel poundageConfig(int tid, int insurerId, int nodeId, int coefficientId) {
		return poundage.poundageConfig(tid, insurerId, nodeId, coefficientId);
	}
	
	@Override
	public Result<Void> poundageConfigEdit(PoundageConfigEditParam param) {
		return poundage.poundageConfigEdit(param);
	}
}
