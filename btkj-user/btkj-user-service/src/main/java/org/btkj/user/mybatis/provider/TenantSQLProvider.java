package org.btkj.user.mybatis.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.rapid.data.storage.mybatis.SQLProvider;
import org.rapid.util.common.SqlUtil;
import org.rapid.util.common.enums.SORT_TYPE;

public class TenantSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "tenant";
	
	public TenantSQLProvider() {
		super(TABLE, "tid");
	}
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`name`", "#{name}");
				VALUES("`app_id`", "#{appId}");
				VALUES("`region`", "#{region}");
				VALUES("`team_depth`", "#{teamDepth}");
				VALUES("`license_face`", "#{licenseFace}");
				VALUES("`license_back`", "#{licenseBack}");
				VALUES("`service_phone`", "#{servicePhone}");
				VALUES("`jian_jie_fetch_time`", "#{jianJieFetchTime}");
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
				SET("`jian_jie_id`=#{jianJieId}");
				SET("`team_depth`=#{teamDepth}");
				SET("`non_auto_bind`=#{nonAutoBind}");
				SET("`service_phone`=#{servicePhone}");
				SET("`bonus_scale_count_mod`=#{bonusScaleCountMod}");
				SET("`bonus_scale_reward_mod`=#{bonusScaleRewardMod}");
				SET("`jian_jie_fetch_time`=#{jianJieFetchTime}");
				SET("`updated`=#{updated}");
				WHERE("`tid`=#{tid}");
			}
		}.toString();
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
