package org.btkj.statistics.mybatis.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.statistics.StatisticPolicy;
import org.btkj.pojo.info.statistics.PolicyStatisticInfo;
import org.btkj.pojo.param.statistics.StatisticPoliciesParam;
import org.btkj.statistics.mybatis.provider.StatisticPolicySQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface StatisticPolicyDao extends DBMapper<String, StatisticPolicy> {

	@Override
	@UpdateProvider(type = StatisticPolicySQLProvider.class, method = "replace")
	void replace(Collection<StatisticPolicy> collection);
	
	@SelectProvider(type = StatisticPolicySQLProvider.class, method = "statisticPoliciesTotal")
	int statisticPoliciesTotal(StatisticPoliciesParam param);
	
	@SelectProvider(type = StatisticPolicySQLProvider.class, method = "statisticPolicies")
	List<PolicyStatisticInfo> statisticPolicies(StatisticPoliciesParam param);
}
