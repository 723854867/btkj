package org.btkj.config.mybatis.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class RegionSQLProvider {
	
	private static final String TABLE			= "region";
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("id", "#id");
				VALUES("name", "#name");
				VALUES("level", "#level");
				VALUES("parent_id", "#parentId");
				VALUES("short_name", "#shortName");
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
				WHERE("id=#{key}");
			}
		}.toString();
	}
	
	public String getByKeys(Map<String, List<Integer>> params) {
		List<Integer> keys = params.get("keys");
		StringBuilder builder = new StringBuilder("SELECT * FROM region WHERE id IN(");
		for (int key : keys)
			builder.append(key).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
}
