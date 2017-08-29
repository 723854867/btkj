package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class JianJieInsurerSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "jian_jie_insurer";

	public JianJieInsurerSQLProvider() {
		super(TABLE, "id");
	}
	
	public String getByTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
}
