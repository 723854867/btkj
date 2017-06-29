package org.btkj.manager.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.enums.vehicle.CoefficientType;

public class VehicleCoefficientCategories implements Serializable {

	private static final long serialVersionUID = 558599430290568817L;
	
	private List<TypeInfo> types;
	
	public List<TypeInfo> getTypes() {
		return types;
	}
	
	public void setTypes(List<TypeInfo> types) {
		this.types = types;
	}
	
	public void addCoefficientInfo(CoefficientType type) {
		if (null == types)
			this.types = new ArrayList<TypeInfo>();
		this.types.add(new TypeInfo(type));
	}
	
	private class TypeInfo implements Serializable {

		private static final long serialVersionUID = -7316167463972229325L;
		
		private int id;
		private String name;
		private boolean custom;
		private int maxCustomNum = 4;
		
		public TypeInfo(CoefficientType type) {
			this.id = type.mark();
			this.name = type.title();
			switch (type) {
			case AGE:
			case VEHICLE_AGE:
			case PURCHASE_PRICE:
				custom = true;
				break;
			default:
				custom = false;
				break;
			}
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
	}
}
