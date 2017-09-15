package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.user.TenantListInfo;
import org.btkj.pojo.param.NilParam;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 用户代理公司列表：包括正在审核的代理公司列表和已经拥有的代理公司列表
 * 
 * @author ahab
 */
public class TENANT_LIST extends UserAction<NilParam> {
	
	@Resource
	private TenantService tenantService;


	@Override
	protected Result<TenantListInfo> execute(App app, User user, NilParam param) {
		return Result.result(tenantService.tenantListInfo(user));
	}
}
