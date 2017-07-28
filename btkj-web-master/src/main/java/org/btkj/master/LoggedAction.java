package org.btkj.master;

import javax.annotation.Resource;

import org.btkj.master.api.CloudService;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 超级管理员 action，用在保途的管理平台中
 * 
 * @author ahab
 */
public abstract class LoggedAction extends Action {
	
	@Resource
	protected CloudService cloudService;
	
	@Override
	public Result<?> execute(Request request) {
		Administrator administrator = cloudService.getAdministratorByToken(request.getHeader(Params.TOKEN));
		if (null == administrator)
			return Result.result(Code.TOKEN_INVALID);
		return execute(request, administrator);
	}
	
	protected abstract Result<?> execute(Request request, Administrator operator);
}
