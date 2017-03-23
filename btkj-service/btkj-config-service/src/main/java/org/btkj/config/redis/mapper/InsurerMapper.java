package org.btkj.config.redis.mapper;

import org.btkj.config.persistence.dao.InsurerDao;
import org.btkj.config.redis.RedisKeyGenerator;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Insurer;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class InsurerMapper extends O2OMapper<Integer, Insurer, byte[], InsurerDao> {

	public InsurerMapper() {
		super(BtkjTables.INSURER, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.insuranceDataKey()));
	}

}
