package org.btkj.bihu.vehicle.service;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuManageService;
import org.btkj.bihu.vehicle.mybatis.EntityGenerator;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.bihu.vehicle.redis.TenantConfigMapper;
import org.btkj.pojo.BtkjConsts;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("biHuManageService")
public class BiHuManageServiceImpl implements BiHuManageService {
	
	@Resource
	private TenantConfigMapper tenantConfigMapper;
	
	@Override
	public TenantConfig tenantConfig(int tid) {
		return tenantConfigMapper.getByKey(tid);
	}
	
	@Override
	public Result<Void> tenantConfigAdd(int tid, String agent, String key) {
		TenantConfig config = EntityGenerator.newTenantConfig(tid, agent, key);
		try {
			tenantConfigMapper.insert(config);
			return Consts.RESULT.OK;
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
	}
	
	@Override
	public Result<Void> tenantConfigUpdate(int tid, String agent, String key) {
		TenantConfig config = tenantConfigMapper.getByKey(tid);
		if (null == config)
			return BtkjConsts.RESULT.BI_HU_TENANT_CONFIG_NOT_EXIST;
		config.setAgent(agent);
		config.setKey(key);
		config.setUpdated(DateUtil.currentTime());
		tenantConfigMapper.update(config);
		return Consts.RESULT.OK;
	}
	
	@Override
	public void tenantConfigDelete(int tid) {
		tenantConfigMapper.delete(tid);
	}
}