package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.pojo.info.user.TenantListInfo;
import org.btkj.pojo.model.identity.User;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.action.OldUserAction;
import org.btkj.web.util.action.Request;
import org.rapid.util.common.message.Result;

/**
 * 用户代理公司列表：包括正在审核的代理公司列表和已经拥有的代理公司列表
 * 
 * @author ahab
 */
public class TENANT_LIST extends OldUserAction {
	
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<TenantListInfo> execute(Request request, User user) {
		return Result.result(tenantService.tenantListInfo(user.getEntity()));
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
}
