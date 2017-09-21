package org.btkj.statistics.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.param.statistics.StatisticPoliciesParam;
import org.rapid.data.storage.mybatis.SQLProvider;

public class StatisticPolicySQLProvider extends SQLProvider {
	
	private static final String TABLE			= "statistic_policy";

	public StatisticPolicySQLProvider() {
		super(TABLE, "id", false);
	}
	
	public String statisticPoliciesTotal(StatisticPoliciesParam param) {
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
	
	public String statisticPolicies(StatisticPoliciesParam param) {
		String sql = _statisticSql(param, false);
		return new StringBuilder(sql).append(" LIMIT ").append(param.getStart()).append(",").append(param.getPageSize()).toString();	
	}
	
	private String _statisticSql(StatisticPoliciesParam param, boolean count) { 
		return new SQL() {
			{
				String[] groups = param.groups();
				if (groups.length > 0) {
					SELECT(groups);
					GROUP_BY(groups);
				}
				switch (param.getTimeType()) {
				case YEAR:
					SELECT("`year`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "COUNT(*) total");
					GROUP_BY("`year`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC" : "`year` DESC");
					break;
				case MONTH:
					SELECT("`year`", "`month`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "COUNT(*) total");
					GROUP_BY("`year`, `month`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `month` ASC" : "`year` DESC, `month` DESC");
					break;
				case DAY:
					SELECT("`year`", "`month`", "`day`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "COUNT(*) total");
					GROUP_BY("`year`, `month`, `day`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `month` ASC, `day` ASC" : "`year` DESC, `month` DESC, `day` DESC");
					break;
				case WEEK:
					SELECT("`year`", "`month`", "`week`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "COUNT(*) total");
					GROUP_BY("`year`, `month`, `week`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `month` ASC, `week` ASC" : "`year` DESC, `month` DESC, `week` DESC");
					break;
				case SEASON:
					SELECT("`year`", "`season`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "COUNT(*) total");
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
