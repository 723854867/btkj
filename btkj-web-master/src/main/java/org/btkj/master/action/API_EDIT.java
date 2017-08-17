package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.param.ApiEditParam;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class API_EDIT extends AdminAction<ApiEditParam> {
	
	@Resource
	private ConfigManageService configManageService;
	
	public API_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<Void> execute(Administrator admin, ApiEditParam param) {
		return configManageService.apiEdit(param);
	}
}
