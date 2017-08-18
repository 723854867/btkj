package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.NilParam;
import org.btkj.user.api.TenantService;
import org.btkj.user.pojo.info.TenantListInfo;
import org.rapid.util.common.message.Result;

/**
 * 用户商户列表：包括已经拥有的和正在审核的
 * 
 * @author ahab
 */
public class TENANTS_USER extends UserAction<NilParam> {
	
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<TenantListInfo> execute(AppPO app, UserPO user, NilParam param) {
		return Result.result(tenantService.tenantListInfo(user));
	}
}
