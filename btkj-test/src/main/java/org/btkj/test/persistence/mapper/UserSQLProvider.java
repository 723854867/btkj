package org.btkj.test.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;

public class UserSQLProvider {

	public String selectForUpdate() {
		return "select * from user where app_id=#{appId} for update";
	}
	
	public String selectByAppId() {
		return "select * from user where app_id=#{appId}";
	}
	
	public String selectByUid() {
		return "select * from user where uid=#{uid}";
	}
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO("user");
				VALUES("app_id", "#{appId}");
				VALUES("mobile", "#{mobile}");
				VALUES("identity", "#{identity}");
				VALUES("name", "#{name}");
				VALUES("last_login_time", "#{lastLoginTime}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
}
