package org.btkj.config.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class InsuranceSQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.INSURANCE.name());
				VALUES("name", "#name");
				VALUES("created", "#created");
				VALUES("updated", "#updated");
			}
		}.toString();
	}

	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.INSURANCE.name());
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.INSURANCE.name());
				WHERE("id=#{key}");
			}
		}.toString();
	}
}
