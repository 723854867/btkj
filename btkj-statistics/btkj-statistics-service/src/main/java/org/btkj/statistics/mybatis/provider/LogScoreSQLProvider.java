package org.btkj.statistics.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class LogScoreSQLProvider {
	
	private static final String TABLE			= "log_score";
	
	public String scoreReward() {
		return new SQL() {
			{
				SELECT("SUM(quota) quota, `type`");
				FROM(TABLE);
				WHERE("employee_id=#{employeeId}");
				AND();
				WHERE("created BETWEEN #{begin} AND #{end}");
				GROUP_BY("`type`");
			}
		}.toString();
	}
}
