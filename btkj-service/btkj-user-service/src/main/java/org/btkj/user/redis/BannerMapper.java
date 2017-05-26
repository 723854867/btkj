package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.persistence.dao.BannerDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class BannerMapper extends RedisProtostuffDBMapper<Integer, Banner, BannerDao> {
	
	private String LIST							= "set:banner:list:{0}:{1}";	// 租户 banner 列表
	private String LIST_CONTROLLER				= "banner：controller:{0}：{1}";

	public BannerMapper() {
		super(BtkjTables.BANNER, "hash:db:banner");
	}
	
	public List<Banner> getByAppIdAndTid(int appId, int tid) {
		String listKey = _listKey(appId, tid);
		List<byte[]> list = redis.protostuffCacheListLoadWithData(BtkjConsts.CACHE_CONTROLLER, listKey, redisKey, _listController(appId, tid));
		List<Banner> banners = null;
		if (null == list) {
			banners = dao.selectByAppIdAndTid(appId, tid);
			redis.protostuffCacheListFlush(BtkjConsts.CACHE_CONTROLLER, redisKey, _listKey(appId, tid), _listController(appId, tid), banners);
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
	public void flush(Banner entity) {
		redis.protostuffCacheFlush(redisKey, entity, _listKey(entity.getAppId(), entity.getTid()));
	}
	
	public String _listKey(int appId, int tid) { 
		return MessageFormat.format(LIST, String.valueOf(appId), String.valueOf(tid));
	}
	
	public String _listController(int appId, int tid) { 
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(appId), String.valueOf(tid));
	}
}
