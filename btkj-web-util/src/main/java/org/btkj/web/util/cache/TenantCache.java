package org.btkj.web.util.cache;

import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.cache.Cache;
import org.rapid.util.common.consts.Const;
import org.rapid.util.exception.ConstConvertFailureException;

public class TenantCache extends Cache<Integer, Tenant> {
	
	private static final String NULL_TID						= "0000";
	
	private TenantService tenantService;
	private Map<Integer, Map<Integer, Tenant>> multiTenants;	// 保途租户

	public TenantCache(TenantService tenantService) {
		super(BtkjTables.TENANT.name());
		this.tenantService = tenantService;
	}

	@Override
	public void load() throws Exception {
		Map<Integer, Tenant> temp = tenantService.getTenants();
		Map<Integer, Map<Integer, Tenant>> map = new HashMap<Integer, Map<Integer, Tenant>>();
		for (Tenant tenant : temp.values()) {
			int appId = tenant.getAppId();
			Map<Integer, Tenant> m = map.get(appId);
			if (null == m) {
				m = new HashMap<Integer, Tenant>();
				map.put(appId, m);
			}
			m.put(tenant.getTid(), tenant);
		}
		this.cache = temp;
		this.multiTenants = map;
	}
	
	/**
	 * 获取多租户 app 下的某一个指定的租户
	 * 
	 * @param appId
	 * @param tid
	 * @return
	 */
	public Tenant get(int appId, int tid) {
		Map<Integer, Tenant> map = multiTenants.get(appId);
		return null == map ? null : map.get(tid);
	}
	
	/**
	 * 解析 credential 类型参数时使用
	 * 
	 * @param constant
	 * @param appId
	 * @param tid
	 * @return
	 */
	public Tenant get(Const<?> constant, int appId, String tid) {
		if (tid.equals(NULL_TID))
			return null;
		Tenant tenant = get(appId, Integer.valueOf(tid));
		if (null == tenant)
			throw ConstConvertFailureException.errorConstException(constant);
		return tenant;
	}
	
	/**
	 * 获取多租户 app 的租户个数
	 * 
	 * @param appId
	 * @return
	 */
	public int tenantsNum(int appId) {
		Map<Integer, Tenant> map = multiTenants.get(appId);
		return null == map ? 0 : map.size(); 
	}
}
