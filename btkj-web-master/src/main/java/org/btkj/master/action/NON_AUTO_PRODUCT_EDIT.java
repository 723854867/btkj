package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.po.Administrator;
import org.btkj.pojo.po.NonAutoProduct;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtil;

public class NON_AUTO_PRODUCT_EDIT extends LoggedAction {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		NonAutoProduct product = _check(request.getParam(Params.NON_AUTO_PRODUCT));
		nonAutoService.editProduct(product);
		return Result.success();
	}
	
	private NonAutoProduct _check(NonAutoProduct product) { 
		if (null == product.getName())
			throw ConstConvertFailureException.errorConstException(Params.NON_AUTO_PRODUCT);
		
		int time = DateUtil.currentTime();
		product.setUpdated(time);
		if (0 == product.get_id())
			product.setCreated(time);
		return product;
	}
}
