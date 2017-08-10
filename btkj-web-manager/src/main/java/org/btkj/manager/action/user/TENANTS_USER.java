package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.param.Param;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.TenantService;
import org.btkj.user.pojo.info.TenantListInfo;
import org.rapid.util.common.message.Result;

/**
 * 用户商户列表：包括已经拥有的和正在审核的
 * 
 * @author ahab
 */
public class TENANTS_USER extends UserAction<Param> {
	
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<TenantListInfo> execute(AppPO app, UserPO user, Param param) {
		return Result.result(tenantService.tenantListInfo(user));
	}
}
