package org.btkj.statistics.mybatis.provider;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.entity.statistics.LogScore;
import org.btkj.pojo.param.statistics.StatisticScoreParam;
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
	
	public String total(StatisticScoreParam param) { 
		String sql = _statisticSql(param, true);
		switch (param.getTimeType()) {
		case HOUR:
		case MINUTES:
		case SECOND:
		case MILLISECOND:
		case NANOSECOND:
			return sql;
		default:
			return new StringBuilder("SELECT COUNT(*) FROM(").append(sql).append(") t").toString();
		}
	}
	
	public String scores(StatisticScoreParam param) { 
		String sql = _statisticSql(param, false);
		return new StringBuilder(sql).append(" LIMIT ").append(param.getStart()).append(",").append(param.getPageSize()).toString();	
	}
	
	private String _statisticSql(StatisticScoreParam param, boolean count) {
		return new SQL() {
			{
				switch (param.getTimeType()) {
				case YEAR:
					SELECT("`year`, SUM(`quota`) quota");
					GROUP_BY("`year`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC" : "`year` DESC");
					break;
				case MONTH:
					SELECT("`year`, `month`, SUM(`quota`) quota");
					GROUP_BY("`year`, `month`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `month` ASC" : "`year` DESC, `month` DESC");
					break;
				case DAY:
					SELECT("`year`, `month`, `day`, SUM(`quota`) quota");
					GROUP_BY("`year`, `month`, `day`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `month` ASC, `day` ASC" : "`year` DESC, `month` DESC, `day` DESC");
					break;
				case WEEK:
					SELECT("`year`, `month`, `week`, SUM(`quota`) quota");
					GROUP_BY("`year`, `month`, `week`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `month` ASC, `week` ASC" : "`year` DESC, `month` DESC, `week` DESC");
					break;
				case SEASON:
					SELECT("`year`, `season`, SUM(`quota`) quota");
					GROUP_BY("`year`, `season`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `season` ASC" : "`year` DESC, `season` DESC");
					break;
				default:
					if (count)
						SELECT("COUNT(*)");
					else {
						SELECT("*");
						ORDER_BY(param.isAsc() ? "`created` ASC" : "`created` DESC");
					}
					break;
				}
				FROM(table);
				WHERE(param.conditions());
			}
		}.toString();
	}
}
