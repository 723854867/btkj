package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class QuizSQLProvider extends SQLProvider {
	
	private static final String TABLE				= "quiz";
	
	public QuizSQLProvider() {
		super(TABLE, "id");
	}
	
	public String getByAppId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`app_id`=#{appId}");
			}
		}.toString();
	}
}
