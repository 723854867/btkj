package org.btkj.vehicle.pojo.model;

import java.io.Serializable;
import java.util.List;

import org.btkj.pojo.model.BonusRouteBody;

public class VehicleCoefficients implements Serializable {

	private static final long serialVersionUID = 65340428160768834L;

	private int commercialRate;
	private int compulsoryRate;
	private int commercialRetainRate;
	private int compulsoryRetainRate;
	private List<VehicleCoefficientType> coefficientTypes;
	
	public VehicleCoefficients(BonusRouteBody body, List<VehicleCoefficientType> coefficientTypes) {
		this.coefficientTypes = coefficientTypes;
		if (null != body) {
			this.commercialRate = body.getCommercialRate();
			this.compulsoryRate = body.getCompulsoryRate();
			this.commercialRetainRate = body.getCommercialRetainRate();
			this.compulsoryRetainRate = body.getCompulsoryRetainRate();
		}
	}
	
	public int getCommercialRate() {
		return commercialRate;
	}

	public void setCommercialRate(int commercialRate) {
		this.commercialRate = commercialRate;
	}

	public int getCompulsoryRate() {
		return compulsoryRate;
	}

	public void setCompulsoryRate(int compulsoryRate) {
		this.compulsoryRate = compulsoryRate;
	}

	public int getCommercialRetainRate() {
		return commercialRetainRate;
	}

	public void setCommercialRetainRate(int commercialRetainRate) {
		this.commercialRetainRate = commercialRetainRate;
	}

	public int getCompulsoryRetainRate() {
		return compulsoryRetainRate;
	}

	public void setCompulsoryRetainRate(int compulsoryRetainRate) {
		this.compulsoryRetainRate = compulsoryRetainRate;
	}

	public List<VehicleCoefficientType> getCoefficientTypes() {
		return coefficientTypes;
	}

	public void setCoefficientTypes(List<VehicleCoefficientType> coefficientTypes) {
		this.coefficientTypes = coefficientTypes;
	}
}
