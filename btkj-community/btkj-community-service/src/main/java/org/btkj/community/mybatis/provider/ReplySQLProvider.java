package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class ReplySQLProvider extends SQLProvider {
	
	private static final String TABLE			= "reply";
	
	public ReplySQLProvider() {
		super(TABLE, "id");
	}
	
	public String getByQuizId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`quiz_id`=#{quizId}");
			}
		}.toString();
	}
	
	public String deleteByQuizId() {
		return new SQL() {
			{
				DELETE_FROM(TABLE);
				WHERE("`quiz_id`=#{quizId}");
			}
		}.toString();
	}
}
