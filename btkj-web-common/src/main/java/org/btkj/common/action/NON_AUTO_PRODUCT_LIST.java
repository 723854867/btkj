package org.btkj.common.action;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 非车险产品列表
 * 
 * @author ahab
 */
public class NON_AUTO_PRODUCT_LIST extends TenantAction {

	@Override
	protected Result<?> execute(Request request, Client client, App app, Tenant tenant, User user) {
		return null;
	}
}
