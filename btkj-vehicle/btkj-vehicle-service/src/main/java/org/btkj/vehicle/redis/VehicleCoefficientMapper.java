package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.vehicle.mybatis.dao.VehicleCoefficientDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class VehicleCoefficientMapper extends RedisDBAdapter<Integer, VehicleCoefficient, VehicleCoefficientDao> {
	
	private final String TENANT_SET							= "set:vehicle_coeeficient:tenant:{0}";
	private final String TENANT_CONTROLLER					= "vehicle_coeeficient:controller:{0}";
	
	public VehicleCoefficientMapper() {
		super(new ByteProtostuffSerializer<VehicleCoefficient>(), "hash:db:vehicle_coefficient");
	}
	
	public List<VehicleCoefficient> getByTid(int tid) {
		Map<Integer, VehicleCoefficient> map = _checkLoad(tid);
		if (null != map)
			return new ArrayList<VehicleCoefficient>(map.values());
		List<byte[]> list = redis.hmsget(redisKey, _tenantSetKey(tid));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<VehicleCoefficient> l = new ArrayList<VehicleCoefficient>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	public List<VehicleCoefficient> getByTidAndType(int tid, CoefficientType type) {
		List<VehicleCoefficient> l = getByTid(tid);
		Iterator<VehicleCoefficient> itr = l.iterator();
		while (itr.hasNext()) {
			VehicleCoefficient coefficient = itr.next();
			if (coefficient.getType() == type.mark())
				continue;
			itr.remove();
		}
		return l;
	}
	
	private Map<Integer, VehicleCoefficient> _checkLoad(int tid) {
		if (!checkLoad(_tenantControllerField(tid)))
			return null;
		Map<Integer, VehicleCoefficient> map = dao.getByTid(tid);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(VehicleCoefficient entity) {
		redis.hmsset(redisKey, entity, serializer, _tenantSetKey(entity.getTid()));
	}
	
	@Override
	public void remove(VehicleCoefficient entity) {
		redis.hmsdel(redisKey, entity.key(), _tenantSetKey(entity.getTid()));
	}
	
	@Override
	public void flush(Map<Integer, VehicleCoefficient> entities) {
		Map<Integer, List<VehicleCoefficient>> map = new HashMap<Integer, List<VehicleCoefficient>>();
		for (VehicleCoefficient temp : entities.values()) {
			int tid = temp.getTid();
			List<VehicleCoefficient> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<VehicleCoefficient>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<VehicleCoefficient>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _tenantSetKey(entry.getKey()));
	}
	
	private String _tenantSetKey(int tid) {
		return MessageFormat.format(TENANT_SET, String.valueOf(tid));
	}
	
	private String _tenantControllerField(int tid) {
		return MessageFormat.format(TENANT_CONTROLLER, String.valueOf(tid));
	}
}
