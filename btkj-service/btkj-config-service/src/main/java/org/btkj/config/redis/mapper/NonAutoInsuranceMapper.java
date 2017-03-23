package org.btkj.config.redis.mapper;

import org.btkj.config.persistence.dao.NonAutoInsuranceDao;
import org.btkj.config.redis.RedisKeyGenerator;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.NonAutoInsurance;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class NonAutoInsuranceMapper extends O2OMapper<Integer, NonAutoInsurance, byte[], NonAutoInsuranceDao> {

	public NonAutoInsuranceMapper() {
		super(BtkjTables.NON_AUTO_INSURANCE, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.insuranceDataKey()));
	}

	
}
