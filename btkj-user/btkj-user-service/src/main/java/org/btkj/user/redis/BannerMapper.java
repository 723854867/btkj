package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.mybatis.dao.BannerDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.key.Pair;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class BannerMapper extends RedisDBAdapter<Integer, Banner, BannerDao> {
	
	private final String APP_TENANT_SET				= "set:banner:app:{0}:tenant:{1}";
	private final String APP_TENANT_CONTROLLER		= "banner：controller:{0}:{1}";			// 基于平台和商户的缓存控制键：因为 tid = 0是属于平台的banner因此还需要一个 appId 才行

	public BannerMapper() {
		super(new ByteProtostuffSerializer<Banner>(), "hash:db:banner");
	}
	
	public List<Banner> getByAppIdAndTid(int appId, int tid) {
		Map<Integer, Banner> map = _checkLoad(appId, tid);
		if (null == map) {
			List<byte[]> list = redis.hmsget(redisKey, _appTenantSetKey(appId, tid));
			map = new HashMap<Integer, Banner>();
			if (null != list) {
				for (byte[] buffer : list) {
					Banner banner = serializer.antiConvet(buffer);
					map.put(banner.getId(), banner);
				}
			}
		}
		if (0 != tid && map.size() < GlobalConfigContainer.getGlobalConfig().getBannerNum()) {
			List<Banner> temp = getByAppIdAndTid(appId, 0);
			int idx = Math.min(GlobalConfigContainer.getGlobalConfig().getBannerNum() - map.size(), temp.size());
			for (int i = 0; i < idx; i++)
				map.put(temp.get(i).getId(), temp.get(i));
		}
		return new ArrayList<Banner>(map.values());
	}
	
	private Map<Integer, Banner> _checkLoad(int appId, int tid) {
		if (!checkLoad(_appTenantControllerField(appId, tid)))
			return null;
		Map<Integer, Banner> map = dao.getByAppIdAndTid(appId, tid);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(Banner entity) {
		redis.hmsset(redisKey, entity, serializer, _appTenantControllerField(entity.getAppId(), entity.getTid()));
	}
	
	@Override
	public void flush(Map<Integer, Banner> entities) {
		Map<Pair<Integer, Integer>, List<Banner>> map = new HashMap<Pair<Integer, Integer>, List<Banner>>();
		for (Banner temp : entities.values()) {
			Pair<Integer, Integer> key = new Pair<Integer, Integer>(temp.getAppId(), temp.getTid());
			List<Banner> list = map.get(key);
			if (null == list) {
				list = new ArrayList<Banner>();
				map.put(key, list);
			}
			list.add(temp);
		}
		
		for (Entry<Pair<Integer, Integer>, List<Banner>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _appTenantSetKey(entry.getKey().getFirst(), entry.getKey().getSecond()));
	}
	
	public String _appTenantSetKey(int appId, int tid) { 
		return MessageFormat.format(APP_TENANT_SET, String.valueOf(appId), String.valueOf(tid));
	}
	
	public String _appTenantControllerField(int appId, int tid) { 
		return MessageFormat.format(APP_TENANT_CONTROLLER, String.valueOf(appId), String.valueOf(tid));
	}
}
