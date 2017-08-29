package org.btkj.vehicle.realm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleBrand;
import org.btkj.pojo.entity.VehicleDept;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.vehicle.cache.CacheService;
import org.btkj.vehicle.cache.domain.CfgCoefficient;
import org.btkj.vehicle.cache.domain.CfgCoefficientRange;
import org.btkj.vehicle.cache.domain.CfgCoefficientStructure;
import org.btkj.vehicle.cache.domain.CfgCoefficientStructure.CfgCoefficientNode;
import org.btkj.vehicle.cache.domain.CfgPoundageNode;
import org.btkj.vehicle.cache.domain.CfgPoundageStructure;
import org.btkj.vehicle.mongo.PoundageConfigMapper;
import org.btkj.vehicle.pojo.entity.PoundageConfig;
import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorPoundageNode;
import org.btkj.vehicle.pojo.enums.PoundageNodeType;
import org.btkj.vehicle.pojo.model.CoefficientRange;
import org.btkj.vehicle.pojo.model.CoefficientStructure;
import org.btkj.vehicle.pojo.model.CoefficientStructure.CoefficientNode;
import org.btkj.vehicle.pojo.model.PoundageErogidicMessage;
import org.btkj.vehicle.pojo.model.PoundageStructure;
import org.btkj.vehicle.pojo.param.PoundageErogidicParam;
import org.btkj.vehicle.realm.poundage.ErgodicCallback;
import org.btkj.vehicle.redis.PoundageCoefficientRangeMapper;
import org.btkj.vehicle.redis.VehicleBrandMapper;
import org.btkj.vehicle.redis.VehicleDeptMapper;
import org.btkj.vehicle.redis.VehicleModelMapper;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("poundage")
public class Poundage {
	
	private static final Logger logger = LoggerFactory.getLogger(Poundage.class);
	
	public static final int NO_BIZ_COACH_COEFFICIENT_STRUCTURE				= 1;

	@Resource
	private CacheService cacheService;
	@Resource
	private ConfigService configService;
	@Resource
	private PoundageConfigMapper poundageConfigMapper;
	private Map<Integer, PoundageStructure> poundageStructures;
	@Resource
	private PoundageCoefficientRangeMapper poundageCoefficientRangeMapper;
	
	@Resource
	private VehicleDeptMapper vehicleDeptMapper;
	@Resource
	private VehicleBrandMapper vehicleBrandMapper;
	@Resource
	private VehicleModelMapper vehicleModelMapper;
	
	@Resource(name = "normalErogidicCallback")
	private ErgodicCallback<?> normalErogidicCallback;
	@Resource(name = "updateErogidicCallback")
	private ErgodicCallback<?> updateErogidicCallback;
	@Resource(name = "nodeConfigErogidicCallback")
	private ErgodicCallback<?> nodeConfigErogidicCallback;
	
	private Map<Integer, CoefficientStructure> coefficientStructures = new HashMap<Integer, CoefficientStructure>();
	
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
		
		CoefficientStructure temp = coefficientStructures.get(coefficientStructure.getId());
		if (null == temp) {
			temp = new CoefficientStructure();
			coefficientStructures.put(coefficientStructure.getId(), temp);
		}
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
	
	/**
	 * 计算手续费
	 */
	public void calculate(VehicleOrder order) {
		
	}
	
	public Result<?> poundageErgodic(PoundageErogidicParam param) {
		switch (param.getType()) {
		case EDIT:
			return poundageErgodic(param, updateErogidicCallback);
		case NODE_CONFIG:
			return poundageErgodic(param, nodeConfigErogidicCallback);
		default:
			return poundageErgodic(param, normalErogidicCallback);
		}
	}
	
	public <MESSAGE extends PoundageErogidicMessage> Result<?> poundageErgodic(MESSAGE message, ErgodicCallback callback) {
		if (null == configService.insurer(message.getInsurerId()))
			return BtkjConsts.RESULT.INSURER_NOT_EXIST;
		int cfgNodeId = message.getNodePath().poll();
		PoundageStructure current = poundageStructures.get(cfgNodeId);
		if (null == current)
			return BtkjConsts.RESULT.POUNDAGE_NODE_NOT_EXIST;
		PoundageConfig config = callback.getPoundageConfig(message, cfgNodeId);
		Result<?> result = _poundageErgodic(message, current, config.getStructure().get(cfgNodeId), null, callback);
		if (result.isSuccess()) 
			callback.finishErgodic(config, config.getStructure().get(cfgNodeId), null);
		return result;
	}
	
	private <MESSAGE extends PoundageErogidicMessage> Result<?> _poundageErgodic(MESSAGE message, PoundageStructure current, MirrorPoundageNode mirrorCurrent, MirrorPoundageNode parent, ErgodicCallback callback) {
		Integer nextNodeId = message.getNodePath().poll();
		if (null == nextNodeId)
			return callback.execute(message, current, mirrorCurrent);
		else {
			Map<Integer, PoundageStructure> children = _children(current);
			current = null == children ? null : children.get(nextNodeId);
			if (null == current)
				return BtkjConsts.RESULT.POUNDAGE_NODE_NOT_EXIST;
			MirrorPoundageNode nextMirrorCurrent = callback.nextMirrorPoundageNode(nextNodeId, mirrorCurrent, parent);
			if (null == nextMirrorCurrent)
				return BtkjConsts.RESULT.POUNDAGE_NODE_CONFIG_NOT_EXIST;
			Result<?> result = _poundageErgodic(message, current, nextMirrorCurrent, mirrorCurrent, callback);
			if (result.isSuccess())
				callback.finishErgodic(null, mirrorCurrent, parent);
			return result;
		}
	}
	
	/**
	 * 有些节点需要自定义
	 * 
	 * @param structure
	 * @return
	 */
	private Map<Integer, PoundageStructure> _children(PoundageStructure structure) {
		Map<Integer, PoundageStructure> nodes = new HashMap<Integer, PoundageStructure>();
		switch (structure.getType()) {
		case SPECIAL:
			Map<Integer, VehicleBrand> brands = vehicleBrandMapper.getAll();
			for (VehicleBrand brand : brands.values()) {
				PoundageStructure node = new PoundageStructure();
				node.setId(brand.getId());
				node.setName(brand.getName());
				node.setType(PoundageNodeType.VEHICLE_BRAND);
				node.setCoefficientStructure(coefficientStructures.get(NO_BIZ_COACH_COEFFICIENT_STRUCTURE));
			}
			return nodes;
		case VEHICLE_BRAND:
			Map<Integer, VehicleDept> depts = vehicleDeptMapper.getAll();
			for (VehicleDept dept : depts.values()) {
				PoundageStructure node = new PoundageStructure();
				node.setId(dept.getId());
				node.setName(dept.getName());
				node.setType(PoundageNodeType.VEHICLE_NAME);
				node.setCoefficientStructure(coefficientStructures.get(NO_BIZ_COACH_COEFFICIENT_STRUCTURE));
			}
			return nodes;
		case VEHICLE_DEPT:
			Map<Integer, VehicleModel> models = vehicleModelMapper.getAll();
			for (VehicleModel model : models.values()) {
				PoundageStructure node = new PoundageStructure();
				node.setId(model.getId());
				node.setName(model.getName());
				node.setType(PoundageNodeType.VEHICLE_NAME);
				node.setCoefficientStructure(coefficientStructures.get(NO_BIZ_COACH_COEFFICIENT_STRUCTURE));
			}
			return nodes;
		default:
			return null == structure.getChildren() ? null : structure.getChildren();
		}
	}
	
	public Map<Integer, PoundageStructure> getPoundageStructures() {
		return poundageStructures;
	}
}
