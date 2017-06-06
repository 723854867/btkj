package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.enums.SortType;
import org.btkj.pojo.submit.QuizSearcher;
import org.btkj.pojo.submit.QuizSearcher.SortCol;

public class QuizSQLProvider {
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.QUIZ.name());
				WHERE("id=#{key}");
			}
		}.toString();
	}
	
	public String update() { 
		return new SQL() {
			{
				UPDATE(BtkjTables.QUIZ.name());
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
				builder.append("ORDER BY browse_num ");
				break;
			case REPLY_NUM:
				builder.append("ORDER BY reply_num ");
				break;
			default:
				builder.append("ORDER BY created ");
				break;
			}
		}
		SortType sortType = searcher.getSortType();
		builder.append(null == sortType ? "DESC " : SortType.ASC == sortType ? "ASC " : "DESC ");
		builder.append("LIMIT ").append(searcher.getStart()).append(", ").append(searcher.getPageSize());
		return builder.toString();
	}
}
