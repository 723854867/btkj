package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class UserSQLProvider {
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.USER.name());
				VALUES("app_id", "#appId");
				VALUES("mobile", "#mobile");
				VALUES("identity", "#identity");
				VALUES("name", "#name");
				VALUES("last_login_time", "#lastLoginTime");
				VALUES("created", "#created");
				VALUES("updated", "#updated");
			}
		}.toString();
	}

	public String getByAppIdAndMobile() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.USER.name());
				WHERE("app_id=#{appId}");
				AND();
				WHERE("mobile=#{mobile}");
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.USER.name());
				WHERE("uid=#{key}");
			}
		}.toString();
	}
}
