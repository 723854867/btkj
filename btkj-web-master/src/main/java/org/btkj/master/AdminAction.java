package org.btkj.master;

import javax.annotation.Resource;

import org.btkj.master.api.CloudService;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.btkj.web.util.Params;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

public abstract class AdminAction<PARAM extends Param> extends Action<PARAM> {
	
	@Resource
	protected CloudService cloudService;

	@Override
	protected final Result<?> execute(PARAM param) {
		Administrator admin = cloudService.getAdministratorByToken(request().getHeader(Params.TOKEN));
		if (null == admin)
			return Result.result(Code.TOKEN_INVALID);
		return execute(admin, param);
	}
	
	protected abstract Result<?> execute(Administrator admin, PARAM param);
}
