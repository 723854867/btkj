package org.btkj.courier.redis;

import org.btkj.courier.pojo.info.StsInfo;
import org.rapid.data.storage.mapper.RedisMapper;
import org.rapid.util.common.serializer.impl.ByteJsonSerializer;
import org.springframework.stereotype.Component;

@Component
public class AliyunMapper extends RedisMapper<String, StsInfo> {
	
	public AliyunMapper() {
		super(new ByteJsonSerializer<StsInfo>(), "hash:memory:stsinfo");
	}
	
	@Override
	public StsInfo getByKey(String key) {
		byte[] data = redis.hgetJsonValueIfNotExpire(redisKey, key, System.currentTimeMillis());
		return null == data ? null : serializer.antiConvet(data);
	}
}
