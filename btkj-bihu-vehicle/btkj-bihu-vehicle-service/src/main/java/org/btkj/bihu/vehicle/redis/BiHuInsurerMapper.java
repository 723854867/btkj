package org.btkj.bihu.vehicle.redis;

import java.util.List;

import org.btkj.bihu.vehicle.mybatis.Tables;
import org.btkj.bihu.vehicle.mybatis.dao.BiHuInsurerDao;
import org.btkj.bihu.vehicle.mybatis.entity.BiHuInsurer;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class BiHuInsurerMapper extends RedisProtostuffDBMapper<Integer, BiHuInsurer, BiHuInsurerDao> {
	
	private final String CODE_MAP					= "hash:memory:BHI_map";

	public BiHuInsurerMapper() {
		super(Tables.BI_HU_INSURER, "hash:db:bi_hu_insurer");
	}
	
	public BiHuInsurer getByCode(int code) {
		byte[] data = redis.load_1(CODE_MAP, redisKey, code);
		if (null == data) {
			BiHuInsurer insurer = dao.selectByCode(code);
			if (null != insurer)
				flush(insurer);
			return insurer;
		}
		return deserial(data);
	}
	
	@Override
	public void flush(BiHuInsurer entity) {
		redis.flush_1(redisKey, CODE_MAP, entity.getId(), serial(entity), entity.getCode());
	}
	
	@Override
	public void flush(List<BiHuInsurer> entities) {
		throw new UnsupportedOperationException("BiHuInsurerMapper unsupport multi flush!");
	}
}
