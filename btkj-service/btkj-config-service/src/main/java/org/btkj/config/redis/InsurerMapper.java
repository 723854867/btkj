package org.btkj.config.redis;

import org.btkj.config.persistence.dao.InsurerDao;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Insurer;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class InsurerMapper extends RedisProtostuffDBMapper<Integer, Insurer, InsurerDao> {

	public InsurerMapper() {
		super(BtkjTables.INSURER, "hash:db:insurer");
	}
}
