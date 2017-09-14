package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.config.InsurerEditParam;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class INSURER_EDIT extends AdminAction<InsurerEditParam> {
	
	@Resource
	private ConfigManageService configManageService;
	
	public INSURER_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE);
	}

	@Override
	protected Result<Void> execute(Admin admin, InsurerEditParam param) {
		return configManageService.insurerEdit(param);
	}
}
