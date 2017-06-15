package org.btkj.user.mybatis.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class UserSQLProvider {
	
	private static final String TABLE			= "user";
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
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
				UPDATE(TABLE);
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

	public String getByMobile() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("app_id=#{appId}");
				AND();
				WHERE("mobile=#{mobile}");
			}
		}.toString();
	}
	
	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("uid=#{key}");
			}
		}.toString();
	}
	
	public String getWithinKey(Map<String, List<Integer>> params) {
		List<Integer> keys = params.get("list");
		StringBuilder builder = new StringBuilder("select * from user where uid in(");
		for (int key : keys)
			builder.append(key).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
}
