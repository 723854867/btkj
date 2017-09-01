package org.btkj.vehicle.realm.poundage;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.vehicle.cache.CacheService;
import org.btkj.vehicle.mongo.PoundageConfigMapper;
import org.btkj.vehicle.pojo.entity.CoefficientNode;
import org.btkj.vehicle.pojo.entity.CoefficientRange;
import org.btkj.vehicle.pojo.entity.PoundageCoefficientRange;
import org.btkj.vehicle.pojo.entity.PoundageConfig;
import org.btkj.vehicle.pojo.entity.PoundageConfig.NodeConfig;
import org.btkj.vehicle.pojo.entity.PoundageNode;
import org.btkj.vehicle.pojo.model.CoefficientDocument;
import org.btkj.vehicle.pojo.model.PoundageDocument;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam.Type;
import org.btkj.vehicle.redis.PoundageCoefficientRangeMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("poundage")
public class Poundage {
	
	private static final Logger logger = LoggerFactory.getLogger(Poundage.class);
	
	@Resource
	private CacheService cacheService;
	@Resource
	private ConfigService configService;
	@Resource
	private PoundageConfigMapper poundageConfigMapper;
	@Resource
	private PoundageDocumentFactory poundageDocumentFactory;
	@Resource
	private CoefficientDocumentFactory coefficientDocumentFactory;
	@Resource
	private PoundageCoefficientRangeMapper poundageCoefficientRangeMapper;
	
	private Map<Integer, PoundageDocument> poundageDocuments;
	private Map<Integer, CoefficientDocument> coefficientDocuments;
	
	public void init() { 
		Map<Integer, PoundageNode> nodes = cacheService.getAll(CacheService.POUNDAGE_NODE);
		if (!CollectionUtil.isEmpty(nodes))
			poundageDocuments = poundageDocumentFactory.build(nodes);
		Map<Integer, org.btkj.vehicle.pojo.entity.CoefficientNode> coefficientNodes = cacheService.getAll(CacheService.COEFFICIENT_NODE);
		if (!CollectionUtil.isEmpty(coefficientNodes))
			coefficientDocuments = coefficientDocumentFactory.build(coefficientNodes);
	}
	
	/**
	 * 计算手续费
	 */
	public void calculate(VehicleOrder order) {
	}
	
	public Map<Integer, PoundageDocument> poundageDocuments() {
		return poundageDocuments;
	}
	
	public Result<Map<Integer, CoefficientDocument>> coefficientDocuments(int poundageNodeId) {
		PoundageNode node = cacheService.getById(CacheService.POUNDAGE_NODE, poundageNodeId);
		if (null == node) 
			return BtkjConsts.RESULT.POUNDAGE_NODE_NOT_EXIST;
		if (CollectionUtil.isEmpty(node.getCoefficients()))
			return Consts.RESULT.OK;
		Set<Integer> coefficients = new HashSet<Integer>(node.getCoefficients());
		Map<Integer, CoefficientDocument> map = new HashMap<Integer, CoefficientDocument>();
		_searcheCoefficients(map, coefficientDocuments, coefficients);
		return Result.result(map);
	}
	
	private void _searcheCoefficients(Map<Integer, CoefficientDocument> map, Map<Integer, CoefficientDocument> documents, Set<Integer> coefficients) {
		if (CollectionUtil.isEmpty(documents) || CollectionUtil.isEmpty(coefficients))
			return;
		for (Entry<Integer, CoefficientDocument> entry : documents.entrySet()) {
			if (coefficients.remove(entry.getKey())) {
				map.put(entry.getKey(), entry.getValue());
				continue;
			} else 
				_searcheCoefficients(map, entry.getValue().children(), coefficients);
		}
	}
	
	public Map<Integer, CoefficientRange> coefficientRanges(int tid, int coefficientId) {
		CoefficientNode coefficient = cacheService.getById(CacheService.COEFFICIENT_NODE, coefficientId);
		if (null == coefficient)
			return null;
		if (coefficient.isCustom()) {
			Map<Integer, PoundageCoefficientRange> map = poundageCoefficientRangeMapper.ranges(tid, coefficientId);
			if (CollectionUtil.isEmpty(map))
				return null;
			Map<Integer, CoefficientRange> ranges = new HashMap<Integer, CoefficientRange>();
			for (PoundageCoefficientRange range : map.values()) {
				CoefficientRange temp = new CoefficientRange();
				temp.setId(range.getId());
				temp.setName(range.getName());
				temp.setComparison(range.getComparison());
				temp.setComparableValue(range.getComparableValue());
				ranges.put(temp.getId(), temp);
			}
			return ranges;
		} else {
			if (CollectionUtil.isEmpty(coefficient.getCoefficientRanges()))
				return null;
			Map<Integer, CoefficientRange> ranges = cacheService.getByIds(CacheService.COEFFICIENT_RANGE, coefficient.getCoefficientRanges());
			return ranges;
		}
	}
	
	public NodeConfig poundageConfig(int tid, int insurerId, int nodeId) {
		PoundageConfig config = poundageConfigMapper.getByKey(String.valueOf(tid));
		Map<Integer, Map<Integer, NodeConfig>> map = null == config ? null : config.getConfigs();
		Map<Integer, NodeConfig> configs = null == map ? null : map.get(insurerId);
		return null == configs ? null : configs.get(nodeId);
	}
	
	public Result<Void> poundageConfigEdit(PoundageConfigEditParam param) {
		PoundageConfig config = poundageConfigMapper.getByKey(String.valueOf(param.getTid()));
		if (null == config && param.getType() == Type.EDIT) 
			config = new PoundageConfig(param.getTid());
		Map<Integer, Map<Integer, NodeConfig>> map = null == config ? null : config.getConfigs();
		if (null == map && param.getType() == Type.EDIT) {
			map = new HashMap<Integer, Map<Integer, NodeConfig>>();
			config.setConfigs(map);
		}
		Map<Integer, NodeConfig> configs = null == map ? null : map.get(param.getInsurerId());
		if (null == configs && param.getType() == Type.EDIT) {
			configs = new HashMap<Integer, NodeConfig>();
			map.put(param.getInsurerId(), configs);
		}
		NodeConfig nodeConfig = null == configs ? null : configs.get(param.getNodeId());
		switch (param.getType()) {
		case BIND:
		case UNBIND:
			if (null == nodeConfig)
				return BtkjConsts.RESULT.POUNDAGE_CONFIG_NOT_EXIST;
			nodeConfig.setEffective(param.getType() == Type.BIND ? true : false);
			break;
		case EDIT:
			PoundageNode node = cacheService.getById(CacheService.POUNDAGE_NODE, param.getNodeId());
			if (null == node)
				return BtkjConsts.RESULT.POUNDAGE_NODE_NOT_EXIST;
			if (null == param.getConfig())
				configs.remove(param.getNodeId());
			else {
				if (null == nodeConfig)
					nodeConfig = new NodeConfig();
				NodeConfig update = param.getConfig();
				nodeConfig.setCmRate(update.getCmRate());
				nodeConfig.setCpRate(update.getCpRate());
				nodeConfig.setCmRetainRate(update.getCmRetainRate());
				nodeConfig.setCpRetainRate(update.getCpRetainRate());
				if (!CollectionUtil.isEmpty(update.getRatios())) {
					_coefficientRatiosVerify(param.getTid(), update.getRatios(), node);
					nodeConfig.setRatios(update.getRatios());
				}
			}
			break;
		default:
			break;
		}
		return Consts.RESULT.OK;
	}
	
	private Result<Void> _coefficientRatiosVerify(int tid, Map<Integer, Map<Integer, Integer>> ratios, PoundageNode node) {
		Map<Integer, CoefficientDocument> coefficients = _coefficientDocuments(node);
		for (Entry<Integer, Map<Integer, Integer>> entry : ratios.entrySet()) {
			if (null == entry.getKey())
				return Consts.RESULT.FORBID;
			CoefficientDocument coefficient = _coefficient(tid, coefficients, entry.getKey());
			if (null == coefficient)
				return BtkjConsts.RESULT.COEFFICIENT_NOT_EXIST;
			Map<Integer, CoefficientRange> ranges = coefficientRanges(tid, entry.getKey());
			if (CollectionUtil.isEmpty(ranges) || null == entry.getValue())
				return Consts.RESULT.FORBID;
			for (Entry<Integer, Integer> e : entry.getValue().entrySet()) {
				if (ranges.containsKey(e.getKey()))
					return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_RANGE_NOT_EXIST;
				e.setValue(Math.min(BtkjConsts.LIMITS.MAX_COEFFICIENT_RANGE_RATIO, e.getValue()));
				e.setValue(Math.max(BtkjConsts.LIMITS.MIN_COEFFICIENT_RANGE_RATIO, e.getValue()));
			}
		}
		return Consts.RESULT.OK;
	}

	private Map<Integer, CoefficientDocument> _coefficientDocuments(PoundageNode node) {
		if (CollectionUtil.isEmpty(node.getCoefficients()))
			return Collections.EMPTY_MAP;
		Set<Integer> coefficients = new HashSet<Integer>(node.getCoefficients());
		Map<Integer, CoefficientDocument> map = new HashMap<Integer, CoefficientDocument>();
		_searcheCoefficients(map, coefficientDocuments, coefficients);
		return map;
	}
	
	private CoefficientDocument _coefficient(int tid, Map<Integer, CoefficientDocument> documents, int coefficientId) {
		if (CollectionUtil.isEmpty(documents))
			return null;
		for (Entry<Integer, CoefficientDocument> entry : documents.entrySet()) {
			if (entry.getKey() == coefficientId)
				return entry.getValue();
			CoefficientDocument document = _coefficient(tid, entry.getValue().children(), coefficientId);
			if (null != document)
				return document;
		}
		return null;
	}
}
