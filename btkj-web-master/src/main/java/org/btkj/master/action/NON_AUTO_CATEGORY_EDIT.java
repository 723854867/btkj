package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.po.Administrator;
import org.btkj.pojo.po.NonAutoCategory;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtil;

/**
 * 添加非车险类型
 * 
 * @author ahab
 */
public class NON_AUTO_CATEGORY_EDIT extends LoggedAction {
	
	@Resource
	private NonAutoService nonAutoService;
	
	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		NonAutoCategory category = _check(request.getParam(Params.NON_AUTO_CATEGORY));
		nonAutoService.editCategory(category);
		return Result.success();
	}
	
	private NonAutoCategory _check(NonAutoCategory category) { 
		if (null == category.getName())
			throw ConstConvertFailureException.errorConstException(Params.NON_AUTO_CATEGORY);
		
		int time = DateUtil.currentTime();
		category.setUpdated(time);
		if (0 == category.get_id())
			category.setCreated(time);
		return category;
	}
}
