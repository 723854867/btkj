package org.btkj.vehicle.realm.poundage;

import org.btkj.vehicle.pojo.entity.CoefficientNode;
import org.btkj.vehicle.pojo.model.CoefficientDocument;
import org.rapid.util.math.tree.PBTreeFactory;
import org.springframework.stereotype.Component;

@Component("coefficientDocumentFactory")
public class CoefficientDocumentFactory extends PBTreeFactory<Integer, CoefficientNode, CoefficientDocument> {

	@Override
	public CoefficientDocument instance(CoefficientNode node) {
		return new CoefficientDocument(node);
	}
}
