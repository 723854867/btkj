package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.submit.QuizSearcher;
import org.btkj.pojo.submit.QuizSearcher.SortCol;
import org.rapid.util.common.enums.SORT_TYPE;

public class QuizSQLProvider {
	
	private static final String TABLE				= "quiz";
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("app_id", "#{appId}");
				VALUES("reply_num", "#{replyNum}");
				VALUES("uid", "#{uid}");
				VALUES("browse_num", "#{browseNum}");
				VALUES("content", "#{content}");
				VALUES("created", "#{created}");
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
				SET("browse_num=#{browseNum}");
				SET("reply_num=#{replyNum}");
				WHERE("id=#{id}");
			}
		}.toString();
	}

	public String total(QuizSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM quiz ");
		if (null != searcher.getAppId())
			builder.append("WHERE app_id=").append(searcher.getAppId());
		return builder.toString();
	}
	
	public String paging(QuizSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM quiz ");
		if (null != searcher.getAppId())
			builder.append("WHERE app_id=").append(searcher.getAppId());
		SortCol col = searcher.getSortCol();
		if (null == col) 
			builder.append(" ORDER BY created ");
		else {
			switch (col) {
			case BROWSE_NUM:
				builder.append(" ORDER BY browse_num ");
				break;
			case REPLY_NUM:
				builder.append(" ORDER BY reply_num ");
				break;
			default:
				builder.append(" ORDER BY created ");
				break;
			}
		}
		SORT_TYPE sortType = searcher.getSortType();
		builder.append(null == sortType ? "DESC " : SORT_TYPE.ASC == sortType ? "ASC " : "DESC ");
		builder.append("LIMIT ").append(searcher.getStart()).append(", ").append(searcher.getPageSize());
		return builder.toString();
	}
}
