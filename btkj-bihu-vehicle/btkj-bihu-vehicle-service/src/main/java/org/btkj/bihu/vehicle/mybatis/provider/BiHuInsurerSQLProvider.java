package org.btkj.bihu.vehicle.mybatis.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class BiHuInsurerSQLProvider {
	
	private static final String TABLE			= "bi_hu_insurer";

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("id=#{key}");
			}
		}.toString();
	}
	
	public String getByCode() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("code=#{code}");
			}
		}.toString();
	}
	
	public String getWithinKey(Map<String, List<Integer>> params) {
		List<Integer> keys = params.get("list");
		StringBuilder builder = new StringBuilder("select * from bi_hu_insurer where id in(");
		for (int key : keys)
			builder.append(key).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
}
