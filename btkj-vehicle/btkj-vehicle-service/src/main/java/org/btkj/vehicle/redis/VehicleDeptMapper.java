package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.po.VehicleDept;
import org.btkj.vehicle.mybatis.dao.VehicleDeptDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class VehicleDeptMapper extends RedisDBAdapter<Integer, VehicleDept, VehicleDeptDao> {
	
	private final String BRAND_SET							= "set:vehicle_dept:brand:{0}";
	private final String BRAND_CONTROLLER					= "vehicle_dept:controller:{0}";

	public VehicleDeptMapper() {
		super(new ByteProtostuffSerializer<VehicleDept>(), "hash:db:vehicle_dept");
	}
	
	/**
	 * 获取指定品牌车系列表
	 * 
	 * @param brandId
	 * @return
	 */
	public List<VehicleDept> getByBrandId(int brandId) {
		Map<Integer, VehicleDept> map = _checkLoad(brandId);
		if (null != map)
			return new ArrayList<VehicleDept>(map.values());
		List<byte[]> list = redis.hmsget(redisKey, _brandSetKey(brandId));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<VehicleDept> l = new ArrayList<VehicleDept>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private Map<Integer, VehicleDept> _checkLoad(int brandId) {
		if (!checkLoad(_brandControllerField(brandId)))
			return null;
		Map<Integer, VehicleDept> map = dao.getByBrandId(brandId);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(VehicleDept entity) {
		redis.hmsset(redisKey, entity, serializer, _brandSetKey(entity.getBrandId()));
	}
	
	@Override
	public void flush(Map<Integer, VehicleDept> entities) {
		Map<Integer, List<VehicleDept>> map = new HashMap<Integer, List<VehicleDept>>();
		for (VehicleDept temp : entities.values()) {
			int tid = temp.getBrandId();
			List<VehicleDept> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<VehicleDept>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<VehicleDept>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _brandSetKey(entry.getKey()));
	}
	
	public String _brandSetKey(int brandId) { 
		return MessageFormat.format(BRAND_SET, String.valueOf(brandId));
	}
	
	public String _brandControllerField(int brandId) { 
		return MessageFormat.format(BRAND_CONTROLLER, String.valueOf(brandId));
	}
}
