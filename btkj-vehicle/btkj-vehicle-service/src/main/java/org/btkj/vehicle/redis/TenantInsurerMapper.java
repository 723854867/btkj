package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.vehicle.TenantInsurer;
import org.btkj.vehicle.mybatis.dao.TenantInsurerDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class TenantInsurerMapper extends RedisDBAdapter<String, TenantInsurer, TenantInsurerDao> {
	
	private final String TENANT_SET							= "set:tenant:insurer:{0}";
	private final String CONTROLLER							= "tenant:insurer:controller:{0}";
	
	private ConfigService configService;
	
	public TenantInsurerMapper() {
		super(new ByteProtostuffSerializer<TenantInsurer>(), "hash:db:tenant_insurer");
	}
	
	public TenantInsurer getByTidAndJianJieId(int tid, int jianJieId) {
		if (0 == jianJieId)
			return null;
		Map<String, TenantInsurer> map = getByTid(tid);
		if (CollectionUtil.isEmpty(map))
			return null;
		for (TenantInsurer insurer : map.values()) {
			if (insurer.getJianJieId() == jianJieId)
				return insurer;
		}
		return null;
	}
	
	public TenantInsurer getByTidAndInsurerId(int tid, int insurerId) {
		Map<String, TenantInsurer> map = getByTid(tid);
		if (CollectionUtil.isEmpty(map))
			return null;
		for (TenantInsurer insurer : map.values()) {
			if (insurer.getInsurerId() == insurerId)
				return insurer;
		}
		return null;
	}
	
	public Map<String, TenantInsurer> getByTidAndMinor(int tid, boolean minor) {
		Map<String, TenantInsurer> map = getByTid(tid);
		if (CollectionUtil.isEmpty(map))
			return Collections.EMPTY_MAP;
		Set<Integer> set = new HashSet<Integer>();
		for (TenantInsurer insurer : map.values()) 
			set.add(insurer.getInsurerId());
		Map<Integer, Insurer> insurers = configService.insurers(set);
		Map<String, TenantInsurer> temp = new HashMap<String, TenantInsurer>();
		for (TenantInsurer tenantInsurer : map.values()) {
			Insurer insurer = insurers.get(tenantInsurer.getInsurerId());
			if (!(minor ^ insurer.isMinor()))
				temp.put(tenantInsurer.getKey(), tenantInsurer);
		}
		return temp;
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
