package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.nonauto.pojo.param.NonAutoProductListParam;
import org.rapid.util.common.message.Result;

public class NON_AUTO_PRODUCT_LIST extends AdminAction<NonAutoProductListParam> {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	protected Result<?> execute(Administrator admin, NonAutoProductListParam param) {
		return Result.result(nonAutoService.products(param));
	}
}
