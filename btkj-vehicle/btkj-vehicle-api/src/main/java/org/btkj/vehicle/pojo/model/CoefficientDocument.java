package org.btkj.vehicle.pojo.model;

import java.util.Map;

import org.btkj.vehicle.pojo.entity.CoefficientNode;
import org.btkj.vehicle.pojo.entity.CoefficientRange;
import org.rapid.util.math.tree.Document;

import com.google.gson.annotations.Expose;

public class CoefficientDocument extends Document<Integer, CoefficientNode, CoefficientDocument> {

	private static final long serialVersionUID = 7778889134107522023L;
	
	@Expose
	private Map<Integer, CoefficientRange> ranges;

	public CoefficientDocument(CoefficientNode node) {
		super(node);
	}
	
	public Map<Integer, CoefficientRange> getRanges() {
		return ranges;
	}
	
	public void setRanges(Map<Integer, CoefficientRange> ranges) {
		this.ranges = ranges;
	}
}
