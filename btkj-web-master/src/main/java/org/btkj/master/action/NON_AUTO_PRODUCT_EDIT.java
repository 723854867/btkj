package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.NonAutoProduct;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtils;

public class NON_AUTO_PRODUCT_EDIT extends AdministratorAction {
	
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
		
		int time = DateUtils.currentTime();
		product.setUpdated(time);
		if (0 == product.get_id())
			product.setCreated(time);
		return product;
	}
}