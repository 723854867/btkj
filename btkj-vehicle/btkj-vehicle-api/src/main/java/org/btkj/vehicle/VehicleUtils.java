package org.btkj.vehicle;

import java.util.TimeZone;

import org.btkj.pojo.entity.VehicleOrder;
import org.rapid.util.lang.DateUtils;

public class VehicleUtils {

	/**
	 * 商业险计算车龄：商业险起保日期 - 初登日期
	 * 
	 * @param order
	 * @return
	 */
	public static final int vehicleAge(VehicleOrder order) {
		String commericialStart = order.getTips().getSchema().getCommercialStart();
		String enrollDate = order.getTips().getEnrollDate();
		long timestamp = DateUtils.getTimeGap(commericialStart, enrollDate, DateUtils.YYYY_MM_DD_HH_MM_SS, TimeZone.getDefault());
		return timestamp <= 0 ? 0 : (int) (timestamp / (365 * 24 * 3600 * 1000));
	}
	
	/**
	 * 是否是新车，新车没有车牌
	 * 
	 * @return
	 */
	public static final boolean isNewVehicleLicense(String license) {
		return false;
	}
}
