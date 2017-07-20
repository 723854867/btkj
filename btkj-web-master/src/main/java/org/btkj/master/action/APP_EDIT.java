package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.pojo.po.Administrator;
import org.btkj.user.api.UserManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class APP_EDIT extends LoggedAction {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			int region = request.getParam(Params.REGION);
			String name = request.getParam(Params.NAME);
			int maxTenantsCount = request.getOptionalParam(Params.MAX_TENANTS_COUNT);
			int maxArticlesCount = request.getOptionalParam(Params.MAX_ARTICLES_COUNT);	
			return userManageService.appAdd(region, name, maxTenantsCount, maxArticlesCount);
		case UPDATE:
			region = request.getOptionalParam(Params.REGION);
			name = request.getOptionalParam(Params.NAME);
			maxTenantsCount = request.getOptionalParam(Params.MAX_TENANTS_COUNT);
			maxArticlesCount = request.getOptionalParam(Params.MAX_ARTICLES_COUNT);	
			return userManageService.appUpdate(request.getParam(Params.ID), region, name, maxTenantsCount, maxArticlesCount);
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
