package org.btkj.user.redis.mapper;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.persistence.dao.BannerDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class BannerMapper extends O2OMapper<Integer, Banner, byte[], BannerDao> {

	public BannerMapper() {
		super(BtkjTables.BANNER, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.bannerDataKey()));
	}
}
