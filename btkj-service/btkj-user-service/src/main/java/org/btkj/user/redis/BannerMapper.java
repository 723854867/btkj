package org.btkj.user.redis;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.persistence.dao.BannerDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;

public class BannerMapper extends ProtostuffDBMapper<Integer, Banner, BannerDao> {
	
	private static final String BANNER_DATA						= "hash:db:banner";

	public BannerMapper() {
		super(BtkjTables.BANNER, BANNER_DATA);
	}
}
