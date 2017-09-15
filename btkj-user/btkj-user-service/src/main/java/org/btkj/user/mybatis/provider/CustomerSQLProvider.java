package org.btkj.user.mybatis.provider;

import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.param.user.CustomerListParam;
import org.rapid.data.storage.mybatis.SQLProvider;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.SortType;
import org.rapid.util.lang.CollectionUtil;

public class CustomerSQLProvider extends SQLProvider {

	private static final String TABLE			= "customer";
	
	public CustomerSQLProvider() {
		super(TABLE, "id");
	}

	public String total(CustomerListParam param) {
		StringBuilder builder = new StringBuilder("SELECT COUNT(*) FROM customer WHERE uid=#{uid} ");
		Map<String, String> params = param.params();
		if (!CollectionUtil.isEmpty(params)) {
			for (Entry<String, String> entry : params.entrySet()) 
				builder.append("AND ").append(entry.getKey()).append(Consts.SYMBOL_EQUAL).append(entry.getValue()).append(" ");
		}
		return builder.toString();
	}
	
	public String paging(CustomerListParam param) {
		StringBuilder builder = new StringBuilder("SELECT * FROM customer WHERE uid=#{uid} ");
		Map<String, String> params = param.params();
		if (null != params) {
			for (Entry<String, String> entry : params.entrySet()) 
				builder.append("AND ").append(entry.getKey()).append(Consts.SYMBOL_EQUAL).append(entry.getValue()).append(" ");
		}
		if (null != param.getSortCol())
			builder.append("ORDER BY #{sortCol} ").append(param.isAsc() ? SortType.ASC.name() : SortType.DESC.name()).append(" ");
		builder.append("LIMIT #{start}, #{pageSize}");
		return builder.toString();
	}
}
