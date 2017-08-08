package org.btkj.config.mybatis.provider;

import java.util.Map;
import java.util.Set;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class ModularSQLProvider extends SQLProvider {

	public ModularSQLProvider() {
		super("modular", "id", false);
		addNoUpdateCol("created", "left", "right", "layer", "id");
	}
	
	public String getChildren() {
		return new SQL() {
			{
				SELECT("*");
				FROM(table);
				WHERE("`left`>=#{left}");
				AND();
				WHERE("`right`<=#{right}");
			}
		}.toString();
	}
	
	public String updateForInsert() {
		return "UPDATE modular SET `left`=CASE WHEN `left`>#{threshold} THEN `left`+#{step} ELSE `left` END, `right`=CASE WHEN `right`>=#{threshold} THEN `right`+#{step} ELSE `right` END";
	}
	
	public String updateForMove(Map<String, Object> params) {
		StringBuilder builder = new StringBuilder("UPDATE `modular` SET `left`=`left`+#{step}, `right`=`right`+#{step}"
				+ " WHERE `left`>=#{start} AND `right`<=#{end} AND `id` IN(");
		Set<Integer> set = (Set<Integer>) params.get("set");
		for (int id : set)
			builder.append(id).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
	
	public String updateForLeftMove(Map<String, Object> params) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE `").append(table).append("` SET `right`=CASE WHEN `right`>=").append(params.get("PR"))
				.append(" AND `right`<").append(params.get("CL")).append(" THEN `right`+").append(params.get("step"))
				.append(" ELSE `right` END, `left`=CASE WHEN `left`>").append(params.get("PR")).append(" AND `left`<")
				.append(params.get("CL")).append(" THEN `left`+").append(params.get("step")).append("  ELSE `left` END")
				.append(" WHERE `id` NOT IN(");
		Set<Integer> set = (Set<Integer>) params.get("set");
		for (int id : set)
			builder.append(id).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
	
	public String updateForRightMove(Map<String, Object> params) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE `").append(table).append("` SET `right`=CASE WHEN `right`>").append(params.get("CR"))
				.append(" AND `right`<").append(params.get("PR")).append(" THEN `right`+").append(params.get("step"))
				.append(" ELSE `right` END, `left`=CASE WHEN `left`>").append(params.get("CR")).append(" AND `left`<")
				.append(params.get("PR")).append(" THEN `left`+").append(params.get("step")).append(" ELSE `left` END")
				.append(" WHERE `id` NOT IN(");
		Set<Integer> set = (Set<Integer>) params.get("set");
		for (int id : set)
			builder.append(id).append(",");
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
	
	@Override
	public String delete() {
		return new SQL() {
			{
				DELETE_FROM(table);
				WHERE("left>=#{left}");
				AND();
				WHERE("right<=#{right}");
			}
		}.toString();
	}
}
