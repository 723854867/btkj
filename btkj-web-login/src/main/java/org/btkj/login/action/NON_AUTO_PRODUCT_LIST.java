package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.submit.NonAutoProductSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

/**
 * 非车险产品列表
 * 
 * @author ahab
 */
public class NON_AUTO_PRODUCT_LIST implements Action {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	public Result<?> execute(Request request) {
		NonAutoProductSearcher searcher = request.getParam(Params.NON_AUTO_PRODUCT_SEARCHER);
		return Result.result(nonAutoService.productList(searcher));
	}
}
