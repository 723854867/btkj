package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.user.AppEditParam;
import org.btkj.user.api.UserManageService;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class APP_EDIT extends AdminAction<AppEditParam> {
	
	@Resource
	private UserManageService userManageService;
	
	public APP_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE);
	}

	@Override
	protected Result<?> execute(Admin admin, AppEditParam param) {
		return userManageService.appEdit(param);
	}
}
