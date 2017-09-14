package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.nonauto.NonAutoProductListParam;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;

public class NON_AUTO_PRODUCT_LIST extends AdminAction<NonAutoProductListParam> {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	protected Result<?> execute(Admin admin, NonAutoProductListParam param) {
		return Result.result(nonAutoService.products(param));
	}
}
