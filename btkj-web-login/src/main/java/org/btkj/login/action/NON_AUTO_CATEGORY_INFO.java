package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.nonauto.NonAutoCategory;
import org.btkj.pojo.param.IdParam;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

/**
 * 非车险类型信息
 * 
 * @author ahab
 */
public class NON_AUTO_CATEGORY_INFO extends Action<IdParam> {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	protected Result<NonAutoCategory> execute(IdParam param) {
		NonAutoCategory category = nonAutoService.category(param.getId());
		return null == category ? Result.result(BtkjCode.NON_AUTO_CATEGORY_NOT_EXIST) : Result.result(category);
	}
}
