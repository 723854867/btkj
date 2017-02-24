package org.btkj.config.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.config.persistence.BtkjTable;
import org.btkj.config.persistence.mapper.AppMapper;
import org.btkj.config.persistence.mapper.InsuranceMapper;
import org.btkj.config.persistence.mapper.RegionCityMapper;
import org.btkj.config.persistence.mapper.RegionDistrictMapper;
import org.btkj.config.persistence.mapper.RegionProvinceMapper;
import org.rapid.redis.Redis;
import org.rapid.util.common.SerializeUtil;
import org.rapid.util.common.db.Entity;
import org.rapid.util.common.db.EntitySerializer;
import org.rapid.util.common.key.Key;

public class RedisService extends RedisKeyGenerator {

	private Redis redis;
	private AppMapper appMapper;
	private InsuranceMapper insuranceMapper;
	private RegionCityMapper regionCityMapper;
	private RegionDistrictMapper regionDistrictMapper;
	private RegionProvinceMapper regionProvinceMapper;
	
	/**
	 * 将数据家载入 redis 中
	 */
	public void preLoading() {
		_store(Key.PROTOSTUFF_ENTITY_SERIALIZER, BtkjTable.APP, appMapper.selectAll());
		_store(Key.PROTOSTUFF_ENTITY_SERIALIZER, BtkjTable.INSURANCE, insuranceMapper.selectAll());
		_store(Key.PROTOSTUFF_ENTITY_SERIALIZER, BtkjTable.REGION_CITY, regionCityMapper.selectAll());
		_store(Key.PROTOSTUFF_ENTITY_SERIALIZER, BtkjTable.REGION_DISTRICT, regionDistrictMapper.selectAll());
		_store(Key.PROTOSTUFF_ENTITY_SERIALIZER, BtkjTable.REGION_PROVINCE, regionProvinceMapper.selectAll());
	}
	
	/**
	 * 缓存数据库数据
	 * 
	 * @param identity 序列化类型
	 * @param table 需要序列表的表
	 * @param entities 序列表实体列表
	 */
	private <KEY, DATA extends Entity<KEY>> void _store(Key<Integer> identity, BtkjTable<KEY, DATA> table, List<DATA> entities) {
		EntitySerializer<KEY, DATA, byte[]> serializer = table.getEntitySerializer(identity);
		String redisKey = table.redisKey();
		Map<byte[], byte[]> data = new HashMap<byte[], byte[]>();
		for (DATA entity : entities) {
			byte[] buffer = serializer.convert(entity);
			data.put(SerializeUtil.RedisUtil.encode(entity.key().toString()), buffer);
		}
		redis.hmset(SerializeUtil.RedisUtil.encode(redisKey), data);
	}
	
	public void setRedis(Redis redis) {
		this.redis = redis;
	}
	
	public void setAppMapper(AppMapper appMapper) {
		this.appMapper = appMapper;
	}
	
	public void setInsuranceMapper(InsuranceMapper insuranceMapper) {
		this.insuranceMapper = insuranceMapper;
	}
	
	public void setRegionCityMapper(RegionCityMapper regionCityMapper) {
		this.regionCityMapper = regionCityMapper;
	}
	
	public void setRegionDistrictMapper(RegionDistrictMapper regionDistrictMapper) {
		this.regionDistrictMapper = regionDistrictMapper;
	}
	
	public void setRegionProvinceMapper(RegionProvinceMapper regionProvinceMapper) {
		this.regionProvinceMapper = regionProvinceMapper;
	}
}
