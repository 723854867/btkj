package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.param.AreaEditParam;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class AREA_EDIT extends AdminAction<AreaEditParam> {
	
	@Resource
	private ConfigManageService configManageService;
	
	public AREA_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE);
	}

	@Override
	protected Result<Void> execute(Administrator admin, AreaEditParam param) {
		return configManageService.areaEdit(param);
	}
}
