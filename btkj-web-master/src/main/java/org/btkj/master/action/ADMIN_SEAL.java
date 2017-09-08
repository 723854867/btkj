package org.btkj.master.action;

import org.btkj.master.AdminAction;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.IdParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class ADMIN_SEAL extends AdminAction<IdParam> {

	@Override
	protected Result<Void> execute(Admin admin, IdParam param) {
		if (admin.getId() == param.getId())
			return Consts.RESULT.FORBID;
		return adminService.seal(param.getId());
	}
}
