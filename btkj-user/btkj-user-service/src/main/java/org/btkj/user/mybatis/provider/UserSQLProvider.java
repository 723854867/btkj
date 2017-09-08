package org.btkj.user.mybatis.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.param.user.UsersParam;
import org.rapid.data.storage.SqlUtil;
import org.rapid.data.storage.mybatis.SQLProvider;
import org.rapid.util.common.enums.SortType;

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
	
	public String count(UsersParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM user");
		Map<String, Object> params = param.params();
		if (null != params && !params.isEmpty()) 
			SqlUtil.appendWithWhere(builder, params);
		return builder.toString();
	}
	
	public String users(UsersParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM user");
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
