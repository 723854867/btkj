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
				VALUES("name", "#{name}");
				VALUES("identity", "#{identity}");
				VALUES("parent_id", "#{parentId}");
				VALUES("`mod`", "#{mod}");
				VALUES("`left`", "#{left}");
				VALUES("`right`", "#{right}");
				VALUES("`level`", "#{level}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.EMPLOYEE.name());
				WHERE("id=#{key}");
			}
		}.toString();
	}
	
	public String selectByTidAndUid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.EMPLOYEE.name());
				WHERE("tid=#{tid}");
				AND();
				WHERE("uid=#{uid}");
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
	
	public String selectByUid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.EMPLOYEE.name());
				WHERE("uid=#{uid}");
			}
		}.toString();
	}
	
	public String selectByTid() {
		String string= "select * from employee where `tid`=#{tid} and #{byId} and #{byName} and #{byMobile} and #{byPayType} and #{byState} order by created desc limit #{start}, #{count}";
		System.out.println(string);
		return string;
	}

	
	
	public String countByTid() {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(BtkjTables.EMPLOYEE.name());
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
	
	public String selectByTidForUpdate() {
		return "select * from employee where `tid`=#{tid} for update";
	}
	
	public String updateForJoin() {
		return "update employee set `left`=case when `left`>#{value} then `left`+2 else `left` end, `right`=case when `right`>=#{value} then `right`+2 else `right` end where tid=#{tid}";
	}
}
