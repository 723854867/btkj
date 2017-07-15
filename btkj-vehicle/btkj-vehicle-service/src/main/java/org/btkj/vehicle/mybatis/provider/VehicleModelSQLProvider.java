package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class VehicleModelSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "vehicle_model";
	
	public VehicleModelSQLProvider() {
		super(TABLE, "id");
	}

	public String getByDeptId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`dept_id`=#{deptId}");
			}
		}.toString();
	}
}
