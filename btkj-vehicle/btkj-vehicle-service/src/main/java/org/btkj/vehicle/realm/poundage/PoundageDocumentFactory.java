package org.btkj.vehicle.realm.poundage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.vehicle.cache.CacheService;
import org.btkj.vehicle.cache.domain.CfgVehicle;
import org.btkj.vehicle.mongo.PoundageConfigMapper;
import org.btkj.vehicle.pojo.entity.PoundageConfig;
import org.btkj.vehicle.pojo.entity.PoundageConfig.NodeConfig;
import org.btkj.vehicle.pojo.entity.PoundageNode;
import org.btkj.vehicle.pojo.enums.PoundageType;
import org.btkj.vehicle.pojo.model.CoefficientDocument;
import org.btkj.vehicle.pojo.model.PoundageDocument;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.math.tree.PBTreeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("poundageDocumentFactory")
public class PoundageDocumentFactory extends PBTreeFactory<Integer, PoundageNode, PoundageDocument> {
	
	private static final Logger logger = LoggerFactory.getLogger(PoundageDocumentFactory.class);
	
	@Resource
	private Poundage poundage;
	@Resource
	private CacheService cacheService;
	@Resource
	private PoundageConfigMapper poundageConfigMapper;
	
	@Override
	public PoundageDocument instance(PoundageNode node) {
		return new PoundageDocument(node);
	}
	
	public void caculatePoundage(Map<Integer, PoundageDocument> documents, VehicleOrder order) {
		PoundageConfig poundageConfig = poundageConfigMapper.getByKey(String.valueOf(order.getTid()));
		Map<Integer, Map<Integer, NodeConfig>> insurersConfig = null == poundageConfig ? null : poundageConfig.getConfigs();
		Map<Integer, NodeConfig> map = null == insurersConfig ? null : insurersConfig.get(order.getInsurerId());
		if (null == map)
			return;
		Map<Integer, MatchNode> recursions = new HashMap<Integer, MatchNode>();
		_recursionNode(recursions, documents, order, 0, map);
		Iterator<Entry<Integer, MatchNode>> iterator = recursions.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, MatchNode> entry = iterator.next();
			if (null == entry.getValue())
				iterator.remove();
		}
		if (CollectionUtil.isEmpty(recursions))
			return;
		if (recursions.size() != 1) 
			logger.error("车险订单 - {} 匹配多条手续费规则，随机选择一条！", order.get_id());
		MatchNode temp = null;
		for (MatchNode node : recursions.values()) {
			if (null == temp) {
				temp = node;
				break;
			}
		}
		NodeConfig config = temp.config;
		PoundageNode pnode = temp.node;
		if (!CollectionUtil.isEmpty(config.getRatios())) {
			Result<Map<Integer, CoefficientDocument>> result = poundage.coefficientDocuments(pnode.getId());
			if (!CollectionUtil.isEmpty(result.attach())) {
				
			}
		}
	}
	
	private void _recursionNode(Map<Integer, MatchNode> recursions, Map<Integer, PoundageDocument> documents, VehicleOrder order, int pmod, Map<Integer, NodeConfig> configs) {
		if (CollectionUtil.isEmpty(documents))
			return;
		for (Entry<Integer, PoundageDocument> entry : documents.entrySet()) {
			PoundageNode node = entry.getValue().node();
			PoundageType type = node.getType();
			switch (type) {
			case NO_BIZ_COACH_MODEL:
				CfgVehicle cfgVehicle = cacheService.getById(CacheService.CFG_VEHICLE, order.getTips().getName());
				if (null == cfgVehicle)
					continue;
				if (!cfgVehicle.getModel().equals(node.getCval()[0]))
					continue;
				break;
			case NO_BIZ_COACH_DEPT:
				cfgVehicle = cacheService.getById(CacheService.CFG_VEHICLE, order.getTips().getName());
				if (null == cfgVehicle)
					continue;
				if (!cfgVehicle.getDept().equals(node.getCval()[0]))
					continue;
				break;
			case NO_BIZ_COACH_BRAND:
				cfgVehicle = cacheService.getById(CacheService.CFG_VEHICLE, order.getTips().getName());
				if (null == cfgVehicle)
					continue;
				if (!cfgVehicle.getBrand().equals(node.getCval()[0]))
					continue;
				break;
			default:
				if (!type.matches(order, node))
					continue;
				break;
			}
			MatchNode current = recursions.remove(pmod);
			int nmod = type.mod() | pmod;
			NodeConfig config = configs.get(node.getId());
			if (null != config && config.isEffective()) {
				current = new MatchNode(nmod, config, node);
				Iterator<MatchNode> iterator = recursions.values().iterator();
				while (iterator.hasNext()) {
					MatchNode temp = iterator.next();
					if (null == temp || temp.getNode().getGid() != node.getGid())
						continue;
					if (temp.getNode().getPriority() == node.getPriority()) {
						logger.error("手续费配置异常，车险订单 - {} 同时匹配多组规则：[gid:{},priority:{}], 随机选择一组作为匹配条件！", order.get_id(), node.getGid(), node.getPriority());
						current = null;
					} else if (temp.getNode().getPriority() > node.getPriority())
						iterator.remove();
					else
						current = null;
				}
			}
			recursions.put(nmod, current);
			_recursionNode(recursions, entry.getValue().children(), order, nmod, configs);
		}
	}
	
	private void _recursionCoefficient(Map<Integer, Map<Integer, Integer>> ratios, Map<Integer, CoefficientDocument> coefficients) {
		
	}
	
	private class MatchNode {
		private int mod;
		private NodeConfig config;
		private PoundageNode node;
		public MatchNode(int mod, NodeConfig config, PoundageNode node) {
			this.mod = mod;
			this.node = node;
			this.config = config;
		}
		public int getMod() {
			return mod;
		}
		public void setMod(int mod) {
			this.mod = mod;
		}
		public PoundageNode getNode() {
			return node;
		}
		public void setNode(PoundageNode node) {
			this.node = node;
		}
		public NodeConfig getConfig() {
			return config;
		}
		public void setConfig(NodeConfig config) {
			this.config = config;
		}
	}
}
