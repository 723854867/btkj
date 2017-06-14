package org.btkj.config.mybatis.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.config.mybatis.Tables;

public class InsurerSQLProvider {
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.INSURER.name());
				WHERE("id=#{key}");
			}
		}.toString();
	}
	
	public String selectWithinKey(Map<String, List<Integer>> params) {
		List<Integer> keys = params.get("list");
		StringBuilder builder = new StringBuilder("select * from insurer where id in(");
		for (int key : keys)
			builder.append(key).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
}
