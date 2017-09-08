package org.btkj.vehicle.realm.poundage;

import org.btkj.pojo.entity.vehicle.CoefficientNode;
import org.btkj.pojo.model.CoefficientDocument;
import org.rapid.util.math.tree.PBTreeFactory;
import org.springframework.stereotype.Component;

@Component("coefficientDocumentFactory")
public class CoefficientDocumentFactory extends PBTreeFactory<Integer, CoefficientNode, CoefficientDocument> {

	@Override
	public CoefficientDocument instance(CoefficientNode node) {
		return new CoefficientDocument(node);
	}
}
