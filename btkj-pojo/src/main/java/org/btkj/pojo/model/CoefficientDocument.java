package org.btkj.pojo.model;

import org.btkj.pojo.entity.vehicle.CoefficientNode;
import org.rapid.util.math.tree.Document;

public class CoefficientDocument extends Document<Integer, CoefficientNode, CoefficientDocument> {

	private static final long serialVersionUID = 7778889134107522023L;
	
	public CoefficientDocument(CoefficientNode node) {
		super(node);
	}
}