package org.btkj.config.redis.mapper;

import org.btkj.config.persistence.dao.InsuranceDao;
import org.btkj.config.redis.RedisKeyGenerator;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Insurance;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.SerializeUtil;

public class InsuranceMapper extends O2OMapper<Integer, Insurance, byte[], InsuranceDao> {

	public InsuranceMapper() {
		super(BtkjTables.INSURANCE, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.insuranceDataKey()));
	}

}
