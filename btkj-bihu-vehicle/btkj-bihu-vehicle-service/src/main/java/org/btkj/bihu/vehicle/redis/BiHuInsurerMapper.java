package org.btkj.bihu.vehicle.redis;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.bihu.vehicle.mybatis.dao.BiHuInsurerDao;
import org.btkj.bihu.vehicle.pojo.entity.BiHuInsurer;
import org.btkj.pojo.BtkjConsts;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class BiHuInsurerMapper extends RedisDBAdapter<Integer, BiHuInsurer, BiHuInsurerDao> {
	
	private final String CODE_MAP					= "hash:memory:BHI_map";
	private final String cacheControllerField		= "lock:bi_hu_insurer";

	public BiHuInsurerMapper() {
		super(new ByteProtostuffSerializer<BiHuInsurer>(), "hash:db:bi_hu_insurer");
	}
	
	@Override
	public List<BiHuInsurer> getAll() {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, cacheControllerField);
		return super.getAll();
	}
	
	public BiHuInsurer getByCode(int code) {
		byte[] data = redis.load_1(CODE_MAP, redisKey, code);
		if (null == data) {
			BiHuInsurer insurer = dao.getByCode(code);
			if (null != insurer)
				flush(insurer);
			return insurer;
		}
		return serializer.antiConvet(data);
	}
	
	@Override
	public void flush(BiHuInsurer entity) {
		redis.flush_1(redisKey, CODE_MAP, entity.getId(), serializer.convert(entity), entity.getCode());
	}
	
	@Override
	public void flush(Collection<BiHuInsurer> models) {
		Map<BiHuInsurer, Object> map = new HashMap<BiHuInsurer, Object>();
		for (BiHuInsurer insurer : models)
			map.put(insurer, insurer.getCode());
		redis.flush_1_batch(redisKey, CODE_MAP, map, serializer);
	}
}
