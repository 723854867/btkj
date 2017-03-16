package org.btkj.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.api.TenantService;
import org.btkj.user.redis.mapper.AppMapper;
import org.btkj.user.redis.mapper.TenantMapper;
import org.springframework.stereotype.Service;

@Service("tenantService")
public class TenantServiceImpl implements TenantService {
	
	@Resource
	private AppMapper appMapper;
	@Resource
	private TenantMapper tenantMapper;
	
	@Override
	public Map<Integer, App> getApps() {
		return appMapper.getAll();
	}
	
	@Override
	public Map<Integer, Tenant> getTenants() {
		return tenantMapper.getAll();
	}
}
