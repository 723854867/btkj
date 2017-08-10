package org.btkj.manager.action.user;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Region;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.param.TenantsParam;
import org.rapid.util.common.message.Result;

public class TENANTS extends UserAction<TenantsParam> {
	
	@Resource
	private ConfigService configService;
	@Resource
	private UserManageService userManageService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantsParam param) {
		param.setAppId(user.getAppId());
		param.setClient(Client.TENANT_MANAGER);
		Result<Pager<TenantPagingInfo>> result = userManageService.tenants(param);
		Set<Integer> set = new HashSet<Integer>();
		for (TenantPagingInfo info : result.attach().getList())
			set.add(info.getRegionId());
		Map<Integer, Region> regions = configService.regions(set);
		for (TenantPagingInfo info : result.attach().getList()) {
			Region region = regions.get(info.getRegionId());
			info.setRegionName(null != region ? region.getName() : null);
		}
		return result;
	}
}
