package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.TenantListInfo;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 用户代理公司列表：包括正在审核的代理公司列表和已经拥有的代理公司列表
 * 
 * @author ahab
 */
public class TENANT_LIST extends UserAction {
	
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<TenantListInfo> execute(Request request, AppPO app, Client client, UserPO user) {
		return Result.result(tenantService.tenantListInfo(client, app, user));
	}
}
