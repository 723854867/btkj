package org.btkj.config.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.config.mybatis.dao.ApiDao;
import org.btkj.config.pojo.entity.Api;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.vo.Page;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class ApiMapper extends RedisDBAdapter<String, Api, ApiDao> {
	
	private String CONTROLLER						= "controller:api";					// 基于平台的缓存控制键
	private String ZSET								= "zset:api";			

	public ApiMapper() {
		super(new ByteProtostuffSerializer<Api>(), "hash:db:api");
	}
	
	public Pager<Api> paging(Page page) {
		_checkLoad();
		List<byte[]> list = redis.hpaging(ZSET, redisKey, page.getPage(), page.getPageSize(), RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return Pager.EMPLTY;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Api> apis = new ArrayList<Api>();
		for (byte[] data : list)
			apis.add(serializer.antiConvet(data));
		return new Pager<Api>(total, apis);
	}
	
	private void _checkLoad() {
		if (!checkLoad(CONTROLLER))
			return;
		Map<String, Api> map = dao.getAll();
		if (CollectionUtil.isEmpty(map))
			return;
		flush(map);
	}
	
	@Override
	public void flush(Api entity) {
		redis.hmzset(redisKey, entity, ZSET, Double.valueOf(entity.getCreated()), serializer);
	}
	
	@Override
	public void remove(Api entity) {
		redis.hmzdel(redisKey, entity.key(), ZSET);
	}
	
	@Override
	public void flush(Map<String, Api> entities) {
		Map<String, double[]> zsetParams = new HashMap<String, double[]>();
		Api[] apis = entities.values().toArray(new Api[]{});
		double[] scores = new double[apis.length];
		int idx = 0;
		for (int i = 0, len = apis.length; i < len; i++)
			scores[idx++] = apis[i].getCreated();
		zsetParams.put(ZSET, scores);
		redis.hmzset(redisKey, apis, zsetParams, serializer);
	}
}
