package org.btkj.statistics.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.param.statistics.StatisticActsParam;
import org.rapid.data.storage.mybatis.SQLProvider;

public class StatisticActSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "statistic_act";

	public StatisticActSQLProvider() {
		super(TABLE, "id");
	}
	
	public String report_1_total(StatisticActsParam param) {
		String sql = _report_1_sql(param, true);
		return new StringBuilder("SELECT COUNT(*) FROM(").append(sql).append(") t").toString();
	}
	
	public String report_1(StatisticActsParam param) {
		String sql = _report_1_sql(param, false);
		return new StringBuilder(sql).append(" LIMIT ").append(param.getStart()).append(",").append(param.getPageSize()).toString();	
	}
	
	private String _report_1_sql(StatisticActsParam param, boolean count) {
		return new SQL() {
			{
				switch (param.getTimeType()) {
				case YEAR:
					SELECT("`year`, COUNT(*) `total`, `type`, `employee_id`, `uid`");
					GROUP_BY("`year`, `type`, `employee_id`, `uid`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC" : "`year` DESC");
					break;
				case DAY:
					SELECT("`year`, `month`, `day`, COUNT(*) `total`, `type`, `employee_id`, `uid`");
					GROUP_BY("`year`, `month`, `day`, `type`, `employee_id`, `uid`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `month` ASC, `day` ASC" : "`year` DESC, `month` DESC, `day` DESC");
					break;
				case WEEK:
					SELECT("`year`, `month`, `week`, COUNT(*) `total`, `type`, `employee_id`, `uid`");
					GROUP_BY("`year`, `month`, `week`, `type`, `employee_id`, `uid`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `month` ASC, `week` ASC" : "`year` DESC, `month` DESC, `week` DESC");
					break;
				case SEASON:
					SELECT("`year`, `season`, COUNT(*) `total`, `type`, `employee_id`, `uid`");
					GROUP_BY("`year`, `season`, `type`, `employee_id`, `uid`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `season` ASC" : "`year` DESC, `season` DESC");
					break;
				default:
					SELECT("`year`, `month`, COUNT(*) `total`, `type`, `employee_id`, `uid`");
					GROUP_BY("`year`, `month`, `type`, `employee_id`, `uid`");
					if (!count)
						ORDER_BY(param.isAsc() ? "`year` ASC, `month` ASC" : "`year` DESC, `month` DESC");
					break;
				}
				FROM(table);
				WHERE(param.conditions());
			}
		}.toString();
	}
}
