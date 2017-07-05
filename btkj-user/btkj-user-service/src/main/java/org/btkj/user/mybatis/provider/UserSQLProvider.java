package org.btkj.user.mybatis.provider;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.user.pojo.submit.UserSearcher;
import org.rapid.util.common.enums.SORT_TYPE;

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
	
	public String count(UserSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM user");
		Map<String, String> params = searcher.params();
		if (null != params && !params.isEmpty()) {
			builder.append(" WHERE ");
			boolean first = true;
			for (Entry<String, String> entry : params.entrySet()) {
				if (!first)
					builder.append(" AND ");
				first = false;
				builder.append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		return builder.toString();
	}
	
	public String paging(UserSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM user");
		Map<String, String> params = searcher.params();
		if (null != params && !params.isEmpty()) {
			builder.append(" WHERE ");
			boolean first = true;
			for (Entry<String, String> entry : params.entrySet()) {
				if (!first)
					builder.append(" AND ");
				first = false;
				builder.append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		if (null != searcher.getSortCol()) {
			builder.append(" ORDER BY ").append(searcher.getSortCol()).append(" ");
			builder.append(searcher.isAsc() ? SORT_TYPE.ASC.name() : SORT_TYPE.DESC.name());
		}
		builder.append(" LIMIT ").append(searcher.getStart()).append(",").append(searcher.getPageSize());
		return builder.toString();
	}
}
