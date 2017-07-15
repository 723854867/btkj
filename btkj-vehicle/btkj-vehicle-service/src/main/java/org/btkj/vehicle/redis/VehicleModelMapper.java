package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.entity.VehicleModel;
import org.btkj.vehicle.mybatis.dao.VehicleModelDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class VehicleModelMapper extends RedisDBAdapter<Integer, VehicleModel, VehicleModelDao> {
	
	private final String DEPT_SET							= "set:vehicle_model:dept:{0}";
	private final String CONTROLLER							= "vehicle_model:controller:{0}";

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
		Map<Integer, VehicleModel> map = _checkLoad(deptId);
		if (null != map)
			return new ArrayList<VehicleModel>(map.values());
		List<byte[]> list = redis.hmsget(redisKey, _deptSetKey(deptId));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<VehicleModel> l = new ArrayList<VehicleModel>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private Map<Integer, VehicleModel> _checkLoad(int deptId) {
		if (!checkLoad(_controllerKey(deptId)))
			return null;
		Map<Integer, VehicleModel> map = dao.getByDeptId(deptId);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(VehicleModel entity) {
		redis.hmsset(redisKey, entity, serializer, _deptSetKey(entity.getDeptId()));
	}
	
	@Override
	public void flush(Map<Integer, VehicleModel> entities) {
		Map<Integer, List<VehicleModel>> map = new HashMap<Integer, List<VehicleModel>>();
		for (VehicleModel temp : entities.values()) {
			int tid = temp.getDeptId();
			List<VehicleModel> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<VehicleModel>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<VehicleModel>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _deptSetKey(entry.getKey()));
	}
	
	public String _deptSetKey(int deptId) { 
		return MessageFormat.format(DEPT_SET, String.valueOf(deptId));
	}
	
	public String _controllerKey(int deptId) { 
		return MessageFormat.format(CONTROLLER, String.valueOf(deptId));
	}
}
