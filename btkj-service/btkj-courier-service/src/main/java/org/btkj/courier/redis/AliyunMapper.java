package org.btkj.courier.redis;

import org.btkj.pojo.info.StsInfo;
import org.rapid.data.storage.mapper.JsonMemoryMapper;

public class AliyunMapper extends JsonMemoryMapper<String, StsInfo> {
	
	private static final String DATA_KEY				= "hash:memory:stsinfo";

	public AliyunMapper() {
		super(DATA_KEY);
	}
	
	@Override
	public StsInfo getByKey(String key) {
		return redis.hgetJsonValueIfNotExpire(DATA_KEY, key, System.currentTimeMillis(), StsInfo.class);
	}
}
