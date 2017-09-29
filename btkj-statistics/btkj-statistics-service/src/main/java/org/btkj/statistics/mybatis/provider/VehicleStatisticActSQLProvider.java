package org.btkj.statistics.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.param.statistics.Report3Param;
import org.rapid.data.storage.mybatis.SQLProvider;

public class VehicleStatisticActSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "vehicle_statistic_act";

	public VehicleStatisticActSQLProvider() {
		super(TABLE, "id");
	}
	
	@Override
	public String insert(Object entity) {
		return new SQL() {
			{
				INSERT_INTO(table);
				VALUES("app_id", "#{appId}");
				VALUES("uid", "#{uid}");
				VALUES("tid", "#{tid}");
				VALUES("employee_id", "#{employeeId}");
				VALUES("type", "#{type}");
				VALUES("year", "#{year}");
				VALUES("month", "#{month}");
				VALUES("day", "#{day}");
				VALUES("week", "#{week}");
				VALUES("season", "#{season}");
				VALUES("created", "#{created}");
			}
		}.toString();
	}
	
	public String report_3(Report3Param param) {
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
	
	public String orderNum() {
		return new SQL() {
			{
				SELECT("COUNT(*)");
				FROM(table);
				WHERE("employee_id=#{employeeId}");
				AND();
				WHERE("created>=#{beginTime}");
				AND();
				WHERE("created<=#{endTime}");
				AND();
				WHERE("`type`=2");
			}
		}.toString();
	}
}
