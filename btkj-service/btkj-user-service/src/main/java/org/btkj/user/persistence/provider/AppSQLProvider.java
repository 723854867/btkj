package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class AppSQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.APP.name());
				VALUES("`name`", "#{name}");
				VALUES("`mod`", "#{mod}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}

	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.APP.name());
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.APP.name());
				WHERE("tid=#{key}");
			}
		}.toString();
	}
}
