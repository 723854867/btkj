package org.btkj.web.util;

import java.util.List;

import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.VehicleCoefficientCategory;

public class VehicleCoefficientCache {

	/**
	 * 获取商户的车险可配置系数
	 * 
	 * @param tenant
	 * @return
	 */
	public List<VehicleCoefficient> coefficients(VehicleCoefficientCategory category, Tenant tenant) {
		switch (category) {
		case LICENSE:
			break;
		default:
			break;
		}
		return null;
	}
}
