package org.btkj.pojo.model.insur.vehicle;

/**
 * 指定修理厂险
 * 
 * @author ahab
 */
public class GarageDesignatedInsurance extends Insurance {

	private static final long serialVersionUID = -8482075341927930608L;
	
	private int type;

	public GarageDesignatedInsurance() {}
	
	public GarageDesignatedInsurance(int type, double quota, double price) {
		super(quota, price);
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
}
