package org.btkj.vehicle.realm.poundage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.VehicleUtil;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Employee.Mod;
import org.btkj.pojo.entity.vehicle.CoefficientRange;
import org.btkj.pojo.entity.vehicle.PoundageConfig;
import org.btkj.pojo.entity.vehicle.PoundageConfig.NodeConfig;
import org.btkj.pojo.entity.vehicle.PoundageNode;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.enums.PoundageType;
import org.btkj.pojo.model.BonusPoundage;
import org.btkj.pojo.model.CoefficientDocument;
import org.btkj.pojo.model.PoundageDocument;
import org.btkj.vehicle.cache.CacheService;
import org.btkj.vehicle.cache.domain.CfgVehicle;
import org.btkj.vehicle.mongo.PoundageConfigMapper;
import org.rapid.util.common.Consts;
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
	
	public void caculatePoundage(Map<Integer, PoundageDocument> documents, VehicleOrder order, Employee employee) {
		PoundageConfig poundageConfig = poundageConfigMapper.getByKey(String.valueOf(order.getTid()));
		Map<Integer, Map<Integer, NodeConfig>> insurersConfig = null == poundageConfig ? null : poundageConfig.getConfigs();
		Map<Integer, NodeConfig> configs = null == insurersConfig ? null : insurersConfig.get(order.getInsurerId());
		Map<Integer, MatchNode> recursions = new HashMap<Integer, MatchNode>();
		_recursionNode(recursions, documents, order, 0, null == configs ? null : new HashMap<Integer, NodeConfig>(configs));
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
		int cmrate = config.getCmRate() - config.getCmRetainRate();
		int cprate = config.getCpRate() - config.getCpRetainRate();
		Mod mod = VehicleUtil.employeeModFromVehicleUsedType(order.getTips().getUsedType());
		if ((employee.getMod() & mod.mark()) == mod.mark()) {
			cmrate += employee.getCommercialRate();
			cprate += employee.getCompulsoryRate();
		}
		if (!CollectionUtil.isEmpty(config.getRatios()) && config.isEffective()) {
			Result<Map<Integer, CoefficientDocument>> result = poundage.coefficientDocuments(pnode.getId());
			if (!CollectionUtil.isEmpty(result.attach())) {
				Map<String, Integer> record = new HashMap<String, Integer>();
				_recursionCoefficient(order, config.getRatios(), result.attach(), record, null);
				int ratio = 0;
				for (Integer detla : record.values()) {
					if (null != detla)
						ratio += detla;
				}
				cmrate += ratio;
			}
		}
		cmrate = Math.max(cmrate, BtkjConsts.LIMITS.MIN_POUNDAGE_RATE);
		cmrate = Math.min(cmrate, BtkjConsts.LIMITS.MAX_POUNDAGE_RATE);
		cprate = Math.max(cprate, BtkjConsts.LIMITS.MIN_POUNDAGE_RATE);
		cprate = Math.min(cprate, BtkjConsts.LIMITS.MAX_POUNDAGE_RATE);
		String cmtotal = order.getTips().getSchema().getCommercialTotal();
		String cmbonus = null;
		if (null != cmtotal)
			cmbonus = new BigDecimal(cmtotal).multiply(new BigDecimal(cmrate)).divide(new BigDecimal(1000)).setScale(2, RoundingMode.HALF_UP).toString();
		String cptotal = order.getTips().getSchema().getCompulsoryTotal();
		String cpbonus = null;
		if (null != cptotal)
			cpbonus = new BigDecimal(cptotal).multiply(new BigDecimal(cprate)).divide(new BigDecimal(1000)).setScale(2, RoundingMode.HALF_UP).toString();
		BonusPoundage bonus = new BonusPoundage(cmbonus, cpbonus);
		order.setBonus(bonus);
	}
	
	private void _recursionNode(Map<Integer, MatchNode> recursions, Map<Integer, PoundageDocument> documents, VehicleOrder order, int pmod, Map<Integer, NodeConfig> configs) {
		if (CollectionUtil.isEmpty(documents) || CollectionUtil.isEmpty(configs))
			return;
		for (Entry<Integer, PoundageDocument> entry : documents.entrySet()) {
			PoundageNode node = entry.getValue().node();
			PoundageType type = node.getType();
			NodeConfig config = configs.remove(node.getId());
			if (null == config || node.isPoly())
				_recursionNode(recursions, entry.getValue().children(), order, pmod, configs);
			else {
				switch (type) {
				case NO_BIZ_COACH_MODEL:
				case NO_BIZ_COACH_DEPT:
				case NO_BIZ_COACH_BRAND:
					CfgVehicle cfgVehicle = cacheService.getById(CacheService.CFG_VEHICLE, order.getTips().getName());
					if (null == cfgVehicle)
						continue;
					if (!cfgVehicle.match(node))
						continue;
					break;
				default:
					if (!type.matches(order, node))
						continue;
					break;
				}
				recursions.remove(pmod);
				MatchNode matchNode = new MatchNode(pmod, config, node);
				Iterator<MatchNode> iterator = recursions.values().iterator();
				while (iterator.hasNext()) {
					MatchNode temp = iterator.next();
					if (null == temp || temp.getNode().getGid() != node.getGid())
						continue;
					if (temp.getNode().getPriority() == node.getPriority()) {
						logger.error("手续费配置异常，车险订单 - {} 同时匹配多组规则：[gid:{},priority:{}], 随机选择一组作为匹配条件！", order.get_id(), node.getGid(), node.getPriority());
						matchNode = null;
					} else if (temp.getNode().getPriority() > node.getPriority()) {
						iterator.remove();
						pmod |= type.mod();
					}
				}
				recursions.put(pmod, matchNode);
				_recursionNode(recursions, entry.getValue().children(), order, pmod, configs);
			}
		}
	}
	
	private void _recursionCoefficient(VehicleOrder order, Map<Integer, Map<Integer, Integer>> ratios, Map<Integer, CoefficientDocument> coefficients, Map<String, Integer> record, String path) {
		if (CollectionUtil.isEmpty(coefficients))
			return;
		for (Entry<Integer, CoefficientDocument> entry : coefficients.entrySet()) {
			String nextPath = null == path ? String.valueOf(entry.getKey()) : path + Consts.SYMBOL_UNDERLINE + entry.getKey();
			Integer ratio = record.remove(path);
			Map<Integer, Integer> rangeRatios = ratios.get(entry.getKey());
			if (null != rangeRatios) {
				CoefficientType coefficientType = entry.getValue().node().getType();
				Map<Integer, CoefficientRange> ranges = poundage.coefficientRanges(order.getTid(), entry.getKey());
				if (!CollectionUtil.isEmpty(ranges)) {
					for (CoefficientRange range : ranges.values()) {
						if (!coefficientType.satisfy(range, order)) 
							continue;
						Integer temp = rangeRatios.get(range.getId());
						if (null == temp)
							continue;
						ratio = temp;	
						break;					// 同一个系数多个范围最多有且只有一个范围能够满足条件
					}
				}
			}
			if (null != ratio)
				record.put(nextPath, ratio);
			_recursionCoefficient(order, ratios, entry.getValue().children(), record, nextPath);
		}
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
