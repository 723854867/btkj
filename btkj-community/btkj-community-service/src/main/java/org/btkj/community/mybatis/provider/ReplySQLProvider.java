package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class ReplySQLProvider {
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.REPLY.name());
				VALUES("quiz_id", "quizId");
				VALUES("uid", "uid");
				VALUES("content", "content");
				VALUES("created", "created");
			}
		}.toString();
	}

	public String total() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.REPLY.name());
				WHERE("quiz_id=#{quizId}");
			}
		}.toString();
	}
	
	public String paging() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.REPLY.name());
				WHERE("quiz_id=#{quizId} LIMIT #{start}, #{pageSize}");
			}
		}.toString();
	}
}
