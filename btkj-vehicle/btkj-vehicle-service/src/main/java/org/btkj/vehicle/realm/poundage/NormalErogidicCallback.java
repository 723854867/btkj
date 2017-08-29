package org.btkj.vehicle.realm.poundage;

import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorPoundageNode;
import org.btkj.vehicle.pojo.model.PoundageStructure;
import org.btkj.vehicle.pojo.param.PoundageErogidicParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Component;

@Component("normalErogidicCallback")
public class NormalErogidicCallback extends ErgodicCallback<PoundageErogidicParam> {

	@Override
	public Result<Void> execute(PoundageErogidicParam message, PoundageStructure current, MirrorPoundageNode node) {
		switch (message.getType()) {
		case DELETE:
			node.setCommercialRate(0);
			node.setCompulsoryRate(0);
			node.setCoefficients(null);
			node.setCommercialRetainRate(0);
			node.setCompulsoryRetainRate(0);
			return Consts.RESULT.OK;
		case INEFFECTIVE:
			node.setCoefficientsEffective(false);
			return Consts.RESULT.OK;
		case EFFECTIVE:
			node.setCoefficientsEffective(true);
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
