package org.btkj.vehicle.realm.poundage;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.btkj.pojo.BtkjConsts;
import org.btkj.vehicle.pojo.entity.PoundageConfig;
import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorCoefficient;
import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorPoundageNode;
import org.btkj.vehicle.pojo.model.CoefficientStructure;
import org.btkj.vehicle.pojo.model.CoefficientStructure.CoefficientNode;
import org.btkj.vehicle.pojo.model.PoundageStructure;
import org.btkj.vehicle.pojo.param.PoundageErogidicParam;
import org.btkj.vehicle.pojo.param.PoundageErogidicParam.Node;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.springframework.stereotype.Component;

@Component("updateErogidicCallback")
public class UpdateErogidicCallback extends ErgodicCallback<PoundageErogidicParam> {
	
	@Override
	public Result<Void> execute(PoundageErogidicParam message, PoundageStructure current, MirrorPoundageNode node) {
		Node update = message.getNode();
		node.setCommercialRate(update.getCommercialRate());
		node.setCompulsoryRate(update.getCompulsoryRate());
		node.setCommercialRetainRate(update.getCommercialRetainRate());
		node.setCompulsoryRetainRate(update.getCompulsoryRetainRate());
		return _coefficientVaidate(message, current, node);
	}
	
	private Result<Void> _coefficientVaidate(PoundageErogidicParam message, PoundageStructure current, MirrorPoundageNode mirrorCurrent) {
		if (!CollectionUtil.isEmpty(message.getNode().getCoefficients())) {
			CoefficientStructure coefficientStructure = current.getCoefficientStructure();
			Map<Integer, CoefficientNode> coefficientNodes = null == coefficientStructure ? null : coefficientStructure.getNodes();
			if (null == coefficientNodes) 
				return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_EXIST;
			Result<Void> result = _coefficientValidate(message.getTid(), message.getNode().getCoefficients(), coefficientNodes);
			if (!result.isSuccess())
				return result;
			mirrorCurrent.setCoefficients(message.getNode().getCoefficients());
		} else 
			mirrorCurrent.setCoefficients(null);
		return Consts.RESULT.OK;
	}
	
	private Result<Void> _coefficientValidate(int tid, Map<Integer, MirrorCoefficient> update, Map<Integer, CoefficientNode> coefficientNodes) {
		for (Entry<Integer, MirrorCoefficient> entry : update.entrySet()) {
			CoefficientNode coefficient = coefficientNodes.get(entry.getKey());
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
				if (!ranges.contains(e.getKey()))
					return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_RANGE_NOT_EXIST;
			}
			Map<Integer, MirrorCoefficient> childUpdate = entry.getValue().getChildren();
			if (CollectionUtil.isEmpty(childUpdate))
				continue;
			Map<Integer, CoefficientNode> childCoefficientNodes = coefficient.getChildren();
			if (CollectionUtil.isEmpty(childCoefficientNodes))
				return BtkjConsts.RESULT.POUNDAGE_COEFFICIENT_NOT_EXIST;
			return _coefficientValidate(tid, update, childCoefficientNodes);
		}
		return Consts.RESULT.OK;
	}
	
	/**
	 * 第一个节点需要在这里初始化
	 */
	@Override
	public PoundageConfig getPoundageConfig(PoundageErogidicParam message, int cfgNodeId) {
		PoundageConfig config = super.getPoundageConfig(message, cfgNodeId);
		if (null == config) 
			config = new PoundageConfig(message.getTid(), message.getInsurerId());
		Map<Integer, MirrorPoundageNode> structure = null == config ? null : config.getStructure();
		MirrorPoundageNode mirrorCurrent = null == structure ? null : structure.get(cfgNodeId);
		if (null == mirrorCurrent) {
			mirrorCurrent = new MirrorPoundageNode();
			mirrorCurrent.setCfgPoundageNodeId(cfgNodeId);
			if (null == structure)
				structure = new HashMap<Integer, MirrorPoundageNode>();
			structure.put(cfgNodeId, mirrorCurrent);
			config.setStructure(structure);
		}
		return config;
	}
	
	@Override
	public MirrorPoundageNode nextMirrorPoundageNode(int nextNodeId, MirrorPoundageNode current, MirrorPoundageNode parent) {
		MirrorPoundageNode node = null == current.getChildren() ? null : current.getChildren().get(nextNodeId);
		if (null == node) {
			node = new MirrorPoundageNode();
			node.setCfgPoundageNodeId(nextNodeId);
			if (null != parent)
				parent.addChild(node);
		}
		return node;
	}
	
	/**
	 * 结束时需要判断当前对象是否为没有配置的对象，如果为空配置则需要删除配置节点
	 */
	@Override
	public void finishErgodic(PoundageConfig config, MirrorPoundageNode current, MirrorPoundageNode parent) {
		if (CollectionUtil.isEmpty(current.getChildren())&& 0 == current.getCommercialRate() && 0 == current.getCompulsoryRate()
				&& 0 == current.getCommercialRetainRate() && 0 == current.getCompulsoryRetainRate())  {
			if (null != parent)
				parent.removeChild(current);
			else {
				config.getStructure().remove(current);
				if (CollectionUtil.isEmpty(config.getStructure())) 
					poundageConfigMapper.delete(config.get_id());
			}
		}
	}
}
