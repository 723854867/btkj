package org.btkj.config.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class ModularSQLProvider extends SQLProvider {

	public ModularSQLProvider() {
		super("modular", "id", false);
	}
	
	@Override
	public String update(Object entity) {
		return new SQL() {
			{
				UPDATE(table);
				SET("`name`=#{name}");
				SET("`parent_id`=#{parentId}");
				SET("`updated=#{updated}`");
			}
		}.toString();
	}
	
	public String updateForInsert() {
		return "UPDATE modular SET `left`=CASE WHEN `left`>#{threshold} THEN `left`+#{step} ELSE `left` END, `right`=CASE WHEN `right`>=#{threshold} THEN `right`+#{step} ELSE `right` END";
	}
	
	public String updateForLeftMove() {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE `").append(table).append("` SET "
				+ "`right`=CASE WHEN `right`>=#{PL} ADN `right`<#{CL} THEN `right`+#{step} ELSE `right` END, "
				+ "`right`=CASE WHEN `left`>=#{CL} AND `right`<=#{CR} THEN `right`+#{step1} ELSE `right` END, "
				+ "`left=CASE WHEN `left`>#{PL} AND `left`<#{CL} THEN `left`+#{step} ELSE `left` END, "
				+ "`left`=CASE WEHN `left`>=#{CL} AND `right`<=#{CR} THEN `left`+#{step1} ELSE `left` END");
		return builder.toString();
	}
	
	public String updateForRightMove() {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE `").append(table).append("` SET "
				+ "`right`=CASE WHEN `right`>#{CR} ADN `right`<#{PR} THEN `right`+#{step} ELSE `right` END, "
				+ "`right`=CASE WHEN `left`>=#{CL} AND `right`<=#{CR} THEN `right`+#{step1} ELSE `right` END, "
				+ "`left=CASE WHEN `left`>#{CR} AND `left`<#{PR} THEN `left`+#{step} ELSE `left` END, "
				+ "`left`=CASE WEHN `left`>=#{CL} AND `right`<=#{CR} THEN `left`+#{step1} ELSE `left` END");
		return builder.toString();
	}
	
	public String updateForDelete() {
		return "UPDATE `modular` SET "
				+ "`left`=CASE WHEN `left`>#{start} THEN `left`-#{step} ELSE `left` END, "
				+ "`right`=CASE WHEN `right`>#{start} THEN `right`-#{step} ELSE `right` END";
	}
}
