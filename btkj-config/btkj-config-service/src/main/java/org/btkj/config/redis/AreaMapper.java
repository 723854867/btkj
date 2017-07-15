package org.btkj.config.redis;

import java.util.Map;

import org.btkj.config.mybatis.dao.AreaDao;
import org.btkj.config.pojo.entity.Area;
import org.btkj.pojo.BtkjConsts;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class AreaMapper extends RedisDBAdapter<Integer, Area, AreaDao> {
	
	private final String cacheControllerField				= "lock:area";

	public AreaMapper() {
		super(new ByteProtostuffSerializer<Area>(), "hash:db:area");
	}
	
	@Override
	public Map<Integer, Area> getAll() {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, cacheControllerField);
		return super.getAll();
	}
}
