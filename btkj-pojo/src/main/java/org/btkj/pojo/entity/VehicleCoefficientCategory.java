package org.btkj.pojo.entity;

import java.io.Serializable;
import java.util.List;

import org.btkj.pojo.comparable.Comparable;
import org.rapid.util.common.model.UniqueModel;

public class VehicleCoefficientCategory implements UniqueModel<Long> {

	private static final long serialVersionUID = -8789155873652730748L;

	private long _id;
	private String name;
	private boolean customAllowed;
	private int maxSubcategories;
	private List<DefaultVehicleCoefficient> defaultCoefficients;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCustomAllowed() {
		return customAllowed;
	}

	public void setCustomAllowed(boolean customAllowed) {
		this.customAllowed = customAllowed;
	}

	public int getMaxSubcategories() {
		return maxSubcategories;
	}

	public void setMaxSubcategories(int maxSubcategories) {
		this.maxSubcategories = maxSubcategories;
	}

	public List<DefaultVehicleCoefficient> getDefaultCoefficients() {
		return defaultCoefficients;
	}

	public void setDefaultCoefficients(List<DefaultVehicleCoefficient> defaultCoefficients) {
		this.defaultCoefficients = defaultCoefficients;
	}

	@Override
	public Long key() {
		return this._id;
	}

	public class DefaultVehicleCoefficient implements Serializable {
		private static final long serialVersionUID = -6478031277259753349L;
		private int id;
		private String name;
		private Comparable comparable;
		private String comparableValue;
		private int created;
		private int updated;

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

		public Comparable getComparable() {
			return comparable;
		}

		public void setComparable(Comparable comparable) {
			this.comparable = comparable;
		}

		public String getComparableValue() {
			return comparableValue;
		}

		public void setComparableValue(String comparableValue) {
			this.comparableValue = comparableValue;
		}

		public int getCreated() {
			return created;
		}

		public void setCreated(int created) {
			this.created = created;
		}

		public int getUpdated() {
			return updated;
		}

		public void setUpdated(int updated) {
			this.updated = updated;
		}
	}
}
