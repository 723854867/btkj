package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class VehicleModelSQLProvider {
	
	private static final String TABLE			= "vehicle_model";

	public String getByDeptId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("dept_id=#{deptId}");
			}
		}.toString();
	}
}
