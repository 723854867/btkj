package org.btkj.common;

import org.btkj.common.cache.impl.TenantCache;
import org.btkj.common.web.Beans;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.Credential;
import org.rapid.util.exception.ConstConvertFailureException;

public class InternalUtils {

	/**
	 * 根据识别码识别出 app、tenant、user信息：识别码的识别规则是：(appId)(tenantId)(uid)
	 * 其中 appId 固定为三位，tenantId 固定为四位，uid 为正数，appId 必须要传，tenantId 如果
	 * 不需要则固定为 "0000"，uid 可选
	 * 
	 * @param code
	 * @return
	 */
	public static final Credential parse(String code) {
		// 现获取 app
		int appId = Integer.valueOf(code.substring(0, BtkjConsts.APP_ID_BIT_NUM));
		App app = Beans.cacheService.getById(BtkjTables.APP.name(), appId);
		
		// 在获取 tenant
		Tenant tenant = null;
		int index = BtkjConsts.APP_ID_BIT_NUM;
		if (!BtkjUtil.isBaoTuApp(app)) 
			tenant = _getIsolateTenant(app.getId());
		else {
			if (code.length() == index)
				return new Credential(app);
			tenant = _getBaoTuTenant(code);
			index += BtkjConsts.TENANT_ID_BIT_NUM;
		}
		if (code.length() == index)
			return new Credential(app, tenant);
		
		User user = Beans.userService.getUser(Integer.valueOf(code.substring(index)));
		if (user.getAppId() != app.getId())
			throw new RuntimeException();
		Employee employee = null;
		if (null != tenant) {
			employee = Beans.employeeService.getEmplyee(user.getUid(), tenant.getTid());
			if (null == employee)
				throw new RuntimeException();
		}
		return new Credential(app, tenant, user, employee);
	}
	
	private static final Tenant _getBaoTuTenant(String code) throws ConstConvertFailureException {
		String sub = code.substring(BtkjConsts.APP_ID_BIT_NUM, BtkjConsts.APP_ID_BIT_NUM + BtkjConsts.TENANT_ID_BIT_NUM);
		if (!BtkjUtil.hasTenant(sub))
			return null;
		int tid = Integer.valueOf(sub);
		TenantCache cache = (TenantCache) Beans.cacheService.getCache(BtkjTables.TENANT.name());
		Tenant tenant = cache.getBaotuTenant(tid);
		if (null == tenant)
			throw new RuntimeException();
		return tenant;
	}
	
	private static final Tenant _getIsolateTenant(int appId) { 
		TenantCache cache = (TenantCache) Beans.cacheService.getCache(BtkjTables.TENANT.name());
		Tenant tenant = cache.getIsolateTenant(appId);
		if (null == tenant)
			throw new RuntimeException();
		return tenant;
	}
}
