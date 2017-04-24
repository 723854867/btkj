package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class UserSQLProvider {
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.USER.name());
				VALUES("app_id", "#{appId}");
				VALUES("mobile", "#{mobile}");
				VALUES("identity", "#{identity}");
				VALUES("name", "#{name}");
				VALUES("pwd", "#{pwd}");
				VALUES("app_login_time", "#{appLoginTime}");
				VALUES("pc_login_time", "#{pcLoginTime}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String update() { 
		return new SQL() {
			{
				UPDATE(BtkjTables.USER.name());
				SET("name=#{name}");
				SET("pwd=#{pwd}");
				SET("identity=#{identity}");
				SET("app_login_time=#{appLoginTime}");
				SET("pc_login_time=#{pcLoginTime}");
				SET("updated=#{updated}");
				WHERE("uid=#{uid}");
			}
		}.toString();
	}

	public String selectByMobile() {
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
