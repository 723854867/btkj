package org.btkj.bihu.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuConfigService;
import org.btkj.bihu.vehicle.mybatis.EntityGenerator;
import org.btkj.bihu.vehicle.pojo.entity.BiHuArea;
import org.btkj.bihu.vehicle.pojo.entity.BiHuInsurer;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.bihu.vehicle.redis.BiHuAreaMapper;
import org.btkj.bihu.vehicle.redis.BiHuInsurerMapper;
import org.btkj.bihu.vehicle.redis.TenantConfigMapper;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("biHuConfigService")
public class BiHuConfigServiceImpl implements BiHuConfigService {
	
	@Resource
	private BiHuAreaMapper biHuAreaMapper;
	@Resource
	private BiHuInsurerMapper biHuInsurerMapper;
	@Resource
	private TenantConfigMapper tenantConfigMapper;

	@Override
	public List<BiHuArea> cities() {
		return biHuAreaMapper.getAll();
	}
	
	@Override
	public void addBiHuCity(int cid, Region region, String name) {
		BiHuArea city = EntityGenerator.newCity(region, cid, name);
		biHuAreaMapper.insert(city);
	}
	
	@Override
	public void deleteBiHuCity(int code) {
		biHuAreaMapper.delete(code);
	}
	
	@Override
	public List<BiHuInsurer> insurers() {
		return biHuInsurerMapper.getAll();
	}
	
	@Override
	public void addBiHuInsurer(int code, String name) {
		BiHuInsurer insurer = EntityGenerator.newInsurer(code, name);
		biHuInsurerMapper.insert(insurer);
	}
	
	@Override
	public void deleteBiHuInsurer(int id) {
		biHuInsurerMapper.delete(id);
	}
	
	@Override
	public Result<Pager<TenantConfig>> tenantConfigs(int page, int pageSize) {
		return tenantConfigMapper.paging(page, pageSize);
	}
	
	@Override
	public void addTenantConfig(int tid, String agent, String key) {
		TenantConfig config = EntityGenerator.newTenantConfig(tid, agent, key);
		tenantConfigMapper.insert(config);
	}
	
	@Override
	public void deleteTenantConfig(int tid) {
		tenantConfigMapper.delete(tid);
	}
}
