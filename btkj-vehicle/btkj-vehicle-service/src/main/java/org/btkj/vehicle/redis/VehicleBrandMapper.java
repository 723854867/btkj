package org.btkj.vehicle.redis;

import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleBrand;
import org.btkj.vehicle.mybatis.dao.VehicleBrandDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class VehicleBrandMapper extends RedisDBAdapter<Integer, VehicleBrand, VehicleBrandDao> {
	
	private String LOAD_LOCK						= "lock:vehicle_brand";							

	public VehicleBrandMapper() {
		super(new ByteProtostuffSerializer<VehicleBrand>(), "hash:db:vehicle_brand");
	}
	
	public List<VehicleBrand> getAll() {
		_checkLoad();
		List<byte[]> list = redis.hvals(redisKey);
		if (CollectionUtil.isEmpty(list))
			return null;
		List<VehicleBrand> brands = new ArrayList<VehicleBrand>(list.size());
		for (byte[] data : list)
			brands.add(serializer.antiConvet(data));
		return brands;
	}
	
	@Override
	public VehicleBrand getByKey(Integer key) {
		_checkLoad();
		return super.getByKey(key);
	}
	
	private void _checkLoad() {
		if (!redis.hsetnx(BtkjConsts.CACHE_CONTROLLER_KEY, LOAD_LOCK, LOAD_LOCK))
			return;
		List<VehicleBrand> list = dao.getAll();
		if (list.isEmpty())
			return;
		flush(list);
	}
}
