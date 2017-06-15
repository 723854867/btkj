package org.btkj.bihu.vehicle.redis;

import org.btkj.bihu.vehicle.mybatis.dao.BiHuInsurerDao;
import org.btkj.bihu.vehicle.mybatis.entity.BiHuInsurer;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class BiHuInsurerMapper extends RedisDBAdapter<Integer, BiHuInsurer, BiHuInsurerDao> {
	
	private final String CODE_MAP					= "hash:memory:BHI_map";

	public BiHuInsurerMapper() {
		super(new ByteProtostuffSerializer<BiHuInsurer>(), "hash:db:bi_hu_insurer");
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
}
