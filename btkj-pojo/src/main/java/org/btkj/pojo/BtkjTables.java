package org.btkj.pojo;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Insurance;
import org.btkj.pojo.entity.RegionCity;
import org.btkj.pojo.entity.RegionDistrict;
import org.btkj.pojo.entity.RegionProvince;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.entity.UserRelation;
import org.rapid.data.storage.redis.RedisTable;
import org.rapid.util.common.key.Pair;
import org.rapid.util.lang.DateUtils;

public interface BtkjTables {
	 
	// ******************************* config tables *******************************
	final RedisTable<Integer, Insurance> INSURANCE				= new RedisTable<Integer, Insurance>("insurance", Insurance.class);
	final RedisTable<Integer, RegionCity> REGION_CITY			= new RedisTable<Integer, RegionCity>("region_city", RegionCity.class);
	final RedisTable<Integer, RegionDistrict> REGION_DISTRICT	= new RedisTable<Integer, RegionDistrict>("region_district", RegionDistrict.class);
	final RedisTable<Integer, RegionProvince> REGION_PROVINCE	= new RedisTable<Integer, RegionProvince>("region_province", RegionProvince.class);

	// ******************************* user tables *******************************
	final RedisTable<Integer, User> USER									= new RedisTable<Integer, User>("user", User.class);
	final RedisTable<Integer, App> APP										= new RedisTable<Integer, App>("app", App.class);
	final RedisTable<Integer, Tenant> TENANT								= new RedisTable<Integer, Tenant>("tenant", Tenant.class);
	final RedisTable<Pair<Integer, Integer>, UserRelation> USER_RELATION	= new RedisTable<Pair<Integer, Integer>, UserRelation>("user_relation", UserRelation.class, DateUtils.MILLIS_DAY);
}
