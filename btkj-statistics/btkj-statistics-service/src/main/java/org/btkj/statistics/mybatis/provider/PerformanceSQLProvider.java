package org.btkj.statistics.mybatis.provider;

import java.util.List;
import java.util.Map;

public class PerformanceSQLProvider {

	public String teamEmployees(Map<String, Object> params) {
		List<Integer> list = (List<Integer>) params.get("list");
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT SUM(amount) amount, employee_id, uid FROM performance where employee_id IN (");
		for (int employeeId : list)
			builder.append(employeeId).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(") AND (category & #{categoryMod}) = category "
				+ "AND created BETWEEN #{begin} AND #{end} "
				+ "GROUP BY uid, employee_id ORDER BY amount DESC");
		return builder.toString();
	}
	
	public String total(Map<String, Object> params) {
		List<Integer> list = (List<Integer>) params.get("list");
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT IFNULL(SUM(amount), 0) FROM performance where employee_id IN (");
		for (int employeeId : list)
			builder.append(employeeId).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(") AND (category & #{categoryMod}) = category "
				+ "AND created BETWEEN #{begin} AND #{end}");
		return builder.toString();
	}
}
