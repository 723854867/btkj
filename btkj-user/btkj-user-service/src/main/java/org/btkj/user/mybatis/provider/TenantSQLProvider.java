package org.btkj.user.mybatis.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.rapid.data.storage.SqlUtil;
import org.rapid.data.storage.mybatis.SQLProvider;
import org.rapid.util.common.enums.SORT_TYPE;

public class TenantSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "tenant";
	
	public TenantSQLProvider() {
		super(TABLE, "tid");
	}
	
	public String countByAppId() {
		return new SQL() {
			{
				SELECT("COUNT(*)");
				FROM(TABLE);
				WHERE("`app_id`=#{appId}");
			}
		}.toString();
	}
	
	public String countByAppIdForUpdate() {
		return "select count(*) from tenant where app_id=#{appId} for update";
	}
	
	public String count(TenantSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM tenant");
		Map<String, Object> params = searcher.params();
		if (null != params && !params.isEmpty()) 
			SqlUtil.appendWithWhere(builder, params);
		return builder.toString();
	}
	
	public String paging(TenantSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM tenant");
		Map<String, Object> params = searcher.params();
		if (null != params && !params.isEmpty())
			SqlUtil.appendWithWhere(builder, params);
		if (null != searcher.getSortCol()) {
			builder.append(" ORDER BY ").append(searcher.getSortCol()).append(" ");
			builder.append(searcher.isAsc() ? SORT_TYPE.ASC.name() : SORT_TYPE.DESC.name());
		}
		builder.append(" LIMIT ").append(searcher.getStart()).append(",").append(searcher.getPageSize());
		return builder.toString();
	}
}
