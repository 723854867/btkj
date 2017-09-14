package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.config.ApiEditParam;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class API_EDIT extends AdminAction<ApiEditParam> {
	
	@Resource
	private ConfigManageService configManageService;
	
	public API_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<Void> execute(Admin admin, ApiEditParam param) {
		Result<Void> result = configManageService.apiEdit(param);
		return result;
	}
}
