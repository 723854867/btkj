package org.btkj.vehicle.redis;

import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.vehicle.mybatis.dao.AreaDao;
import org.btkj.vehicle.pojo.entity.Area;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class AreaMapper extends RedisDBAdapter<Integer, Area, AreaDao> {
	
	private final String cacheControllerField				= "lock:city";

	public AreaMapper() {
		super(new ByteProtostuffSerializer<Area>(), "hash:db:city");
	}
	
	@Override
	public List<Area> getAll() {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, cacheControllerField);
		return super.getAll();
	}
}
