package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.NonAutoProduct;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.NonAutoProductSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 非车险产品列表
 * 
 * @author ahab
 */
public class NON_AUTO_PRODUCT_LIST extends TenantAction {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	protected Result<Pager<NonAutoProduct>> execute(Request request, Client client, App app, Tenant tenant, User user) {
		NonAutoProductSearcher searcher = request.getParam(Params.NON_AUTO_PRODUCT_SEARCHER);
		if (searcher.getSortType() != 1)
			searcher.setSortType(-1);
		return Result.result(nonAutoService.productList(searcher));
	}
}
