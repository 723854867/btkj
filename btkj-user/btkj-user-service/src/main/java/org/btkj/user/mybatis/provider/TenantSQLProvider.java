package org.btkj.user.mybatis.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.user.pojo.param.TenantsParam;
import org.rapid.data.storage.SqlUtil;
import org.rapid.data.storage.mybatis.SQLProvider;
import org.rapid.util.common.enums.SortType;

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
	
	public String count(TenantsParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM tenant");
		Map<String, Object> params = param.params();
		if (null != params && !params.isEmpty()) 
			SqlUtil.appendWithWhere(builder, params);
		return builder.toString();
	}
	
	public String tenants(TenantsParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM tenant");
		Map<String, Object> params = param.params();
		if (null != params && !params.isEmpty())
			SqlUtil.appendWithWhere(builder, params);
		if (null != param.getSortCol()) {
			builder.append(" ORDER BY ").append(param.getSortCol()).append(" ");
			builder.append(param.isAsc() ? SortType.ASC.name() : SortType.DESC.name());
		}
		builder.append(" LIMIT ").append(param.getStart()).append(",").append(param.getPageSize());
		return builder.toString();
	}
}
