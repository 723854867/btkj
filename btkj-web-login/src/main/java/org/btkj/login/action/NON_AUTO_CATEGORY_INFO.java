package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

/**
 * 非车险类型信息
 * 
 * @author ahab
 */
public class NON_AUTO_CATEGORY_INFO implements Action {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	public Result<NonAutoCategory> execute(Request request) {
		NonAutoCategory category = nonAutoService.getCategoryById(request.getParam(Params.ID));
		if (null == category)
			return Result.result(BtkjCode.NON_AUTO_CATEGORY_NOT_EXIST);
		return Result.result(category);
	}
}
