package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.Insurer;
import org.btkj.vehicle.EntityGenerator;
import org.btkj.vehicle.mybatis.dao.TenantInsurerDao;
import org.btkj.vehicle.pojo.entity.TenantInsurer;
import org.btkj.vehicle.pojo.enums.Lane;
import org.btkj.vehicle.pojo.param.TenantSetParam;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;

public class TenantInsurerMapper extends RedisDBAdapter<String, TenantInsurer, TenantInsurerDao> {
	
	private final String TENANT_SET							= "set:tenant:insurer:{0}";
	private final String CONTROLLER							= "tenant:insurer:controller:{0}";
	
	private ConfigService configService;
	
	public TenantInsurerMapper() {
		super(new ByteProtostuffSerializer<TenantInsurer>(), "hash:db:tenant_insurer");
	}
	
	public Map<String, TenantInsurer> getByTid(int tid) {
		Map<String, TenantInsurer> map = _checkLoad(tid);
		if (null != map)
			return map;
		List<byte[]> list = redis.hmsget(redisKey, _tenantSetKey(tid));
		if (null == list)
			return CollectionUtil.emptyHashMap();
		map = new HashMap<String, TenantInsurer>();
		for (byte[] buffer : list) {
			TenantInsurer temp = serializer.antiConvet(buffer);
			map.put(temp.getKey(), temp);
		}
		return map;
	}
	
	private Map<String, TenantInsurer> _checkLoad(int tid) {
		if (!checkLoad(_controllerField(tid)))
			return null;
		Map<String, TenantInsurer> map = dao.getByTid(tid);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(TenantInsurer entity) {
		redis.hmsset(redisKey, entity, serializer, _tenantSetKey(entity.getTid()));
	}
	
	@Override
	public void remove(TenantInsurer entity) {
		redis.hmsdel(redisKey, entity.key(), _tenantSetKey(entity.getTid()));
	}
	
	public void insurerEdit(TenantSetParam param) {
		if (!CollectionUtil.isEmpty(param.getInsurersDelete())) {
			redis.hmsdel(redisKey, param.getInsurersDelete(), _tenantSetKey(param.getTid()));
			dao.deleteByKeys(param.getInsurersDelete());
		}
		int time = DateUtil.currentTime();
		Map<String, TenantInsurer> insurers = null;
		if (!CollectionUtil.isEmpty(param.getInsurersUpdate())) {
			insurers = getByKeys(new HashSet<String>(param.getInsurersUpdate().keySet()));
			for (TenantInsurer insurer : insurers.values()) {
				TenantInsurer update = param.getInsurersUpdate().get(insurer.getKey());
				if (update.getLane() != insurer.getLane() && null != Lane.match(update.getLane())) 
					insurer.setLane(update.getLane());
				insurer.setUpdated(time);
			}
		}
		if (!CollectionUtil.isEmpty(param.getInsurersInsert())) {
			if (null == insurers)
				insurers = new HashMap<String, TenantInsurer>();
			Set<Integer> set = new HashSet<Integer>();
			for (TenantInsurer insert : param.getInsurersInsert().values())
				set.add(insert.getInsurerId());
			Map<Integer, Insurer> map = configService.insurers(set);
			for (TenantInsurer insert : param.getInsurersInsert().values()) {
				Insurer insurer = map.get(insert.getInsurerId());
				if (null == insurer)
					continue;
				Lane lane = Lane.match(insert.getLane());
				if (null == lane)
					continue;
				TenantInsurer ti = EntityGenerator.newTenantInsurer(param.getTid(), insert.getInsurerId(), lane, time);
				insurers.put(ti.getKey(), ti);
			}
		}
		if (!CollectionUtil.isEmpty(insurers)) {
			dao.replace(insurers.values());
			flush(insurers);
		}
	}
	
	@Override
	public void flush(Map<String, TenantInsurer> entities) {
		Map<Integer, List<TenantInsurer>> map = new HashMap<Integer, List<TenantInsurer>>();
		for (TenantInsurer temp : entities.values()) {
			int tid = temp.getTid();
			List<TenantInsurer> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<TenantInsurer>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<TenantInsurer>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _tenantSetKey(entry.getKey()));
	}
	
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
	
	private String _tenantSetKey(int tid) {
		return MessageFormat.format(TENANT_SET, String.valueOf(tid));
	}
	
	private String _controllerField(int tid) {
		return MessageFormat.format(CONTROLLER, String.valueOf(tid));
	}
}
