package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class TenantUserSQLProvider {

	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.USER_RELATION.name());
				VALUES("uid", "#uid");
				VALUES("tid", "#tid");
				VALUES("parent_id", "#parentId");
				VALUES("left", "#left");
				VALUES("right", "#right");
				VALUES("created", "#created");
				VALUES("updated", "#updated");
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.USER_RELATION.name());
				WHERE("tid=#first");
				AND();
				WHERE("uid=#second");
			}
		}.toString();
	}
	
	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.USER_RELATION.name());
			}
		}.toString();
	}
}
