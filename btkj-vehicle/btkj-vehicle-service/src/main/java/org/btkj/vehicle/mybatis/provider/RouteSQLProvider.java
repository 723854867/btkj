package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class RouteSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "route";
	
	public RouteSQLProvider() {
		super(TABLE, "key");
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
}
