package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.nonauto.pojo.param.NonAutoProductEditParam;
import org.rapid.util.common.message.Result;

public class NON_AUTO_PRODUCT_EDIT extends AdminAction<NonAutoProductEditParam> {
	
	@Resource
	private NonAutoService nonAutoService;
	
	@Override
	protected Result<Void> execute(Administrator admin, NonAutoProductEditParam param) {
		return nonAutoService.editProduct(param.entity());
	}
}
