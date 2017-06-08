package org.btkj.user.mybatis.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class UserSQLProvider {
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.USER.name());
				VALUES("app_id", "#{appId}");
				VALUES("mobile", "#{mobile}");
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
				SET("pwd=#{pwd}");
				SET("name=#{name}");
				SET("avatar=#{avatar}");
				SET("identity=#{identity}");
				SET("identity_face=#{identityFace}");
				SET("identity_back=#{identityBack}");
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
	
	public String selectWithinKey(Map<String, List<Integer>> params) {
		List<Integer> keys = params.get("list");
		StringBuilder builder = new StringBuilder("select * from user where uid in(");
		for (int key : keys)
			builder.append(key).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
}
