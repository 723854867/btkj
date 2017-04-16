package org.btkj.common.action;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
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
	protected Result<?> execute(Request request, Client client, App app, Tenant tenant, User user) {
		return null;
	}
}
