package org.btkj.master;

import javax.annotation.Resource;

import org.btkj.master.api.CloudService;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.btkj.web.util.Params;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public abstract class AdminAction<PARAM extends Param> extends Action<PARAM> {
	
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
		Administrator admin = cloudService.getAdministratorByToken(request().getHeader(Params.TOKEN));
		if (null == admin)
			return Consts.RESULT.TOKEN_INVALID;
		return execute(admin, param);
	}
	
	protected abstract Result<?> execute(Administrator admin, PARAM param);
}
