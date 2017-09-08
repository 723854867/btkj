package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.nonauto.NonAutoProductEditParam;
import org.rapid.util.common.message.Result;

public class NON_AUTO_PRODUCT_EDIT extends AdminAction<NonAutoProductEditParam> {
	
	@Resource
	private NonAutoService nonAutoService;
	
	@Override
	protected Result<Void> execute(Admin admin, NonAutoProductEditParam param) {
		return nonAutoService.editProduct(param.entity());
	}
}
