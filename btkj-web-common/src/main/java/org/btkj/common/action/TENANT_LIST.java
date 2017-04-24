package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.TenantListInfo;
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
	protected Result<TenantListInfo> execute(Request request, App app, Client client, User user) {
		return null;
	}
}
