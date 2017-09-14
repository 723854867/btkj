package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.config.AreaEditParam;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class AREA_EDIT extends AdminAction<AreaEditParam> {
	
	@Resource
	private ConfigManageService configManageService;
	
	public AREA_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE);
	}

	@Override
	protected Result<Void> execute(Admin admin, AreaEditParam param) {
		return configManageService.areaEdit(param);
	}
}
