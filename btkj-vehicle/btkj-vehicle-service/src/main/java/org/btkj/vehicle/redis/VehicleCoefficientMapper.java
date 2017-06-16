package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.VehicleCoefficientCategory;
import org.btkj.vehicle.mybatis.dao.VehicleCoefficientDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class VehicleCoefficientMapper extends RedisDBAdapter<Long, VehicleCoefficient, VehicleCoefficientDao> {
	
	private String LIST							= "set:vehicle_coefficient:list:{0}";			// 用户 employee 列表：主要是用来记录有多少个代理公司
	private String LIST_CONTROLLER				= "vehicle_coefficient：controller:{0}";
	
	public VehicleCoefficientMapper() {
		super(new ByteProtostuffSerializer<VehicleCoefficient>(), "hash:db:vehicle_coefficient");
	}
	
	public List<VehicleCoefficient> getByTid(int tid, VehicleCoefficientCategory category) {
		List<byte[]> l = redis.hsgetIfMarked(BtkjConsts.CACHE_CONTROLLER_KEY, _listKey(tid), redisKey, _listControllerKey(tid));
		List<VehicleCoefficient> list = null;
		if (null == l) {
			list = dao.getByTid(tid);
			redis.hssetMark(BtkjConsts.CACHE_CONTROLLER_KEY, redisKey, _listKey(tid), _listControllerKey(tid), list, serializer);
		} else {
			list = new ArrayList<VehicleCoefficient>(l.size());
			for (byte[] data : l)
				list.add(serializer.antiConvet(data));
		}
		return list;
	}
	
	@Override
	public void flush(VehicleCoefficient model) {
		redis.hsset(redisKey, model.key(), serializer.convert(model), _listKey(model.getTid()));
	}
	
	public String _listKey(int tid) { 
		return MessageFormat.format(LIST, String.valueOf(tid));
	}
	
	public String _listControllerKey(int tid) { 
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(tid));
	}
}
