package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.api.MasterService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.master.AdminEditParam;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class ADMIN_EDIT extends AdminAction<AdminEditParam> {
	
	@Resource
	private MasterService masterService;
	
	public ADMIN_EDIT() {
		super(CrudType.CREATE);
	}
	
	@Override
	protected Result<?> execute(Admin admin, AdminEditParam param) {
		return masterService.adminEdit(param);
	}
}
