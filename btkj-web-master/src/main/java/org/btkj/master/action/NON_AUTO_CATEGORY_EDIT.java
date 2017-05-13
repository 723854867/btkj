package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtils;

/**
 * 添加非车险类型
 * 
 * @author ahab
 */
public class NON_AUTO_CATEGORY_EDIT extends AdministratorAction {
	
	@Resource
	private NonAutoService nonAutoService;
	
	@Override
	protected Result<?> execute(Request request, Administrator operator) {
		NonAutoCategory category = _checkCategory(request.getParam(Params.NON_AUTO_CATEGORY));
		nonAutoService.editCategory(category);
		return Result.success();
	}
	
	private NonAutoCategory _checkCategory(NonAutoCategory category) { 
		if (null == category.getName())
			throw ConstConvertFailureException.errorConstException(Params.NON_AUTO_CATEGORY);
		
		int time = DateUtils.currentTime();
		category.setUpdated(time);
		if (0 == category.get_id())
			category.setCreated(time);
		return category;
	}
}
