package org.btkj.courier.redis;

import org.btkj.pojo.info.StsInfo;
import org.rapid.data.storage.mapper.JsonMemoryMapper;
import org.springframework.stereotype.Component;

@Component
public class AliyunMapper extends JsonMemoryMapper<String, StsInfo> {
	
	public AliyunMapper() {
		super("hash:memory:stsinfo");
	}
	
	@Override
	public StsInfo getByKey(String key) {
		return redis.hgetJsonValueIfNotExpire(redisKey, key, System.currentTimeMillis(), StsInfo.class);
	}
}
