package org.btkj.statistics.mybatis.provider;

import org.btkj.pojo.param.statistics.StatisticPoliciesParam;
import org.rapid.data.storage.SqlUtil;
import org.rapid.data.storage.mybatis.SQLProvider;
import org.rapid.util.common.enums.SortType;
import org.rapid.util.lang.CollectionUtil;

public class StatisticPolicySQLProvider extends SQLProvider {
	
	private static final String TABLE			= "statistic_policy";

	public StatisticPolicySQLProvider() {
		super(TABLE, "id", false);
	}
	
	public String statisticPoliciesTotal(StatisticPoliciesParam param) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM (SELECT ");
		_statisticSql(sql, param);
		sql.append(") t");
		return sql.toString();
	}
	
	public String statisticPolicies(StatisticPoliciesParam param) {
		StringBuilder sql = new StringBuilder("SELECT ");
		String orderBys = _statisticSql(sql, param);
		sql.append(" ORDER BY ").append(orderBys).append(" LIMIT ").append(param.getStart()).append(",").append(param.getPageSize());
		return sql.toString();
	}
	
	private String _statisticSql(StringBuilder sql, StatisticPoliciesParam param) { 
		String groupBys = null;
		String orderBys = null;
		switch (param.getTimeType()) {
		case YEAR:
			sql.append("`year`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium ");
			groupBys = "`year`";
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC" : "`year` DESC";
			break;
		case MONTH:
			sql.append("`year`, `month`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium ");
			groupBys = "`year`, `month`";
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC, `month` ASC" : "`year` DESC, `month` DESC";			
			break;
		case DAY:
			sql.append("`year`, `month`, `day`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium ");
			groupBys = "`year`, `month`, `day`";
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC, `month` ASC, `day` ASC" : "`year` DESC, `month` DESC, `day` DESC";			
			break;
		case WEEK:
			sql.append("`year`, `month`, `week`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium ");
			groupBys = "`year`, `month`, `week`";
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC, `month` ASC, `week` ASC" : "`year` DESC, `month` DESC, `week` DESC";			
			break;
		case SEASON:
			sql.append("`year`, `season`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium ");
			groupBys = "`year`, `season`";
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC, `season` ASC" : "`year` DESC, `season` DESC";			
			break;
		default:
			sql.append("`created`, CAST(`premium` AS DECIMAL(10,2)) premium ");
			orderBys = param.getSortType() == SortType.ASC ? "`created` ASC" : "`created` DESC";			
			break;
		}
		SqlUtil.appendWithWhere(sql, param.params());
		if (!CollectionUtil.isEmpty(param.params()))
			sql.append(" AND `created`>=").append(param.getBeginTime()).append(" AND `created`<=").append(param.getEndTime());
		else
			sql.append("WHERE `created`>=").append(param.getBeginTime()).append(" AND `created`<=").append(param.getEndTime());
		if (null != groupBys) 
			sql.append(" GROUP BY ").append(groupBys);
		return orderBys;
	}
}
