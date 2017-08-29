package org.btkj.vehicle.realm.poundage;

import javax.annotation.Resource;

import org.btkj.vehicle.mongo.PoundageConfigMapper;
import org.btkj.vehicle.pojo.entity.PoundageConfig;
import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorPoundageNode;
import org.btkj.vehicle.pojo.model.PoundageErogidicMessage;
import org.btkj.vehicle.pojo.model.PoundageStructure;
import org.btkj.vehicle.redis.PoundageCoefficientRangeMapper;
import org.rapid.util.common.message.Result;

public abstract class ErgodicCallback<MESSAGE extends PoundageErogidicMessage> {
	
	@Resource
	protected PoundageConfigMapper poundageConfigMapper;
	@Resource
	protected PoundageCoefficientRangeMapper poundageCoefficientRangeMapper;
	
	public abstract Result<?> execute(MESSAGE message, PoundageStructure current, MirrorPoundageNode node);
	
	public PoundageConfig getPoundageConfig(MESSAGE message, int cfgNodeId) {
		return poundageConfigMapper.getByKey(message.configKey());
	}
	
	public MirrorPoundageNode nextMirrorPoundageNode(int nextNodeId, MirrorPoundageNode current, MirrorPoundageNode parent) {
		return null == current.getChildren() ? null : current.getChildren().get(nextNodeId);
	}
	
	public void finishErgodic(PoundageConfig config, MirrorPoundageNode current, MirrorPoundageNode parent) {}
}
