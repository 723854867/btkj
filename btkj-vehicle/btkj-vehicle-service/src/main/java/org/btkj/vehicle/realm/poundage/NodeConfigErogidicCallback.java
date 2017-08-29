package org.btkj.vehicle.realm.poundage;

import java.util.Map;
import java.util.Map.Entry;

import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorCoefficient;
import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorPoundageNode;
import org.btkj.vehicle.pojo.model.PoundageNodeConfigInfo;
import org.btkj.vehicle.pojo.model.PoundageStructure;
import org.btkj.vehicle.pojo.param.PoundageErogidicParam;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.springframework.stereotype.Component;

@Component("nodeConfigErogidicCallback")
public class NodeConfigErogidicCallback extends ErgodicCallback<PoundageErogidicParam> {

	@Override
	public Result<PoundageNodeConfigInfo> execute(PoundageErogidicParam message, PoundageStructure current, MirrorPoundageNode node) {
		MirrorCoefficient coefficient = _searchMirrorCoefficient(node.getCoefficients(), message.getCfgCoefficientId());
		return Result.result(new PoundageNodeConfigInfo(node, coefficient));
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
}
