package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.Config;
import org.btkj.user.UserLuaCmd;
import org.btkj.user.persistence.dao.BannerDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class BannerMapper extends ProtostuffDBMapper<Integer, Banner, BannerDao> {
	
	private static final String DATA_KEY						= "hash:db:banner";
	private static final String LIST							= "set:banner:list:{0}:{1}";	// 租户 banner 列表
	private static final String LIST_CONTROLLER					= "banner：{0}：{1}";

	public BannerMapper() {
		super(BtkjTables.BANNER, DATA_KEY);
	}
	
	public List<Banner> getByAppIdAndTid(int appId, int tid) {
		String listKey = listKey(appId, tid);
		List<byte[]> list = redis.invokeLua(UserLuaCmd.BANNER_LIST_LOAD, 
				SerializeUtil.RedisUtil.encode(
						Config.CACHE_CONTROLLER,
						listKey, DATA_KEY,
						listControllerKey(appId, tid)));
		List<Banner> banners = null;
		if (null == list) {
			banners = dao.selectByAppIdAndTid(appId, tid);
			byte[][] params = new byte[banners.size() * 2 + 4][];
			int index = 0;
			params[index++] = SerializeUtil.RedisUtil.encode(Config.CACHE_CONTROLLER);
			params[index++] = SerializeUtil.RedisUtil.encode(DATA_KEY);
			params[index++] = SerializeUtil.RedisUtil.encode(listKey(appId, tid));
			params[index++] = SerializeUtil.RedisUtil.encode(listControllerKey(appId, tid));
			for (int i = 0, len = banners.size(); i < len; i++) {
				Banner banner = banners.get(i);
				params[index++] = SerializeUtil.RedisUtil.encode(banner.getId());
				params[index++] = serial(banner);
			}
			redis.invokeLua(UserLuaCmd.BANNER_LIST_FLUSH, params);
		} else {
			banners = new ArrayList<Banner>(list.size());
			for (byte[] data : list)
				banners.add(deserial(data));
		}
		if (0 == tid) 			// 如果获取的是 app 的 banner 则直接返回
			return banners;
		// 如果获取的时代理商的 banner，则需要从 app 中填补剩下的 banner 位数
		int bannerNum = GlobalConfigContainer.getGlobalConfig().getBannerNum();
		if (banners.size() < bannerNum) {
			List<Banner> l = getByAppIdAndTid(appId, 0);
			int idx = Math.min(bannerNum - banners.size(), l.size());
			for (int i = 0; i < idx; i++)
				banners.add(l.get(i));
		}
		return banners;
	}
	
	@Override
	protected void flush(Banner entity) {
		redis.invokeLua(UserLuaCmd.BANNER_FLUSH, 
				SerializeUtil.RedisUtil.encode(
						DATA_KEY, 
						listKey(entity.getAppId(), entity.getTid()),
						entity.getId(),
						serial(entity)));
	}
	
	public static final String listKey(int appId, int tid) { 
		return MessageFormat.format(LIST, String.valueOf(appId), String.valueOf(tid));
	}
	
	public static final String listControllerKey(int appId, int tid) { 
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(appId), String.valueOf(tid));
	}
}
