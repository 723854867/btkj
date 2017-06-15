package org.btkj.user.mybatis.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class TenantSQLProvider {
	
	private static final String TABLE			= "tenant";
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`name`", "#{name}");
				VALUES("app_id", "#{appId}");
				VALUES("region", "#{region}");
				VALUES("`team_depth`", "#{teamDepth}");
				VALUES("`jian_jie_id`", "#{jianJieId}");
				VALUES("`created`", "#{created}");
				VALUES("`updated`", "#{updated}");
			}
		}.toString();
	}

	public String update() {
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("`name`=#{name}");
				SET("`team_depth`=#{teamDepth}");
				SET("updated=#{updated}");
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
	
	public String getAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
			}
		}.toString();
	}
	
	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{key}");
			}
		}.toString();
	}
	
	public String getWithinKey(Map<String, List<Integer>> params) {
		List<Integer> keys = params.get("list");
		StringBuilder builder = new StringBuilder("select * from tenant where tid in(");
		for (int key : keys)
			builder.append(key).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
	
	public String countByAppId() {
		return new SQL() {
			{
				SELECT("COUNT(*)");
				FROM(TABLE);
				WHERE("app_id=#{appId}");
			}
		}.toString();
	}
	
	public String countByAppIdForUpdate() {
		return "select count(*) from tenant where app_id=#{appId} for update";
	}
}
