package org.btkj.bihu.vehicle.redis;

import java.util.List;

import org.btkj.bihu.vehicle.mybatis.dao.BiHuAreaDao;
import org.btkj.bihu.vehicle.pojo.entity.BiHuArea;
import org.btkj.pojo.BtkjConsts;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class BiHuAreaMapper extends RedisDBAdapter<Integer, BiHuArea, BiHuAreaDao> {
	
	private String cacheControllerField					= "lock:bi_hu_city";

	public BiHuAreaMapper() {
		super(new ByteProtostuffSerializer<BiHuArea>(), "hash:db:bi_hu_city");
	}
	
	public List<BiHuArea> getAll() {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, cacheControllerField);
		return super.getAll();
	}
}
