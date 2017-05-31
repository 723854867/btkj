package org.btkj.bihu.vehicle.redis;

import org.btkj.bihu.vehicle.mybatis.Tables;
import org.btkj.bihu.vehicle.mybatis.dao.BiHuInsurerDao;
import org.btkj.bihu.vehicle.mybatis.entity.BiHuInsurer;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class BiHuInsurerMapper extends RedisProtostuffDBMapper<Integer, BiHuInsurer, BiHuInsurerDao> {

	public BiHuInsurerMapper() {
		super(Tables.BI_HU_INSURER, "hash:db:bi_hu_insurer");
	}
}
