package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.api.MasterService;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.param.AdminEditParam;
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
