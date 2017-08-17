package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.param.InsurerEditParam;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class INSURER_EDIT extends AdminAction<InsurerEditParam> {
	
	@Resource
	private ConfigManageService configManageService;
	
	public INSURER_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE);
	}

	@Override
	protected Result<Void> execute(Administrator admin, InsurerEditParam param) {
		return configManageService.insurerEdit(param);
	}
}
