package org.btkj.bihu.vehicle.service;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuManageService;
import org.btkj.bihu.vehicle.mybatis.EntityGenerator;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.bihu.vehicle.pojo.param.TenantConfigEditParam;
import org.btkj.bihu.vehicle.redis.TenantConfigMapper;
import org.btkj.pojo.BtkjConsts;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
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
	public Result<Void> tenantConfigEdit(TenantConfigEditParam param) {
		switch (param.getType()) {
		case CREATE:
			TenantConfig config = EntityGenerator.newTenantConfig(param.getTid(), param.getAgent(), param.getKey());
			try {
				tenantConfigMapper.insert(config);
				return Consts.RESULT.OK;
			} catch (DuplicateKeyException e) {
				return Consts.RESULT.KEY_DUPLICATED;
			}
		case UPDATE:
			config = tenantConfigMapper.getByKey(param.getTid());
			if (null == config)
				return BtkjConsts.RESULT.BI_HU_TENANT_CONFIG_NOT_EXIST;
			if (StringUtil.hasText(param.getAgent()))
				config.setAgent(param.getAgent());
			if (StringUtil.hasText(param.getKey()))
				config.setKey(param.getKey());
			config.setUpdated(DateUtil.currentTime());
			tenantConfigMapper.update(config);
			return Consts.RESULT.OK;
		case DELETE:
			tenantConfigMapper.delete(param.getTid());
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
