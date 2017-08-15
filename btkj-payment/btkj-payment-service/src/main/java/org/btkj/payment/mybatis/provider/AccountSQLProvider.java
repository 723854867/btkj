package org.btkj.payment.mybatis.provider;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class AccountSQLProvider extends SQLProvider {
	
	public AccountSQLProvider() {
		super("account", "employee_id", false);
	}
	
	public String getByKeyForUpdate() {
		return new SQL() {
			{
				SELECT("*");
				FROM(table);
				WHERE("employee_id=#{key} FOR UPDATE");
			}
		}.toString();
	}
	
	public String getByKeysForUpdate(Map<String, Object> params) {
		Collection<?> keys = (Collection<?>) params.get(COLLECTION);
		StringBuilder sql = new StringBuilder("SELECT * FROM ");
		sql.append(table).append("  WHERE ").append(keyCol).append(" IN(");
		for (Object key : keys)
			sql.append(key).append(",");
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") FOR UPDATE");
		return sql.toString();
	}
}
