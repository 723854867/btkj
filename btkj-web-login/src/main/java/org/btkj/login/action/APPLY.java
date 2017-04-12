package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.user.api.LoginService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.cache.CacheService;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;


/**
 * 申请分为两类
 * 
 * @author ahab
 */
public class APPLY implements Action {
	
	@Resource
	private Redis redis;
	@Resource
	private LoginService loginService;
	@Resource
	private CacheService<?> cacheService;

	@Override
	public Result<?> execute(Request request) {
		int employeeId = request.getParam(Params.EMPLOYEE_ID);
		String token = request.getOptionalHeader(Params.TOKEN);
		if (null != token) 												// 老用户申请
			return loginService.apply(token, employeeId);
		String sessionId = request.getHeader(Params.SESSION_ID);
		DistributeSession session = request.distributeSession(redis, sessionId);
		Integer appId = session.getAndDelInt(Params.APP_ID.key());
		String mobile = session.getAndDel(Params.MOBILE.key());
		if (null == appId || null == mobile)
			return Result.result(Code.FORBID);
		String name = request.getParam(Params.NAME);
		String identity = request.getParam(Params.IDENTITY);
		return loginService.apply(appId, mobile, name, identity, employeeId);
	}
}
