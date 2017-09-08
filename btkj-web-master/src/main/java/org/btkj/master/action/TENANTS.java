package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.user.TenantsParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class TENANTS extends AdminAction<TenantsParam> {
	
	@Resource
	private UserManageService userManageService;
	
	@Override
	protected Result<?> execute(Admin admin, TenantsParam param) {
		param.setClient(Client.BAO_TU_MANAGER);
		return userManageService.tenants(param);
	}
}
