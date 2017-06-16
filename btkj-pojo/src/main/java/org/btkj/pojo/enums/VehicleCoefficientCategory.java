package org.btkj.pojo.enums;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.comparable.Comparable;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.rapid.util.lang.StringUtils;

public enum VehicleCoefficientCategory {

	/**
	 * 出险系数
	 */
	CLAIM(1, "出险系数") {
		@Override
		protected Map<Integer, VehicleCoefficient> init() {
			Map<Integer, VehicleCoefficient> map = new HashMap<Integer, VehicleCoefficient>();
			map.put(-1, new VehicleCoefficient(-1, mark(), "三次及以上出险", Comparable.LARGE_EQUAL, "3"));
			map.put(-2, new VehicleCoefficient(-2, mark(), "两次出险", Comparable.EQUAL, "2"));
			map.put(-3, new VehicleCoefficient(-3, mark(), "一次出险", Comparable.EQUAL, "1"));
			return map;
		}
	},
	
	/**
	 * 无赔系数
	 */
	NO_CLAIM(2, "无赔系数") {
		@Override
		protected Map<Integer, VehicleCoefficient> init() {
			Map<Integer, VehicleCoefficient> map = new HashMap<Integer, VehicleCoefficient>();
			map.put(-1, new VehicleCoefficient(-1, mark(), "三年及以上无赔", Comparable.LARGE_EQUAL, "3"));
			map.put(-2, new VehicleCoefficient(-2, mark(), "两年无赔", Comparable.EQUAL, "2"));
			map.put(-3, new VehicleCoefficient(-3, mark(), "一年无赔", Comparable.EQUAL, "1"));
			return map;
		}
	},
	
	/**
	 * 年龄系数
	 */
	AGE(4, "年龄系数"),
	
	/**
	 * 性别系数
	 */
	GENDER(8, "性别系数") {
		@Override
		protected Map<Integer, VehicleCoefficient> init() {
			Map<Integer, VehicleCoefficient> map = new HashMap<Integer, VehicleCoefficient>();
			map.put(-1, new VehicleCoefficient(-1, mark(), "女", Comparable.EQUAL, "女"));
			map.put(-2, new VehicleCoefficient(-2, mark(), "男", Comparable.EQUAL, "男"));
			return map;
		}
	},
	
	/**
	 * 车龄系数
	 */
	VEHICLE_AGE(16, "车龄系数") {
		Map<String, Map<String, VehicleCoefficient>> map = new HashMap<String, Map<String, VehicleCoefficient>>();
		@Override
		protected Map<Integer, VehicleCoefficient> init() {
			for (String province : StringUtils.PROVINCES) {
				Map<String, VehicleCoefficient> temp = map.get(province);
				if (null == temp) {
					temp = new HashMap<String, VehicleCoefficient>();
					temp.put(province, new VehicleCoefficient(0, mark(), province, Comparable.EQUAL, province));
					temp.put("其他", new VehicleCoefficient(0, mark(), "其他", Comparable.EQUAL, "其他"));
					map.put(province, temp);
				}
				for (String letter : StringUtils.ALPHABET) {
					temp.put(letter, new VehicleCoefficient(0, mark(), province + letter, Comparable.EQUAL, province + letter));
				}
			}
			return null;
		}
		@Override
		public Collection<VehicleCoefficient> coefficients(Tenant tenant) {
			return super.coefficients(tenant);
		}
	},
	
	/**
	 * 车牌系数
	 */
	LICENSE(32, "车牌系数"),
	
	/**
	 * 转续保系数
	 */
	ZHUAN_XU_BAO(64, "转续保系数") {
		@Override
		protected Map<Integer, VehicleCoefficient> init() {
			Map<Integer, VehicleCoefficient> map = new HashMap<Integer, VehicleCoefficient>();
			map.put(-1, new VehicleCoefficient(-1, mark(), "新车", Comparable.EQUAL, "1"));
			map.put(-2, new VehicleCoefficient(-2, mark(), "转保", Comparable.EQUAL, "2"));
			map.put(-2, new VehicleCoefficient(-3, mark(), "续保", Comparable.EQUAL, "3"));
			return map;
		}
	},
	
	/**
	 * 新车购置价系数
	 */
	PRICE(128, "新车购置价系数"),
	
	/**
	 * 座位系数
	 */
	SEAT_COUNT(256, "座位系数") {
		@Override
		protected Map<Integer, VehicleCoefficient> init() {
			Map<Integer, VehicleCoefficient> map = new HashMap<Integer, VehicleCoefficient>();
			map.put(-1, new VehicleCoefficient(-1, mark(), "2座", Comparable.EQUAL, "2"));
			map.put(-2, new VehicleCoefficient(-2, mark(), "3座", Comparable.EQUAL, "3"));
			map.put(-2, new VehicleCoefficient(-3, mark(), "4座", Comparable.EQUAL, "4"));
			return map;
		}
	};
	
	private int mark;
	private String title;
	private Map<Integer, VehicleCoefficient> defaultCoefficients;
	
	private VehicleCoefficientCategory(int mark, String title) {
		this.mark = mark;
		this.title = title;
		this.defaultCoefficients = null;
	}
	
	protected Map<Integer, VehicleCoefficient> init() {
		return null;
	}
	
	public Collection<VehicleCoefficient> coefficients(Tenant tenant) {
		return null == defaultCoefficients ? null : defaultCoefficients.values();
	}
	
	public int mark() {
		return mark;
	}
	
	public String title() {
		return title;
	}
}
