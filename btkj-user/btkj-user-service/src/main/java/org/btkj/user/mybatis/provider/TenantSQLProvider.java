package org.btkj.user.mybatis.provider;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.rapid.util.common.enums.SORT_TYPE;

public class TenantSQLProvider {
	
	private static final String TABLE			= "tenant";
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`name`", "#{name}");
				VALUES("app_id", "#{appId}");
				VALUES("region", "#{region}");
				VALUES("`team_depth`", "#{teamDepth}");
				VALUES("`jian_jie_id`", "#{jianJieId}");
				VALUES("`license_face`", "#{licenseFace}");
				VALUES("`license_back`", "#{licenseBack}");
				VALUES("`non_auto_bind`", "#{nonAutoBind}");
				VALUES("`created`", "#{created}");
				VALUES("`updated`", "#{updated}");
			}
		}.toString();
	}

	public String update() {
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("`name`=#{name}");
				SET("`team_depth`=#{teamDepth}");
				SET("`non_auto_bind`=#{nonAutoBind}");
				SET("updated=#{updated}");
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
	
	public String getAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
			}
		}.toString();
	}
	
	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{key}");
			}
		}.toString();
	}
	
	public String getWithinKey(Map<String, List<Integer>> params) {
		List<Integer> keys = params.get("list");
		StringBuilder builder = new StringBuilder("select * from tenant where tid in(");
		for (int key : keys)
			builder.append(key).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
	
	public String countByAppId() {
		return new SQL() {
			{
				SELECT("COUNT(*)");
				FROM(TABLE);
				WHERE("app_id=#{appId}");
			}
		}.toString();
	}
	
	public String countByAppIdForUpdate() {
		return "select count(*) from tenant where app_id=#{appId} for update";
	}
	
	public String count(TenantSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM tenant");
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
	
	public String paging(TenantSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM tenant");
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
