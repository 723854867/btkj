package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleDept;
import org.btkj.vehicle.mybatis.dao.VehicleDeptDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class VehicleDeptMapper extends RedisDBAdapter<Integer, VehicleDept, VehicleDeptDao> {
	
	private static final String SET						= "set:vehicle_dept:{0}";			
	private static final String SET_CONTROLLER			= "controller:vehicle_dept:{0}";

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
		List<byte[]> list = redis.hsgetIfMarked(BtkjConsts.CACHE_CONTROLLER_KEY, _setKey(brandId), redisKey, _setControllerKey(brandId));
		List<VehicleDept> depts = null;
		if (null == list) {
			depts = dao.getByBrandId(brandId);
			redis.hssetMark(BtkjConsts.CACHE_CONTROLLER_KEY, redisKey, _setKey(brandId), _setControllerKey(brandId), depts, serializer);
		} else {
			depts = new ArrayList<VehicleDept>(list.size());
			for (byte[] data : list)
				depts.add(serializer.antiConvet(data));
		}
		return depts;
	}
	
	public String _setKey(int brandId) { 
		return MessageFormat.format(SET, String.valueOf(brandId));
	}
	
	public String _setControllerKey(int brandId) { 
		return MessageFormat.format(SET_CONTROLLER, String.valueOf(brandId));
	}
}
