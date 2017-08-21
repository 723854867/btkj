package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class MODULAR_EDIT extends AdminAction<ModularEditParam> {
	
	@Resource
	private ConfigManageService configManageService;
	
	public MODULAR_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<?> execute(Admin admin, ModularEditParam param) {
		return configManageService.modularEdit(param);
	}
}
