package org.btkj.statistics.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.param.statistics.Report3Param;
import org.rapid.data.storage.mybatis.SQLProvider;

public class VehicleStatisticActSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "vehicle_statistic_act";

	public VehicleStatisticActSQLProvider() {
		super(TABLE, "id");
	}
	
	public String report_3_total(Report3Param param) {
		String sql = _report_3_sql(param, true);
		return new StringBuilder("SELECT COUNT(*) FROM(").append(sql).append(") t").toString();
	}
	
	public String report_3(Report3Param param) {
		String sql = _report_3_sql(param, false);
		return new StringBuilder(sql).append(" LIMIT ").append(param.getStart()).append(",").append(param.getPageSize()).toString();	
	}
	
	private String _report_3_sql(Report3Param param, boolean count) {
		return new SQL() {
			{
				switch (param.getTimeType()) {
				case YEAR:
					SELECT("`year`", "COUNT(*) total");
					GROUP_BY("`year`");
					break;
				case DAY:
					SELECT("`year`", "`month`", "`day`", "COUNT(*) total");
					GROUP_BY("`year`, `month`, `day`");
					break;
				default:
					SELECT("`year`", "`month`", "COUNT(*) total");
					GROUP_BY("`year`, `month`");
					break;
				}
				FROM(table);
				WHERE(param.conditions(true));
			}
		}.toString();
	}
}
