package org.btkj.master.redis;

import java.text.MessageFormat;

import org.btkj.master.MasterLuaCmd;
import org.btkj.pojo.model.TenantAddApply;
import org.rapid.data.storage.mapper.RedisProtostuffMemoryMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class TenantAddApplyMapper extends RedisProtostuffMemoryMapper<String, TenantAddApply> {
	
	private String PLATFORM_SET					= "zset:tenant:add:apply:platform";			 // 全平台的所有已提交的申请
	private String APP_COMMITTED_SET			= "zset:tenant:add:apply:app:{0}:committed"; // app 的所有已提交的申请
	private String APP_PROCESSED_SET			= "zset:tenant:add:apply:app:{0}:processed"; // app 的所有已经处理过的申请

	public TenantAddApplyMapper() {
		super("hash:memory:tenantAddApply");
	}

	@Override
	public TenantAddApply insert(TenantAddApply model) {
		return redis.invokeLua(MasterLuaCmd.TENANT_ADD_APPLY_FLUSH, 
				SerializeUtil.RedisUtil.encode(
						redisKey, PLATFORM_SET, 
						_appCommittedSetKey(model.getAppId()),
						_appProcessedSetKey(model.getAppId()),
						model.key(), serial(model), System.currentTimeMillis()));
	}
	
	private String _appCommittedSetKey(int appId) { 
		return MessageFormat.format(APP_COMMITTED_SET, String.valueOf(appId));
	}
	
	private String _appProcessedSetKey(int appId) { 
		return MessageFormat.format(APP_PROCESSED_SET, String.valueOf(appId));
	}
}
