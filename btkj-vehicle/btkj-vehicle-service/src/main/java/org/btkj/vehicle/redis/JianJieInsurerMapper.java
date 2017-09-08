package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.vehicle.JianJieInsurer;
import org.btkj.vehicle.mybatis.dao.JianJieInsurerDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class JianJieInsurerMapper extends RedisDBAdapter<Integer, JianJieInsurer, JianJieInsurerDao> {
	
	private final String SET								= "set:tenant:jian_jie_insurer:{0}";
	private final String CONTROLLER							= "controller:tenant:jian_jie_insurer:{0}";
	
	private ConfigService configService;

	public JianJieInsurerMapper() {
		super(new ByteProtostuffSerializer<JianJieInsurer>(), "hash:db:jian_jie_insurer");
	}
	
	public JianJieInsurer getByCompanyId(int tid, int companyId) {
		Map<Integer, JianJieInsurer> map = getByTid(tid);
		for (JianJieInsurer insurer : map.values()) {
			if (insurer.getCompanyId() == companyId)
				return insurer;
		}
		return null;
	}
	
	public Map<Integer, JianJieInsurer> getByTid(int tid) {
		Map<Integer, JianJieInsurer> map = _checkLoad(tid);
		if (null != map)
			return map;
		List<byte[]> list = redis.hmsget(redisKey, _setKey(tid));
		if (null == list)
			return CollectionUtil.emptyHashMap();
		map = new HashMap<Integer, JianJieInsurer>();
		for (byte[] buffer : list) {
			JianJieInsurer temp = serializer.antiConvet(buffer);
			map.put(temp.key(), temp);
		}
		return map;
	}
	
	private Map<Integer, JianJieInsurer> _checkLoad(int tid) {
		if (!checkLoad(_controllerField(tid)))
			return null;
		Map<Integer, JianJieInsurer> map = dao.getByTid(tid);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(JianJieInsurer entity) {
		redis.hmsset(redisKey, entity, serializer, _setKey(entity.getTid()));
	}
	
	@Override
	public void remove(JianJieInsurer entity) {
		redis.hmsdel(redisKey, entity.key(), _setKey(entity.getTid()));
	}
	
	@Override
	public void flush(Map<Integer, JianJieInsurer> entities) {
		Map<Integer, List<JianJieInsurer>> map = new HashMap<Integer, List<JianJieInsurer>>();
		for (JianJieInsurer temp : entities.values()) {
			int tid = temp.getTid();
			List<JianJieInsurer> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<JianJieInsurer>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<JianJieInsurer>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _setKey(entry.getKey()));
	}
	
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
	
	private String _setKey(int tid) {
		return MessageFormat.format(SET, String.valueOf(tid));
	}
	
	private String _controllerField(int tid) {
		return MessageFormat.format(CONTROLLER, String.valueOf(tid));
	}
}
