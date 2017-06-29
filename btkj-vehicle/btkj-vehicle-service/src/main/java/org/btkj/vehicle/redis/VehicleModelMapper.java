package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.vehicle.mybatis.dao.VehicleModelDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class VehicleModelMapper extends RedisDBAdapter<Integer, VehicleModel, VehicleModelDao> {
	
	private static final String SET						= "set:vehicle_model:{0}";			
	private static final String SET_CONTROLLER			= "controller:vehicle_model:{0}";

	public VehicleModelMapper() {
		super(new ByteProtostuffSerializer<VehicleModel>(), "hash:db:vehicle_model");
	}
	
	/**
	 * 获取指定车系下的所有厂牌型号
	 * 
	 * @param deptId
	 * @return
	 */
	public List<VehicleModel> getByDeptId(int deptId) {
		List<byte[]> list = redis.hsgetIfMarked(BtkjConsts.CACHE_CONTROLLER_KEY, _setKey(deptId), redisKey, _setControllerKey(deptId));
		List<VehicleModel> models = null;
		if (null == list) {
			models = dao.getByDeptId(deptId);
			redis.hssetMark(BtkjConsts.CACHE_CONTROLLER_KEY, redisKey, _setKey(deptId), _setControllerKey(deptId), models, serializer);
		} else {
			models = new ArrayList<VehicleModel>(list.size());
			for (byte[] data : list)
				models.add(serializer.antiConvet(data));
		}
		return models;
	}
	
	public String _setKey(int deptId) { 
		return MessageFormat.format(SET, String.valueOf(deptId));
	}
	
	public String _setControllerKey(int deptId) { 
		return MessageFormat.format(SET_CONTROLLER, String.valueOf(deptId));
	}
}
