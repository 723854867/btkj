package org.btkj.payment.mybatis.provider;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.payment.pojo.entity.Account;
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
	
	public String replace(Map<Integer, Account> accounts) { 
		StringBuilder builder = new StringBuilder("REPLACE INTO `")
				.append(table).append("`(`employee_id`, `score_available`, `score_frozen`, `score_consume`,"
						+ "`score_personal`, `score_manage`, `score_scale`, `created`, `updated`) VALUES");
		for (Account account : accounts.values()) {
			builder.append("(").append(account.getEmployeeId()).append(",").append(account.getScoreAvailable()).append(",")
				.append(account.getScoreFrozen()).append(",").append(account.getScoreConsume()).append(",")
				.append(account.getScorePersonal()).append(",").append(account.getScoreManage()).append(",")
				.append(account.getScoreScale()).append(",").append(account.getCreated()).append(",")
				.append(account.getUpdated()).append("),");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
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
