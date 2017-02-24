package org.btkj.config.persistence;

import org.btkj.config.pojo.entity.App;
import org.btkj.config.pojo.entity.Insurance;
import org.btkj.config.pojo.entity.RegionCity;
import org.btkj.config.pojo.entity.RegionDistrict;
import org.btkj.config.pojo.entity.RegionProvince;
import org.btkj.config.redis.RedisKeyGenerator;
import org.rapid.util.common.db.Entity;
import org.rapid.util.common.db.Table;

public abstract class BtkjTable<KEY, DATA extends Entity<KEY>> extends Table<KEY, DATA> {
	
	private BtkjTable(String key) {
		super(key);
	}
	
	public static final BtkjTable<Integer, App> APP							= new BtkjTable<Integer, App>("app") {
		@Override
		protected void initSerializers() {
			addSerializer(Serializers.APP_PROTOSTUFF);
		}
		@Override
		public String redisKey() {
			return RedisKeyGenerator.appDataKey();
		}
	};
	
	public static final BtkjTable<Integer, Insurance> INSURANCE				= new BtkjTable<Integer, Insurance>("insurance") {
		@Override
		protected void initSerializers() {
			addSerializer(Serializers.INSURANCE_PROTOSTUFF);
		}
		@Override
		public String redisKey() {
			return RedisKeyGenerator.insuranceDataKey();
		}
	};
	
	public static final BtkjTable<Integer, RegionCity> REGION_CITY			= new BtkjTable<Integer, RegionCity>("region_city") {
		@Override
		protected void initSerializers() {
			addSerializer(Serializers.REGION_CITY_PROTOSTUFF);
		}
		@Override
		public String redisKey() {
			return RedisKeyGenerator.regionCityDataKey();
		}
	};
	
	public static final BtkjTable<Integer, RegionDistrict> REGION_DISTRICT	= new BtkjTable<Integer, RegionDistrict>("region_district") {
		@Override
		protected void initSerializers() {
			addSerializer(Serializers.REGION_DISTRICT_PROTOSTUFF);
		}
		@Override
		public String redisKey() {
			return RedisKeyGenerator.regionDistrictDataKey();
		}
	};
	
	public static final BtkjTable<Integer, RegionProvince> REGION_PROVINCE	= new BtkjTable<Integer, RegionProvince>("region_province") {
		@Override
		protected void initSerializers() {
			addSerializer(Serializers.REGION_PROVINCE_PROTOSTUFF);
		}
		@Override
		public String redisKey() {
			return RedisKeyGenerator.regionProvinceDataKey();
		}
	};
}
