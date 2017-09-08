package org.btkj.statistics.mybatis.provider;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.entity.statistics.LogScore;
import org.rapid.data.storage.mybatis.SQLProvider;

public class LogScoreSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "log_score";
	
	public LogScoreSQLProvider() {
		super(TABLE, "id");
	}
	
	public String batchInsert(Map<String, Object> params) {
		Collection<Object> list = (Collection<Object>) params.get(COLLECTION);
		StringBuilder builder = new StringBuilder("INSERT INTO `").append(table).append("`(`employee_id`, `uid`, `tid`, `app_id`, ")
				.append("`type`, `biz_id`, `detail_type`, `quota`, `income`, `year`, `month`, `day`, `week`, `season`, `created`) VALUES");
		for (Object temp : list) {
			LogScore logScore = (LogScore) temp;
			builder.append("(").append(logScore.getEmployeeId()).append(",").append(logScore.getUid()).append(",").append(logScore.getTid())
			.append(",").append(logScore.getAppId()).append(",").append(logScore.getType()).append(",'").append(logScore.getBizId()).append("',")
			.append(logScore.getDetailType()).append(",").append(logScore.getQuota()).append(",").append(logScore.isIncome()).append(",")
			.append(logScore.getYear()).append(",").append(logScore.getMonth()).append(",").append(logScore.getDay()).append(",")
			.append(logScore.getWeek()).append(",").append(logScore.getSeason()).append(",").append(logScore.getCreated()).append("),");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
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
