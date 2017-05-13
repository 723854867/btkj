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
				VALUES("mobile", "#{mobile}");
				VALUES("identity", "#{identity}");
				VALUES("parent_id", "#{parentId}");
				VALUES("`left`", "#{left}");
				VALUES("`right`", "#{right}");
				VALUES("`level`", "#{level}");
				VALUES("`pay_type`", "#{payType}");
				VALUES("`tag_mod`", "#{tagMod}");
				VALUES("`state`", "#{state}");
				VALUES("`integral`", "#{integral}");
				VALUES("`scale_commission`", "#{scaleCommission}");
				VALUES("`manage_commission`", "#{manageCommission}");
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
	
	public String updateStateOfT() {
		return new SQL() {
			{
				UPDATE(BtkjTables.EMPLOYEE.name());
				SET("`state`=0");
				WHERE("id=#{id}");
			}
		}.toString();
	}
	
	public String updateStateOfF() {
		return new SQL() {
			{
				UPDATE(BtkjTables.EMPLOYEE.name());
				SET("`state`=1");
				WHERE("id=#{id}");
			}
		}.toString();
	}
	
	public String updateInfo() {
		return new SQL() {
		   {
				UPDATE(BtkjTables.EMPLOYEE.name());
				SET("`pay_type`=#{paytype}");
				SET("`tag_mod`=#{tagMod}");
				SET("`scale_commission`=#{scaleCommission}");
				SET("`manage_commission`=#{manageCommission}");
				SET("`updated`=#{updated}");
				WHERE("id=#{id}");
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
