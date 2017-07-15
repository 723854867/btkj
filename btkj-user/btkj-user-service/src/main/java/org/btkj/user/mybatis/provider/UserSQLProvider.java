package org.btkj.user.mybatis.provider;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.user.pojo.submit.UserSearcher;
import org.rapid.data.storage.mybatis.SQLProvider;
import org.rapid.util.common.enums.SORT_TYPE;

public class UserSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "user";
	
	public UserSQLProvider() {
		super(TABLE, "uid");
	}
	
	public String getByMobile() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`app_id`=#{appId}");
				AND();
				WHERE("`mobile`=#{mobile}");
			}
		}.toString();
	}
	
	public String count(UserSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM user");
		Map<String, String> params = searcher.params();
		if (null != params && !params.isEmpty()) {
			builder.append(" WHERE ");
			boolean first = true;
			for (Entry<String, String> entry : params.entrySet()) {
				if (!first)
					builder.append(" AND ");
				first = false;
				builder.append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		return builder.toString();
	}
	
	public String paging(UserSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM user");
		Map<String, String> params = searcher.params();
		if (null != params && !params.isEmpty()) {
			builder.append(" WHERE ");
			boolean first = true;
			for (Entry<String, String> entry : params.entrySet()) {
				if (!first)
					builder.append(" AND ");
				first = false;
				builder.append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		if (null != searcher.getSortCol()) {
			builder.append(" ORDER BY ").append(searcher.getSortCol()).append(" ");
			builder.append(searcher.isAsc() ? SORT_TYPE.ASC.name() : SORT_TYPE.DESC.name());
		}
		builder.append(" LIMIT ").append(searcher.getStart()).append(",").append(searcher.getPageSize());
		return builder.toString();
	}
}
