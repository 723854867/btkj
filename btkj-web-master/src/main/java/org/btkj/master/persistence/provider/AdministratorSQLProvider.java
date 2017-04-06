package org.btkj.master.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.master.persistence.Tables;

public class AdministratorSQLProvider {
	
	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.ADMINISTRATOR.name());
			}
		}.toString();
	}
	
	public String update() {
		return new SQL() {
			{
				UPDATE(Tables.ADMINISTRATOR.name());
				SET("id=#{id}");
				SET("pwd=#{pwd}");
				SET("name=#{name}");
				SET("created=#{created}");
				SET("updated=#{updated}");
			}
		}.toString();
	}
}
