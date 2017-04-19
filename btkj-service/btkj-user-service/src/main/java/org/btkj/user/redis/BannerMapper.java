package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.Config;
import org.btkj.user.UserLuaCmd;
import org.btkj.user.persistence.dao.BannerDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class BannerMapper extends ProtostuffDBMapper<Integer, Banner, BannerDao> {
	
	private static final String BANNER_DATA						= "hash:db:banner";
	private static final String BANNER_LIST						= "hash:banner:list:{0}:{1}";
	private static final String BANNER_LIST_CONTROLLER			= "blc";

	public BannerMapper() {
		super(BtkjTables.BANNER, BANNER_DATA);
	}
	
	public List<Banner> getByAppIdAndTid(int appId, int tid) {
		String listKey = bannerListKey(appId, tid);
		List<byte[]> list = redis.invokeLua(UserLuaCmd.BANNER_LIST_LOAD, 
				SerializeUtil.RedisUtil.encode(
						Config.CACHE_CONTROLLER,
						listKey, BANNER_DATA,
						BANNER_LIST_CONTROLLER));
		List<Banner> banners = null;
		if (null == list) {
			banners = dao.selectByAppIdAndTid(appId, tid);
			byte[][] params = new byte[banners.size() * 2 + 4][];
			int index = 0;
			params[index++] = SerializeUtil.RedisUtil.encode(Config.CACHE_CONTROLLER);
			params[index++] = SerializeUtil.RedisUtil.encode(BANNER_DATA);
			params[index++] = SerializeUtil.RedisUtil.encode(bannerListKey(appId, tid));
			params[index++] = SerializeUtil.RedisUtil.encode(BANNER_LIST_CONTROLLER);
			for (int i = 0, len = banners.size(); i < len; i++) {
				Banner banner = banners.get(i);
				params[index++] = SerializeUtil.RedisUtil.encode(banner.getId());
				params[index++] = serial(banner);
			}
			redis.invokeLua(UserLuaCmd.BANNER_LIST_FLUSH, params);
			return banners;
		} else {
			banners = new ArrayList<Banner>(list.size());
			for (byte[] data : list)
				banners.add(deserial(data));
		}
		return banners;
	}
	
	@Override
	protected void flush(Banner entity) {
		redis.invokeLua(UserLuaCmd.BANNER_FLUSH, 
				SerializeUtil.RedisUtil.encode(
						BANNER_DATA, 
						bannerListKey(entity.getAppId(), entity.getTid()),
						entity.getId(),
						serial(entity)));
	}
	
	public static final String bannerListKey(int appId, int tid) { 
		return MessageFormat.format(BANNER_LIST, String.valueOf(appId), String.valueOf(tid));
	}
}
