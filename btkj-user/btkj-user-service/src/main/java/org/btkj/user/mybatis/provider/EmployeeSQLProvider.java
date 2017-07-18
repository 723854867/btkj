package org.btkj.user.mybatis.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.user.pojo.submit.EmployeeSearcher;
import org.rapid.data.storage.SqlUtil;
import org.rapid.data.storage.mybatis.SQLProvider;
import org.rapid.util.common.enums.SORT_TYPE;

public class EmployeeSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "employee";
	
	public EmployeeSQLProvider() {
		super(TABLE, "id");
	}

	public String getByTidAndUid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`tid`=#{tid}");
				AND();
				WHERE("`uid`=#{uid}");
			}
		}.toString();
	}
	
	public String getByUid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`uid`=#{uid}");
			}
		}.toString();
	}
	
	public String team() {
		return "SELECT * FROM employee WHERE (parent_id=#{id} AND `left`>#{left} AND `right`<#{right} AND `level`<=#{level}) OR id=#{id}";
	}
	
	public String getByTidForUpdate() {
		return "select * from employee where `tid`=#{tid} for update";
	}
	
	public String updateForJoin() {
		return "update employee set `left`=case when `left`>#{value} then `left`+2 else `left` end, `right`=case when `right`>=#{value} then `right`+2 else `right` end where tid=#{tid}";
	}
	
	public String count(EmployeeSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM employee");
		Map<String, Object> params = searcher.params();
		if (null != params && !params.isEmpty()) 
			SqlUtil.appendWithWhere(builder, params);
		return builder.toString();
	}
	
	public String paging(EmployeeSearcher searcher) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM employee");
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
