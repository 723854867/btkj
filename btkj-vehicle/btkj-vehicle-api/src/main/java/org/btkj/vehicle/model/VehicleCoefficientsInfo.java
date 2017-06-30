package org.btkj.vehicle.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.CoefficientType;

public class VehicleCoefficientsInfo implements Serializable {

	private static final long serialVersionUID = -1558296883423518248L;

	private int typeId;
	private String typeName;
	private boolean custom;
	private int maxCustomNum;
	private List<VehicleCoefficientInfo> coefficients;
	
	public VehicleCoefficientsInfo() {}
	
	public VehicleCoefficientsInfo(CoefficientType type) {
		this.typeId = type.mark();
		this.typeName = type.title();
		switch (type) {
		case AGE:
		case VEHICLE_AGE:
		case PURCHASE_PRICE:
			this.custom = true;
			this.maxCustomNum = 4;
			break;
		default:
			this.custom = false;
			break;
		}
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean isCustom() {
		return custom;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
	}

	public int getMaxCustomNum() {
		return maxCustomNum;
	}

	public void setMaxCustomNum(int maxCustomNum) {
		this.maxCustomNum = maxCustomNum;
	}

	public List<VehicleCoefficientInfo> getCoefficients() {
		return coefficients;
	}

	public void setCoefficients(List<VehicleCoefficientInfo> coefficients) {
		this.coefficients = coefficients;
	}

	public void addCoefficient(VehicleCoefficient coefficient, Integer rate) { 
		if (null == this.coefficients)
			this.coefficients = new ArrayList<VehicleCoefficientInfo>();
		this.coefficients.add(new VehicleCoefficientInfo(coefficient, rate));
	}
	
	private class VehicleCoefficientInfo implements Serializable {

		private static final long serialVersionUID = -8403765800605961321L;

		private int id;
		private String name;
		private int comparison;
		private String comparableValue;
		private Integer rate;
		
		public VehicleCoefficientInfo() {}
		
		public VehicleCoefficientInfo(VehicleCoefficient coefficient, Integer rate) {
			this.rate = rate;
			this.id = coefficient.getId();
			this.name = coefficient.getName();
			this.comparison = coefficient.getComparison();
			this.comparableValue = coefficient.getComparableValue();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getComparison() {
			return comparison;
		}

		public void setComparison(int comparison) {
			this.comparison = comparison;
		}

		public String getComparableValue() {
			return comparableValue;
		}

		public void setComparableValue(String comparableValue) {
			this.comparableValue = comparableValue;
		}

		public Integer getRate() {
			return rate;
		}

		public void setRate(Integer rate) {
			this.rate = rate;
		}
	}
}
