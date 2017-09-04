package org.btkj.vehicle.realm.poundage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.vehicle.pojo.entity.PoundageConfig.NodeConfig;
import org.btkj.vehicle.pojo.enums.PoundageType;
import org.btkj.vehicle.pojo.entity.PoundageNode;
import org.btkj.vehicle.pojo.model.PoundageDocument;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.math.tree.PBTreeFactory;
import org.springframework.stereotype.Component;

@Component("poundageDocumentFactory")
public class PoundageDocumentFactory extends PBTreeFactory<Integer, PoundageNode, PoundageDocument> {
	
	@Override
	public PoundageDocument instance(PoundageNode node) {
		return new PoundageDocument(node);
	}
	
	public void caculatePoundage(Map<Integer, PoundageDocument> documents, VehicleOrder order) {
		Map<String, RecursionInfo> recursions = new HashMap<String, RecursionInfo>();
	}
	
	private void _recursion(Map<String, RecursionInfo> recursions, Map<Integer, PoundageDocument> documents, VehicleOrder order) {
		if (CollectionUtil.isEmpty(documents))
			return;
		for (Entry<Integer, PoundageDocument> entry : documents.entrySet()) {
			PoundageNode node = entry.getValue().node();
			PoundageType type = node.getType();
		}
	}
	
	private class RecursionInfo {
		private NodeConfig config;
		private LinkedList<PoundageNode> nodes;
		
		public NodeConfig getConfig() {
			return config;
		}
		
		public void setConfig(NodeConfig config) {
			this.config = config;
		}
		
		public LinkedList<PoundageNode> getNodes() {
			return nodes;
		}
		
		public void setNodes(LinkedList<PoundageNode> nodes) {
			this.nodes = nodes;
		}
	}
}
