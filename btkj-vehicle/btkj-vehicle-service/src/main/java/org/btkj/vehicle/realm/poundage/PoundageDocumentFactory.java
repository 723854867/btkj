package org.btkj.vehicle.realm.poundage;

import java.util.Map;

import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.vehicle.pojo.entity.PoundageNode;
import org.btkj.vehicle.pojo.model.PoundageDocument;
import org.rapid.util.math.tree.PBTreeFactory;
import org.springframework.stereotype.Component;

@Component("poundageDocumentFactory")
public class PoundageDocumentFactory extends PBTreeFactory<Integer, PoundageNode, PoundageDocument> {
	
	@Override
	public PoundageDocument instance(PoundageNode node) {
		return new PoundageDocument(node);
	}
	
	public void caculatePoundage(Map<Integer, PoundageDocument> documents, VehicleOrder order) {
		
	}
}
