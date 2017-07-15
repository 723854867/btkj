package org.btkj.user.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class BannerSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "banner";
	
	public BannerSQLProvider() {
		super(TABLE, "id");
	}
	
	public String getByAppIdAndTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`tid`=#{tid}");
				AND();
				WHERE("`app_id`=#{appId}");
			}
		}.toString();
	}
}
