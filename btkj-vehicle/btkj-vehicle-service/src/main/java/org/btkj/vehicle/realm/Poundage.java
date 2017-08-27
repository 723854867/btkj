package org.btkj.vehicle.realm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.vehicle.cache.CacheService;
import org.btkj.vehicle.cache.domain.CfgCoefficient;
import org.btkj.vehicle.cache.domain.CfgCoefficientRange;
import org.btkj.vehicle.cache.domain.CfgCoefficientStructure;
import org.btkj.vehicle.cache.domain.CfgCoefficientStructure.CfgCoefficientNode;
import org.btkj.vehicle.cache.domain.CfgPoundageNode;
import org.btkj.vehicle.cache.domain.CfgPoundageStructure;
import org.btkj.vehicle.mongo.PoundageConfigMapper;
import org.btkj.vehicle.pojo.entity.PoundageConfig;
import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorCoefficient;
import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorPoundageNode;
import org.btkj.vehicle.pojo.model.CoefficientRange;
import org.btkj.vehicle.pojo.model.CoefficientStructure;
import org.btkj.vehicle.pojo.model.CoefficientStructure.CoefficientNode;
import org.btkj.vehicle.pojo.model.PoundageNodeConfigInfo;
import org.btkj.vehicle.pojo.model.PoundageStructure;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam.Node;
import org.btkj.vehicle.pojo.param.PoundageConfigEditParam.Type;
import org.btkj.vehicle.pojo.param.PoundageNodeConfigParam;
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
	private Map<Integer, PoundageStructure> poundageStructures;
	@Resource
	private PoundageCoefficientRangeMapper poundageCoefficientRangeMapper;
	
	public void init() { 
		Map<Integer, CfgPoundageStructure> map = cacheService.getAll(CacheService.CFG_POUNDAGE_STRUCTURE);
		if (!CollectionUtil.isEmpty(map)) {
			this.poundageStructures = new HashMap<Integer, PoundageStructure>();
			_build(map, null);
		}
	}
	
	private void _build(Map<Integer, CfgPoundageStructure> map, PoundageStructure parent) {
		for (CfgPoundageStructure temp : map.values()) {
			CfgPoundageNode node = cacheService.getById(CacheService.CFG_POUNDAGE_NODE, temp.getCfgPoundageNodeId());
			if (null == node) {
				logger.error("手续费节点{}不存在，车险服务启动失败！", temp.getCfgPoundageNodeId());
				System.exit(1);
			}
			
			PoundageStructure current = new PoundageStructure();
			current.setId(node.getId());
			current.setName(node.getName());
			current.setType(node.getType());
			if (0 != temp.getCfgCoefficientStructureId())
				_buildCoefficientStructure(current, temp.getCfgCoefficientStructureId());
			if (null == parent)
				poundageStructures.put(current.getId(), current);
			else
				parent.addChild(current);
			if (!CollectionUtil.isEmpty(temp.getChildren()))
				_build(temp.getChildren(), current);
		}
	}
	
	private void _buildCoefficientStructure(PoundageStructure structure, int cfgCoefficientStructureId) {
		CfgCoefficientStructure coefficientStructure = cacheService.getById(CacheService.CFG_COEFFICIENT_STRUCTURE, cfgCoefficientStructureId);
		if (null == coefficientStructure) {
			logger.error("手续费系数结构{}不存在，车险服务启动失败！", cfgCoefficientStructureId);
			System.exit(1);
		}
		
		CoefficientStructure temp = new CoefficientStructure();
		temp.setId(coefficientStructure.getId());
		if (!CollectionUtil.isEmpty(coefficientStructure.getNodes())) {
			Map<Integer, CoefficientNode> map = new HashMap<Integer, CoefficientNode>();
			_buildCoefficientNode(coefficientStructure.getNodes(), map, null);
			temp.setNodes(map);
		}
		structure.setCoefficientStructure(temp);
	}
	
	private void _buildCoefficientNode(Map<Integer, CfgCoefficientNode> currentMap, Map<Integer, CoefficientNode> map, CoefficientNode parent) {
		for (CfgCoefficientNode node : currentMap.values()) {
			CfgCoefficient coefficient = cacheService.getById(CacheService.CFG_COEFFICIENT, node.getCfgCoefficientId());
			if (null == coefficient) {
				logger.error("手续费系数类型{}不存在，车险服务启动失败！", node.getCfgCoefficientId());
				System.exit(1);
			}
			
			CoefficientNode temp = new CoefficientNode();
			temp.setName(coefficient.getName());
			temp.setType(coefficient.getType());
			temp.setCustom(coefficient.isCustom());
			temp.setMaxRanges(coefficient.getMaxRanges());
			temp.setCfgCoefficientId(coefficient.getId());
			Set<Integer> set = coefficient.getCfgCoefficientRangeIds();
			if (!CollectionUtil.isEmpty(set)) {
				Map<Integer, CfgCoefficientRange> ranges = cacheService.getByIds(CacheService.CFG_COEFFICIENT_RANGE, set);
				if (ranges.size() != set.size()) {
					logger.error("手续费系数{}范围配置错误，车险服务启动失败！", temp.getCfgCoefficientId());
					System.exit(1);
				}
				Map<Integer, CoefficientRange> m = new HashMap<Integer, CoefficientRange>();
				for (CfgCoefficientRange range : ranges.values()) {
					CoefficientRange r = new CoefficientRange();
					r.setId(range.getId());
					r.setName(range.getName());
					r.setComparison(range.getComparison());
					r.setComparableValue(range.getComparableValue());
					m.put(r.getId(), r);
				}
				temp.setRanges(m);
			}
			if (null == parent) 
				map.put(temp.getCfgCoefficientId(), temp);
			else
				parent.addChild(temp);
			if (!CollectionUtil.isEmpty(node.getChildren())) 
				_buildCoefficientNode(node.getChildren(), map, temp);
		}
	}
	
	public Result<Void> poundageConfigEdit(PoundageConfigEditParam param) {
		if (null == configService.insurer(param.getInsurerId()))
			return BtkjConsts.RESULT.INSURER_NOT_EXIST;
		PoundageConfig config = poundageConfigMapper.getByKey(param.key());
		if (param.getType() == Type.EDIT && null == config) 
			config = new PoundageConfig(param.getTid(), param.getInsurerId());
		Map<Integer, MirrorPoundageNode> structure = null == config ? null : config.getStructure();
		Integer cfgPoundageNodeId = param.getNodePath().poll(); 
		PoundageStructure current = poundageStructures.get(cfgPoundageNodeId);
		if (null == current)
			return BtkjConsts.RESULT.POUNDAGE_NODE_NOT_EXIST;
		MirrorPoundageNode mirrorCurrent = null == structure ? null : structure.get(cfgPoundageNodeId);
		if (param.getType() == Type.EDIT && null == mirrorCurrent) {
			mirrorCurrent = new MirrorPoundageNode();
			mirrorCurrent.setCfgPoundageNodeId(cfgPoundageNodeId);
			if (null == structure)
				structure = new HashMap<Integer, MirrorPoundageNode>();
			structure.put(cfgPoundageNodeId, mirrorCurrent);
			config.setStructure(structure);
		}
		if (null == mirrorCurrent)
			return BtkjConsts.RESULT.POUNDAGE_CONFIG_NODE_NOT_EXIST;
		Result<Void> result = _poundageConfigEdit(config, param, current, mirrorCurrent, null);
		if (param.getType() == Type.DELETE && CollectionUtil.isEmpty(mirrorCurrent.getChildren())
				&& 0 == mirrorCurrent.getCommercialRate() && 0 == mirrorCurrent.getCompulsoryRate()
				&& 0 == mirrorCurrent.getCommercialRetainRate() && 0 == mirrorCurrent.getCompulsoryRetainRate()) {
			structure.remove(mirrorCurrent.getCfgPoundageNodeId());
			if (CollectionUtil.isEmpty(structure))
				poundageConfigMapper.delete(config.key());
		}
		return result;
	}
	
	private Result<Void> _poundageConfigEdit(PoundageConfig config, PoundageConfigEditParam param, PoundageStructure current, MirrorPoundageNode mirrorCurrent, MirrorPoundageNode parent) {
		Integer cfgPoundageNodeId = param.getNodePath().poll();
		if (null == cfgPoundageNodeId)
			return _poundageConfigEdit(param, current, mirrorCurrent, parent);
		else {
			current = null == current.getChildren() ? null : current.getChildren().get(cfgPoundageNodeId);
			if (null == current)
				return BtkjConsts.RESULT.POUNDAGE_NODE_NOT_EXIST;
			MirrorPoundageNode nextMirrorCurrent = null == mirrorCurrent.getChildren() ? null : mirrorCurrent.getChildren().get(cfgPoundageNodeId);
			if (param.getType() == Type.EDIT && null == nextMirrorCurrent) {
				nextMirrorCurrent = new MirrorPoundageNode();
				nextMirrorCurrent.setCfgPoundageNodeId(cfgPoundageNodeId);
				if (null != parent)
					parent.addChild(nextMirrorCurrent);
			}
			if (null == nextMirrorCurrent)
				return BtkjConsts.RESULT.POUNDAGE_CONFIG_NODE_NOT_EXIST;
			Result<Void> result = _poundageConfigEdit(config, param, current, nextMirrorCurrent, mirrorCurrent);
			if (null != parent && param.getType() == Type.DELETE && CollectionUtil.isEmpty(mirrorCurrent.getChildren())
					&& 0 == mirrorCurrent.getCommercialRate() && 0 == mirrorCurrent.getCompulsoryRate()
					&& 0 == mirrorCurrent.getCommercialRetainRate() && 0 == mirrorCurrent.getCompulsoryRetainRate())
				parent.removeChild(mirrorCurrent);
			return result;
		}
	}
	
	private Result<Void> _poundageConfigEdit(PoundageConfigEditParam param, PoundageStructure current, MirrorPoundageNode mirrorCurrent, MirrorPoundageNode parent) {
		switch (param.getType()) {
		case EDIT:
			Node node = param.getNode();
			mirrorCurrent.setCommercialRate(node.getCommercialRate());
			mirrorCurrent.setCompulsoryRate(node.getCompulsoryRate());
			mirrorCurrent.setCommercialRetainRate(node.getCommercialRetainRate());
			mirrorCurrent.setCompulsoryRetainRate(node.getCompulsoryRetainRate());
			_poundageCoefficientEdit(param.getTid(), node.getCoefficients(), current, mirrorCurrent);
			break;
		case DELETE:
			mirrorCurrent.setCommercialRate(0);
			mirrorCurrent.setCompulsoryRate(0);
			mirrorCurrent.setCoefficients(null);
			mirrorCurrent.setCommercialRetainRate(0);
			mirrorCurrent.setCompulsoryRetainRate(0);
			parent.removeChild(mirrorCurrent);
		case EFFECTIVE:
			mirrorCurrent.setCoefficientsEffective(true);
			break;
		case INEFFECTIVE:
			mirrorCurrent.setCoefficientsEffective(false);
			break;
		default:
			return Consts.RESULT.FORBID;
		}
		return Consts.RESULT.OK;
	}
	
	private Result<Void> _poundageCoefficientEdit(int tid, Map<Integer, MirrorCoefficient> update, PoundageStructure current, MirrorPoundageNode mirrorCurrent) {
		if (!CollectionUtil.isEmpty(update)) {
			CoefficientStructure cs = current.getCoefficientStructure();
			Map<Integer, CoefficientNode> refrence = null == cs ? null : cs.getNodes();
			if (null == refrence) 
				return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_EXIST;
			Result<Void> result = _poundageCoefficientCheck(tid, update, refrence);
			if (!result.isSuccess())
				return result;
			mirrorCurrent.setCoefficients(update);
		} else 
			mirrorCurrent.setCoefficients(null);
		return Consts.RESULT.OK;
	}
	
	private Result<Void> _poundageCoefficientCheck(int tid, Map<Integer, MirrorCoefficient> update, Map<Integer, CoefficientNode> refrence) {
		for (Entry<Integer, MirrorCoefficient> entry : update.entrySet()) {
			CoefficientNode coefficient = refrence.get(entry.getKey());
			if (null == coefficient)
				return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_EXIST;
			MirrorCoefficient mirrorCoefficient = entry.getValue();
			mirrorCoefficient.setId(entry.getKey());
			Map<Integer, Integer> ratios = mirrorCoefficient.getRatios();
			Set<Integer> ranges = null;
			if (coefficient.isCustom())
				ranges = poundageCoefficientRangeMapper.ranges(tid, coefficient.getCfgCoefficientId()).keySet();
			else 
				ranges = null == coefficient.getRanges() ? null : coefficient.getRanges().keySet();
			for (Entry<Integer, Integer> e : ratios.entrySet()) {
				if (!ranges.remove(e.getKey()))
					return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_RANGE_NOT_EXIST;
			}
			update = entry.getValue().getChildren();
			if (CollectionUtil.isEmpty(update))
				continue;
			refrence = coefficient.getChildren();
			if (null == refrence)
				return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_EXIST;
			return _poundageCoefficientCheck(tid, update, refrence);
		}
		return Consts.RESULT.OK;
	}
	
	public PoundageNodeConfigInfo poundageNodeConfig(PoundageNodeConfigParam param) {
		PoundageConfig config = poundageConfigMapper.getByKey(param.key());
		Map<Integer, MirrorPoundageNode> structure = null == config ? null : config.getStructure();
		if (CollectionUtil.isEmpty(structure))
			return null;
		MirrorPoundageNode node = _searchMirrorPoundageNode(structure, param.getNodePath());
		if (null == node)
			return null;
		Map<Integer, MirrorCoefficient> coefficients = node.getCoefficients();
		MirrorCoefficient coefficient = null;
		if (!CollectionUtil.isEmpty(coefficients)) 
			coefficient = _searchMirrorCoefficient(coefficients, param.getCfgCoefficientId());
		return new PoundageNodeConfigInfo(node, coefficient);
	}
	
	private MirrorPoundageNode _searchMirrorPoundageNode(Map<Integer, MirrorPoundageNode> nodes, LinkedList<Integer> nodePath) {
		Integer cfgPoundageNodeId = nodePath.poll();
		MirrorPoundageNode node = nodes.get(cfgPoundageNodeId);
		if (nodePath.isEmpty()) 
			return node;
		else {
			if (null == node || CollectionUtil.isEmpty(node.getChildren()))
				return null;
			return _searchMirrorPoundageNode(node.getChildren(), nodePath);
		}
	}
	
	private MirrorCoefficient _searchMirrorCoefficient(Map<Integer, MirrorCoefficient> coefficients, int cfgCoefficientId) {
		for (Entry<Integer, MirrorCoefficient> entry : coefficients.entrySet()) {
			if (entry.getKey() == cfgCoefficientId)
				return entry.getValue();
			if (CollectionUtil.isEmpty(entry.getValue().getChildren())) 
				continue;
			MirrorCoefficient coefficient = _searchMirrorCoefficient(entry.getValue().getChildren(), cfgCoefficientId);
			if (null != coefficient)
				return coefficient;
		}
		return null;
	}
	
	public Map<Integer, PoundageStructure> getPoundageStructures() {
		return poundageStructures;
	}
}
