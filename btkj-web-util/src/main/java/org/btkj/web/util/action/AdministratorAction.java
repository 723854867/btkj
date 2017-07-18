package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.master.api.CloudService;
import org.btkj.pojo.po.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 超级管理员 action，用在保途的管理平台中
 * 
 * @author ahab
 */
public abstract class AdministratorAction implements Action {
	
	@Resource
	protected CloudService cloudService;
	
	@Override
	public Result<?> execute(Request request) {
		String token = request.getHeader(Params.TOKEN);
		Administrator administrator = cloudService.getAdministratorByToken(token);
		if (null == administrator)
			return Result.result(Code.TOKEN_INVALID);
		return execute(request, administrator);
	}
	
	protected abstract Result<?> execute(Request request, Administrator operator);
}
