package org.btkj.vehicle.pojo.model;

import java.util.Map;

import org.btkj.vehicle.pojo.entity.PoundageNode;
import org.rapid.util.math.tree.Document;

import com.google.gson.annotations.Expose;

public class PoundageDocument extends Document<Integer, PoundageNode, PoundageDocument> {

	private static final long serialVersionUID = -5528987656405688953L;
	
	@Expose
	private Map<Integer, CoefficientDocument> coefficients;

	public PoundageDocument(PoundageNode node) {
		super(node);
	}
	
	public Map<Integer, CoefficientDocument> getCoefficients() {
		return coefficients;
	}
	
	public void setCoefficients(Map<Integer, CoefficientDocument> coefficients) {
		this.coefficients = coefficients;
	}
}
