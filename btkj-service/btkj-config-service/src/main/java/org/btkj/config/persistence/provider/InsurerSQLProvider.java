package org.btkj.config.persistence.provider;

import java.util.List;
import java.util.Map;

public class InsurerSQLProvider {
	
	public String selectWithinKey(Map<String, List<Integer>> params) {
		List<Integer> keys = params.get("list");
		StringBuilder builder = new StringBuilder("select * from insurer where id in(");
		for (int key : keys)
			builder.append(key).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
}
