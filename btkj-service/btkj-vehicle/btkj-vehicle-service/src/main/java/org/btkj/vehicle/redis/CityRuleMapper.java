package org.btkj.vehicle.redis;

import org.btkj.vehicle.mybatis.Tables;
import org.btkj.vehicle.mybatis.dao.CityRule;
import org.btkj.vehicle.mybatis.entity.CityRuleDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class CityRuleMapper extends RedisProtostuffDBMapper<Integer, CityRule, CityRuleDao> {

	public CityRuleMapper() {
		super(Tables.CITY_RULE, "hash:db:city_rule");
	}
}
