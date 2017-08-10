package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.param.TenantsParam;
import org.rapid.util.common.message.Result;

public class TENANTS extends AdminAction<TenantsParam> {
	
	@Resource
	private UserManageService userManageService;
	
	@Override
	protected Result<?> execute(Administrator admin, TenantsParam param) {
		param.setClient(Client.BAO_TU_MANAGER);
		return userManageService.tenants(param);
	}
}
