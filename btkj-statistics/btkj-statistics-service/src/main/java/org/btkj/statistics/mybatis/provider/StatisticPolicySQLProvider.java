package org.btkj.statistics.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.param.statistics.Report1Param;
import org.btkj.pojo.param.statistics.Report2Param;
import org.btkj.pojo.param.statistics.Report3Param;
import org.rapid.data.storage.mybatis.SQLProvider;

public class StatisticPolicySQLProvider extends SQLProvider {
	
	private static final String TABLE			= "statistic_policy";

	public StatisticPolicySQLProvider() {
		super(TABLE, "id", false);
	}
	
	public String report_1(Report1Param param) { 
		return new SQL() {
			{
				SELECT("`year`", "`month`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "COUNT(*) total, `insurer_id`");
				GROUP_BY("`year`, `month`, `insurer_id`");
				FROM(table);
				WHERE(param.conditions());
			}
		}.toString();
	}
	
	public String report_2_total(Report2Param param) {
		String sql = _report_2_sql(param, true);
		return new StringBuilder("SELECT COUNT(*) FROM(").append(sql).append(") t").toString();
	}
	
	public String report_2(Report2Param param) {
		String sql = _report_2_sql(param, false);
		return new StringBuilder(sql).append(" LIMIT ").append(param.getStart()).append(",").append(param.getPageSize()).toString();	
	}
	
	private String _report_2_sql(Report2Param param, boolean count) { 
		return new SQL() {
			{
				switch (param.getTimeType()) {
				case YEAR:
					SELECT("`year`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "COUNT(*) total, `insurer_id`");
					GROUP_BY("`year`, `insurer_id`");
					break;
				case DAY:
					SELECT("`year`", "`month`", "`day`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "COUNT(*) total, `insurer_id`");
					GROUP_BY("`year`, `month`, `day`, `insurer_id`");
					break;
				default:
					SELECT("`year`", "`month`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "COUNT(*) total, `insurer_id`");
					GROUP_BY("`year`, `month`, `insurer_id`");
					break;
				}
				FROM(table);
				WHERE(param.conditions());
			}
		}.toString();
	}
	
	public String report_3(Report3Param param) {
		return new SQL() {
			{
				switch (param.getTimeType()) {
				case YEAR:
					SELECT("`year`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "`employee_id`", "`uid`");
					GROUP_BY("`year`", "`employee_id`", "`uid`");
					break;
				case DAY:
					SELECT("`year`", "`month`", "`day`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "`employee_id`", "`uid`");
					GROUP_BY("`year`", "`month`", "`day`", "`employee_id`", "`uid`");
					break;
				default:
					SELECT("`year`", "`month`", "SUM(CAST(`premium` AS DECIMAL(10,2))) premium", "`employee_id`", "`uid`");
					GROUP_BY("`year`", "`month`", "`employee_id`", "`uid`");
					break;
				}
				FROM(table);
				WHERE(param.conditions(false));
			}
		}.toString();
	}
}
