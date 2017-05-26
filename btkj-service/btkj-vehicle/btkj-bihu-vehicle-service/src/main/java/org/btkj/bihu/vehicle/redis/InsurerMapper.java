package org.btkj.bihu.vehicle.redis;

import org.btkj.bihu.vehicle.mybatis.Tables;
import org.btkj.bihu.vehicle.mybatis.dao.InsurerDao;
import org.btkj.bihu.vehicle.mybatis.entity.Insurer;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class InsurerMapper extends RedisProtostuffDBMapper<Integer, Insurer, InsurerDao> {

	public InsurerMapper() {
		super(Tables.INSURER, "hash:db:insurer");
	}
}
