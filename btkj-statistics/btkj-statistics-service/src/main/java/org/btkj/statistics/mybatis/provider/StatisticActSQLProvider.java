package org.btkj.statistics.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.param.statistics.Report3Param;
import org.rapid.data.storage.mybatis.SQLProvider;

public class StatisticActSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "statistic_act";

	public StatisticActSQLProvider() {
		super(TABLE, "id");
	}
	
	public String report_3_total(Report3Param param) {
		String sql = _report_1_sql(param, true);
		return new StringBuilder("SELECT COUNT(*) FROM(").append(sql).append(") t").toString();
	}
	
	public String report_3(Report3Param param) {
		String sql = _report_1_sql(param, false);
		return new StringBuilder(sql).append(" LIMIT ").append(param.getStart()).append(",").append(param.getPageSize()).toString();	
	}
	
	private String _report_1_sql(Report3Param param, boolean count) {
		return new SQL() {
			{
				
			}
		}.toString();
	}
}
