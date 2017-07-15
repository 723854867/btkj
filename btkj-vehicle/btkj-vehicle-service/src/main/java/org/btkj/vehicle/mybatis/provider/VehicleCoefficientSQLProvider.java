package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class VehicleCoefficientSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "vehicle_coefficient";
	
	public VehicleCoefficientSQLProvider() {
		super(TABLE, "id");
	}

	public String getByTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`tid`=#{tid}");
			}
		}.toString();
	}
	
	public String getByTidAndTypeForUpdate() {
		return "SELECT * FROM vehicle_coefficient WHERE tid=#{tid} AND type=#{type} FOR UPDATE";
	}
}
