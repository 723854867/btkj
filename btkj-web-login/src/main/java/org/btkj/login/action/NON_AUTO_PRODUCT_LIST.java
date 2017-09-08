package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.entity.nonauto.NonAutoProduct;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.nonauto.NonAutoProductListParam;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

/**
 * 非车险产品列表
 * 
 * @author ahab
 */
public class NON_AUTO_PRODUCT_LIST extends Action<NonAutoProductListParam> {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	protected Result<Pager<NonAutoProduct>> execute(NonAutoProductListParam param) {
		return Result.result(nonAutoService.products(param));
	}
}
