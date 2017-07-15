package org.btkj.user.mybatis.provider;

import java.util.Map;
import java.util.Map.Entry;

import org.btkj.user.pojo.submit.CustomerSearcher;
import org.rapid.data.storage.mybatis.SQLProvider;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.SORT_TYPE;

public class CustomerSQLProvider extends SQLProvider {

	private static final String TABLE			= "customer";
	
	public CustomerSQLProvider() {
		super(TABLE, "id");
	}

	public String total(CustomerSearcher searcher) {
		StringBuilder builder = new StringBuilder("SELECT COUNT(*) FROM customer WHERE uid=#{uid} ");
		Map<String, String> params = searcher.params();
		if (null != params) {
			for (Entry<String, String> entry : params.entrySet()) 
				builder.append("AND ").append(entry.getKey()).append(Consts.SYMBOL_EQUAL).append(entry.getValue()).append(" ");
		}
		return builder.toString();
	}
	
	public String paging(CustomerSearcher searcher) {
		StringBuilder builder = new StringBuilder("SELECT * FROM customer WHERE uid=#{uid} ");
		Map<String, String> params = searcher.params();
		if (null != params) {
			for (Entry<String, String> entry : params.entrySet()) 
				builder.append("AND ").append(entry.getKey()).append(Consts.SYMBOL_EQUAL).append(entry.getValue()).append(" ");
		}
		if (null != searcher.getSortCol())
			builder.append("ORDER BY #{sortCol} ").append(searcher.isAsc() ? SORT_TYPE.ASC.name() : SORT_TYPE.DESC.name()).append(" ");
		builder.append("LIMIT #{start}, #{pageSize}");
		return builder.toString();
	}
}
