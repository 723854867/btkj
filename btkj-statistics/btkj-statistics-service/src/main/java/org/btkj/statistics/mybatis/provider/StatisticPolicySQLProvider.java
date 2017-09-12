package org.btkj.statistics.mybatis.provider;

import java.util.HashSet;
import java.util.Set;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.GroupType;
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
		Set<String> groups = new HashSet<String>();
		String orderBys = null;
		if (null != param.getGroupMod()) {
			for (GroupType type : GroupType.values()) {
				if ((type.mod() & param.getGroupMod()) != type.mod())
					continue;
				switch (type) {
				case APP:
					groups.add(BtkjConsts.FIELD.APP_ID);
					break;
				case USER:
					groups.add(BtkjConsts.FIELD.UID);
					break;
				case TENANT:
					groups.add(BtkjConsts.FIELD.TID);
					break;
				case INSURER:
					groups.add(BtkjConsts.FIELD.INSURER_ID);
					break;
				case EMPLOYEE:
					groups.add(BtkjConsts.FIELD.EMPLOYEE_ID);
					break;
				default:
					break;
				}
			}
			for (String col : groups)
				sql.append("`").append(col).append("`, ");
		}
		switch (param.getTimeType()) {
		case YEAR:
			sql.append("`year`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium, COUNT(*) total ");
			groups.add(BtkjConsts.FIELD.YEAR);
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC" : "`year` DESC";
			break;
		case MONTH:
			sql.append("`year`, `month`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium, COUNT(*) total ");
			groups.add(BtkjConsts.FIELD.YEAR);
			groups.add(BtkjConsts.FIELD.MONTH);
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC, `month` ASC" : "`year` DESC, `month` DESC";			
			break;
		case DAY:
			sql.append("`year`, `month`, `day`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium, COUNT(*) total ");
			groups.add(BtkjConsts.FIELD.YEAR);
			groups.add(BtkjConsts.FIELD.MONTH);
			groups.add(BtkjConsts.FIELD.DAY);
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC, `month` ASC, `day` ASC" : "`year` DESC, `month` DESC, `day` DESC";			
			break;
		case WEEK:
			sql.append("`year`, `month`, `week`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium, COUNT(*) total ");
			groups.add(BtkjConsts.FIELD.YEAR);
			groups.add(BtkjConsts.FIELD.MONTH);
			groups.add(BtkjConsts.FIELD.WEEK);
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC, `month` ASC, `week` ASC" : "`year` DESC, `month` DESC, `week` DESC";			
			break;
		case SEASON:
			sql.append("`year`, `season`, SUM(CAST(`premium` AS DECIMAL(10,2))) premium, COUNT(*) total ");
			groups.add(BtkjConsts.FIELD.YEAR);
			groups.add(BtkjConsts.FIELD.SEASON);
			orderBys = param.getSortType() == SortType.ASC ? "`year` ASC, `season` ASC" : "`year` DESC, `season` DESC";			
			break;
		default:
			sql.append("`created`, CAST(`premium` AS DECIMAL(10,2)) premium ");
			orderBys = param.getSortType() == SortType.ASC ? "`created` ASC" : "`created` DESC";			
			break;
		}
		SqlUtil.appendWithWhere(sql, param.params());
		if (!CollectionUtil.isEmpty(param.params())) {
			if (null != param.getBeginTime())
				sql.append(" AND `created`>=").append(param.getBeginTime());
			if (null != param.getEndTime())
				sql.append(" AND `created`<=").append(param.getEndTime());
		} else {
			if (null != param.getBeginTime())
				sql.append(" WHERE `created`>=").append(param.getBeginTime());
			if (null != param.getEndTime())
				sql.append(" AND `created`<=").append(param.getEndTime());
		}
		if (!CollectionUtil.isEmpty(groups)) {
			sql.append(" GROUP BY ");
			for (String col : groups)
				sql.append("`").append(col).append("`, ");
		}
		return orderBys;
	}
}
