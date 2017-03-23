package org.btkj.config.service;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.config.redis.mapper.InsurerMapper;
import org.btkj.config.redis.mapper.RegionCityMapper;
import org.btkj.config.redis.mapper.RegionDistrictMapper;
import org.btkj.config.redis.mapper.RegionProvinceMapper;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.RegionCity;
import org.btkj.pojo.entity.RegionDistrict;
import org.btkj.pojo.entity.RegionProvince;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private InsurerMapper insuranceMapper;
	@Resource
	private RegionCityMapper regionCityMapper;
	@Resource
	private RegionDistrictMapper regionDistrictMapper;
	@Resource
	private RegionProvinceMapper regionProvinceMapper;

	@Override
	public Map<Integer, Insurer> getInsurances() {
		return insuranceMapper.getAll();
	}

	@Override
	public Map<Integer, RegionCity> getRegionCities() {
		return regionCityMapper.getAll();
	}

	@Override
	public Map<Integer, RegionDistrict> getRegionDistricts() {
		return regionDistrictMapper.getAll();
	}

	@Override
	public Map<Integer, RegionProvince> getRegionProvinces() {
		return regionProvinceMapper.getAll();
	}
}
