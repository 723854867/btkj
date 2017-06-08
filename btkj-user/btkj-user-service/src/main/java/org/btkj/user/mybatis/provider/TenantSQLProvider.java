package org.btkj.user.mybatis.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class TenantSQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.TENANT.name());
				VALUES("`name`", "#{name}");
				VALUES("app_id", "#{appId}");
				VALUES("region", "#{region}");
				VALUES("`team_depth`", "#{teamDepth}");
				VALUES("`created`", "#{created}");
				VALUES("`updated`", "#{updated}");
			}
		}.toString();
	}

	public String update() {
		return new SQL() {
			{
				UPDATE(BtkjTables.TENANT.name());
				SET("`name`=#{name}");
				SET("updated=#{updated}");
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
	
	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.TENANT.name());
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.TENANT.name());
				WHERE("tid=#{key}");
			}
		}.toString();
	}
	
	public String selectWithinKey(Map<String, List<Integer>> params) {
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
				FROM(BtkjTables.TENANT.name());
				WHERE("app_id=#{appId}");
			}
		}.toString();
	}
	
	public String countByAppIdForUpdate() {
		return "select count(*) from tenant where app_id=#{appId} for update";
	}
}
