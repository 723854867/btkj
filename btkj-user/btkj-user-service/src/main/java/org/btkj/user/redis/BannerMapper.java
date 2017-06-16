package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.mybatis.dao.BannerDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class BannerMapper extends RedisDBAdapter<Integer, Banner, BannerDao> {
	
	private String LIST							= "set:banner:list:{0}:{1}";	// 租户 banner 列表
	private String LIST_CONTROLLER				= "banner：controller:{0}：{1}";

	public BannerMapper() {
		super(new ByteProtostuffSerializer<Banner>(), "hash:db:banner");
	}
	
	public List<Banner> getByAppIdAndTid(int appId, int tid) {
		String listKey = _listKey(appId, tid);
		List<byte[]> list = redis.hsgetIfMarked(BtkjConsts.CACHE_CONTROLLER_KEY, listKey, redisKey, _listController(appId, tid));
		List<Banner> banners = null;
		if (null == list) {
			banners = dao.getByAppIdAndTid(appId, tid);
			redis.hssetMark(BtkjConsts.CACHE_CONTROLLER_KEY, redisKey, _listKey(appId, tid), _listController(appId, tid), banners, serializer);
		} else {
			banners = new ArrayList<Banner>(list.size());
			for (byte[] data : list)
				banners.add(serializer.antiConvet(data));
		}
		if (0 == tid) 			// 如果获取的是 app 的 banner 则直接返回
			return banners;
		// 如果获取的是代理商的 banner，则需要从 app 中填补剩下的 banner 位数
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
		redis.hsset(redisKey, entity.key(), serializer.convert(entity), _listKey(entity.getAppId(), entity.getTid()));
	}
	
	public String _listKey(int appId, int tid) { 
		return MessageFormat.format(LIST, String.valueOf(appId), String.valueOf(tid));
	}
	
	public String _listController(int appId, int tid) { 
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(appId), String.valueOf(tid));
	}
}
