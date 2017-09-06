package org.btkj.vehicle.realm.poundage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.entity.EmployeePO.Mod;
import org.btkj.vehicle.cache.CacheService;
import org.btkj.vehicle.mongo.PoundageConfigMapper;
import org.btkj.vehicle.pojo.entity.CoefficientNode;
import org.btkj.vehicle.pojo.entity.CoefficientRange;
import org.btkj.vehicle.pojo.entity.PoundageCoefficientRange;
import org.btkj.vehicle.pojo.entity.PoundageConfig;
import org.btkj.vehicle.pojo.entity.PoundageConfig.NodeConfig;
import org.btkj.vehicle.pojo.entity.PoundageNode;
import org.btkj.vehicle.pojo.entity.TenantInsurer;
import org.btkj.vehicle.pojo.model.CoefficientDocument;
import org.btkj.vehicle.pojo.model.NodeConfigModel;
import org.btkj.vehicle.pojo.model.PoundageDocument;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam.NodeConfigInfo;
import org.btkj.vehicle.redis.PoundageCoefficientRangeMapper;
import org.btkj.vehicle.redis.TenantInsurerMapper;
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
	private TenantInsurerMapper tenantInsurerMapper;
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
	public void calculate(VehicleOrder order, EmployeePO employee) {
		if ((employee.getMod() & Mod.PAY_FULL.mark()) == Mod.PAY_FULL.mark())
			poundageDocumentFactory.caculatePoundage(poundageDocuments, order, employee);
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
	
	public NodeConfigModel poundageConfig(int tid, int insurerId, int nodeId, int coefficientId) {
		PoundageConfig config = poundageConfigMapper.getByKey(String.valueOf(tid));
		Map<Integer, Map<Integer, NodeConfig>> map = null == config ? null : config.getConfigs();
		Map<Integer, NodeConfig> configs = null == map ? null : map.get(insurerId);
		NodeConfig nodeConfig = null == configs ? null : configs.get(nodeId);
		Map<Integer, Map<Integer, Integer>> ratios = null == nodeConfig ? null : nodeConfig.getRatios();
		return null == nodeConfig ? null : new NodeConfigModel(nodeConfig, null == ratios ? null : ratios.get(coefficientId));
	}
	
	public Result<Void> poundageConfigEdit(PoundageConfigEditParam param) {
		PoundageConfig config = poundageConfigMapper.getByKey(String.valueOf(param.getTid()));
		if (null == config) 
			config = new PoundageConfig(param.getTid());
		Map<Integer, Map<Integer, NodeConfig>> map = null == config ? null : config.getConfigs();
		if (null == map) {
			map = new HashMap<Integer, Map<Integer, NodeConfig>>();
			config.setConfigs(map);
		}
		Map<Integer, NodeConfig> configs = null == map ? null : map.get(param.getInsurerId());
		if (null == configs) {
			TenantInsurer insurer = tenantInsurerMapper.getByTidAndInsurerId(param.getTid(), param.getInsurerId());
			if (null == insurer)
				return BtkjConsts.RESULT.INSURER_NOT_EXIST;
			configs = new HashMap<Integer, NodeConfig>();
			map.put(param.getInsurerId(), configs);
		}
		NodeConfig nodeConfig = null == configs ? null : configs.get(param.getNodeId());
		PoundageNode node = null;
		if (null == nodeConfig) {
			node = cacheService.getById(CacheService.POUNDAGE_NODE, param.getNodeId());
			if (null == node)
				return BtkjConsts.RESULT.POUNDAGE_NODE_NOT_EXIST;
			nodeConfig = new NodeConfig();
			configs.put(node.getId(), nodeConfig);
		}
		NodeConfigInfo info = param.getConfig();
		if (null == info)
			configs.remove(param.getNodeId());
		else {
			nodeConfig.setCmRate(info.getCmRate());
			nodeConfig.setCpRate(info.getCpRate());
			nodeConfig.setEffective(info.isEffective());
			nodeConfig.setCmRetainRate(info.getCmRetainRate());
			nodeConfig.setCpRetainRate(info.getCpRetainRate());
			if (null != info.getRangeId() && null != info.getCoefficientId()) {
				if (null == node) {
					node = cacheService.getById(CacheService.POUNDAGE_NODE, param.getNodeId());
					if (null == node)
						return BtkjConsts.RESULT.POUNDAGE_NODE_NOT_EXIST;
				}
				Map<Integer, CoefficientDocument> coefficients = coefficientDocuments(param.getNodeId()).attach();
				if (!_hasCoefficient(coefficients, info.getCoefficientId()))
					return Consts.RESULT.FORBID;
				Map<Integer, CoefficientRange> ranges = coefficientRanges(param.getTid(), info.getCoefficientId());
				CoefficientRange range = null == ranges ? null : ranges.get(info.getRangeId());
				if (null == range)
					return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_RANGE_NOT_EXIST;
				nodeConfig.addRatio(info.getCoefficientId(), info.getRangeId(), info.getRangeRate());
			}
		}
		poundageConfigMapper.insert(config);
		return Consts.RESULT.OK;
	}
	
	private boolean _hasCoefficient(Map<Integer, CoefficientDocument> coefficients, int coefficient) { 
		if (CollectionUtil.isEmpty(coefficients))
			return false;
		for (Entry<Integer, CoefficientDocument> entry : coefficients.entrySet()) {
			if (entry.getKey() == coefficient)
				return true;
			boolean flag = _hasCoefficient(entry.getValue().children(), coefficient);
			if (flag)
				return true;
		}
		return false;
	}
}
