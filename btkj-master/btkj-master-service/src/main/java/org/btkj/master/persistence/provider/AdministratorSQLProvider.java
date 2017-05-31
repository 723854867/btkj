package org.btkj.master.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class AdministratorSQLProvider {

	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.ADMINISTRATOR.name());
				WHERE("id=#{id}");
			}
		}.toString();
	}
}
