package org.btkj.config.persistence;

import org.btkj.config.pojo.entity.App;
import org.btkj.config.pojo.entity.Insurance;
import org.btkj.config.pojo.entity.RegionCity;
import org.btkj.config.pojo.entity.RegionDistrict;
import org.btkj.config.pojo.entity.RegionProvince;
import org.rapid.util.common.db.ProtostuffEntitySerializer;

public interface Serializers {

	final ProtostuffEntitySerializer<Integer, App> APP_PROTOSTUFF							= new ProtostuffEntitySerializer<Integer, App>();
	final ProtostuffEntitySerializer<Integer, Insurance> INSURANCE_PROTOSTUFF				= new ProtostuffEntitySerializer<Integer, Insurance>();
	final ProtostuffEntitySerializer<Integer, RegionCity> REGION_CITY_PROTOSTUFF			= new ProtostuffEntitySerializer<Integer, RegionCity>();
	final ProtostuffEntitySerializer<Integer, RegionDistrict> REGION_DISTRICT_PROTOSTUFF	= new ProtostuffEntitySerializer<Integer, RegionDistrict>();
	final ProtostuffEntitySerializer<Integer, RegionProvince> REGION_PROVINCE_PROTOSTUFF	= new ProtostuffEntitySerializer<Integer, RegionProvince>();
}
