package org.btkj.user.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class EmployeeSQLProvider {
	
	private static final String TABLE			= "employee";

	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("uid", "#{uid}");
				VALUES("tid", "#{tid}");
				VALUES("name", "#{name}");
				VALUES("mobile", "#{mobile}");
				VALUES("identity", "#{identity}");
				VALUES("identity_face", "#{identityFace}");
				VALUES("identity_back", "#{identityBack}");
				VALUES("parent_id", "#{parentId}");
				VALUES("`left`", "#{left}");
				VALUES("`right`", "#{right}");
				VALUES("`level`", "#{level}");
				VALUES("`pay_type`", "#{payType}");
				VALUES("`tag_mod`", "#{tagMod}");
				VALUES("`state`", "#{state}");
				VALUES("`score`", "#{score}");
				VALUES("`scale_bonus`", "#{scaleBonus}");
				VALUES("`manage_bonus`", "#{manageBonus}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("id=#{key}");
			}
		}.toString();
	}
	
	public String update() {
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("`pay_type`=#{payType}");
				SET("`state`=#{state}");
				SET("`tag_mod`=#{tagMod}");
				SET("`score`=#{score}");
				SET("`scale_bonus`=#{scaleBonus}");
				SET("`manage_bonus`=#{manageBonus}");
				SET("updated=#{updated}");
				WHERE("id=#{id}");
			}
		}.toString();
	}
	
	public String getByTidAndUid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{tid}");
				AND();
				WHERE("uid=#{uid}");
			}
		}.toString();
	}
	
	public String getAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
			}
		}.toString();
	}
	
	public String getByUid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("uid=#{uid}");
			}
		}.toString();
	}
	
	public String team() {
		return "SELECT * FROM employee WHERE (parent_id=#{id} AND `left`>#{left} AND `right`<#{right} AND `level`<=#{level}) OR id=#{id}";
	}
	
	public String getByTidForUpdate() {
		return "select * from employee where `tid`=#{tid} for update";
	}
	
	public String updateForJoin() {
		return "update employee set `left`=case when `left`>#{value} then `left`+2 else `left` end, `right`=case when `right`>=#{value} then `right`+2 else `right` end where tid=#{tid}";
	}
}