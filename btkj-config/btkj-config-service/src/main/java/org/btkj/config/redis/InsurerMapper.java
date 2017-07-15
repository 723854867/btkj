package org.btkj.config.redis;

import java.util.Collection;
import java.util.Map;

import org.btkj.config.mybatis.dao.InsurerDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Insurer;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class InsurerMapper extends RedisDBAdapter<Integer, Insurer, InsurerDao> {
	
	private final String CONTROLLER							= "insurer:controller";

	public InsurerMapper() {
		super(new ByteProtostuffSerializer<Insurer>(), "hash:db:insurer");
	}
	
	@Override
	public Insurer getByKey(Integer key) {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, CONTROLLER);
		return super.getByKey(key);
	}
	
	@Override
	public Map<Integer, Insurer> getByKeys(Collection<Integer> keys) {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, CONTROLLER);
		return super.getByKeys(keys);
	}
	
	@Override
	public Map<Integer, Insurer> getAll() {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, CONTROLLER);
		return super.getAll();
	}
}
