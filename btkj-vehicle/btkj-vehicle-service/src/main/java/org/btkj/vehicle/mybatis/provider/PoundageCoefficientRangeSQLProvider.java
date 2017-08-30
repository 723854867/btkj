package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class PoundageCoefficientRangeSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "poundage_coefficient_range";

	public PoundageCoefficientRangeSQLProvider() {
		super("poundage_coefficient_range", "id");
	}
	
	public String getByTidAndCfgCoefficientId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{tid}");
				AND();
				WHERE("cfg_coefficient_id=#{cfgCoefficientId}");
			}
		}.toString();
	}
	
	public String getByTidAndCfgCoefficientIdForUpdate() {
		return "SELECT * FROM `poundage_coefficient_range` where `tid`=#{tid} AND `cfg_coefficient_id`=#{cfgCoefficientId} FOR UPDATE";
	}
}
