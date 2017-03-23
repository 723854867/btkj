package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class EmployeeSQLProvider {

	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.EMPLOYEE.name());
				VALUES("uid", "#{uid}");
				VALUES("tid", "#{tid}");
				VALUES("app_id", "#{appId}");
				VALUES("parent_id", "#{parentId}");
				VALUES("left", "#{left}");
				VALUES("right", "#{right}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String selectByUidAndTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.EMPLOYEE.name());
				WHERE("uid=#{uid}");
				AND();
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
	
	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.EMPLOYEE.name());
			}
		}.toString();
	}
	
	public String selectByUidAndAppId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.EMPLOYEE.name());
				WHERE("uid=#{uid}");
				AND();
				WHERE("app_id=#{appId}");
			}
		}.toString();
	}
}
