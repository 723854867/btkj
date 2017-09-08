package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.entity.vehicle.PoundageCoefficientRange;
import org.btkj.vehicle.mybatis.dao.PoundageCoefficientRangeDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.Consts;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class PoundageCoefficientRangeMapper extends RedisDBAdapter<Integer, PoundageCoefficientRange, PoundageCoefficientRangeDao> {

	private final String SET								= "set:pcr:{0}:{1}";
	private final String CONTROLLER							= "controller:pcr:{0}:{1}";
	
	public PoundageCoefficientRangeMapper() {
		super(new ByteProtostuffSerializer<PoundageCoefficientRange>(), "hash:db:poundage_coefficient_range");
	}
	
	public Map<Integer, PoundageCoefficientRange> ranges(int tid, int cfgCoefficientId) {
		Map<Integer, PoundageCoefficientRange> map = _checkLoad(tid, cfgCoefficientId);
		if (null != map)
			return map;
		List<byte[]> list = redis.hmsget(redisKey, _setKey(tid, cfgCoefficientId));
		if (null == list)
			return CollectionUtil.emptyHashMap();
		map = new HashMap<Integer, PoundageCoefficientRange>();
		for (byte[] buffer : list) {
			PoundageCoefficientRange temp = serializer.antiConvet(buffer);
			map.put(temp.getId(), temp);
		}
		return map;
	}
	
	private Map<Integer, PoundageCoefficientRange> _checkLoad(int tid, int cfgCoefficientId) {
		if (!checkLoad(_controllerField(tid, cfgCoefficientId)))
			return null;
		Map<Integer, PoundageCoefficientRange> map = dao.getByTidAndCfgCoefficientId(tid, cfgCoefficientId);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(PoundageCoefficientRange entity) {
		redis.hmsset(redisKey, entity, serializer, _setKey(entity.getTid(), entity.getCfgCoefficientId()));
	}
	
	@Override
	public void remove(PoundageCoefficientRange entity) {
		redis.hmsdel(redisKey, entity.key(), _setKey(entity.getTid(), entity.getCfgCoefficientId()));
	}
	
	@Override
	public void flush(Map<Integer, PoundageCoefficientRange> entities) {
		Map<String, List<PoundageCoefficientRange>> map = new HashMap<String, List<PoundageCoefficientRange>>();
		for (PoundageCoefficientRange temp : entities.values()) {
			String key = temp.getTid() + Consts.SYMBOL_UNDERLINE + temp.getCfgCoefficientId();
			List<PoundageCoefficientRange> list = map.get(key);
			if (null == list) {
				list = new ArrayList<PoundageCoefficientRange>();
				map.put(key, list);
			}
			list.add(temp);
		}
		for (Entry<String, List<PoundageCoefficientRange>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _setKey(entry.getValue().get(0).getTid(), entry.getValue().get(0).getCfgCoefficientId()));
	}
	
	private String _setKey(int tid, int cfgCoefficientId) {
		return MessageFormat.format(SET, String.valueOf(tid), String.valueOf(cfgCoefficientId));
	}
	
	private String _controllerField(int tid, int cfgCoefficientId) {
		return MessageFormat.format(CONTROLLER, String.valueOf(tid), String.valueOf(cfgCoefficientId));
	}
}
