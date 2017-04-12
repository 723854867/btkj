package org.btkj.common.action.impl;

import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 我的团队
 * 
 * @author ahab
 */
public class TEAM_INFO extends TenantAction {

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		String token = request.getHeader(Params.TOKEN);
		return null;
	}
}
