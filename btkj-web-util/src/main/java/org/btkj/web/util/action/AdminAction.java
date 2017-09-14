package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.master.api.AdminService;
import org.btkj.master.api.CloudService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.entity.master.Admin.Mod;
import org.btkj.pojo.param.Param;
import org.btkj.web.util.Params;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public abstract class AdminAction<PARAM extends Param> extends Action<PARAM> {
	
	@Resource
	protected AdminService adminService;
	@Resource
	protected CloudService cloudService;
	
	public AdminAction() {
		super();
	}
	
	public AdminAction(CrudType... crudTypes) {
		super(crudTypes);
	}

	@Override
	protected Result<?> execute(PARAM param) {
		Admin admin = cloudService.getAdministratorByToken(request().getHeader(Params.TOKEN));
		if (null == admin)
			return Consts.RESULT.TOKEN_INVALID;
		if (Mod.SEAL.satisfy(admin.getMod()))
			return BtkjConsts.RESULT.ADMIN_SEALED;
		return execute(admin, param);
	}
	
	protected abstract Result<?> execute(Admin admin, PARAM param);
}
